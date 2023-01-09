package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.freemaker.*;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.*;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import cn.ist.lowcoding.streamservice.util.CodeGenerate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class RunService {
    @Autowired
    CombinationRepo combinationRepo;
    @Autowired
    DataRepo dataRepo;
    @Autowired
    OperatorRepo operatorRepo;

    public void generate(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        FMDataModel fmDataModel = new FMDataModel();
        BeanUtils.copyProperties(data, fmDataModel);
        fmDataModel.setId("");
        fmDataModel.generate();

        List<Operator> operators = new ArrayList<>();

        int size = operatorIds.size();
        for(int i=0;i<size;i++){
            String operatorId = operatorIds.get(i);
            Operator operator = operatorRepo.findById(operatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
            operators.add(operator);
            String name = operator.getName();
            if(name.equals("StreamMapConstruct")){
                MapConstruct mapConstruct = (MapConstruct) operator;
                FMMapConstruct fmMapConstruct = new FMMapConstruct();
                BeanUtils.copyProperties(mapConstruct, fmMapConstruct);
//                String finalType = mapConstruct.getFinalType();
//                finalType = finalType + dataId;
//                fmMapConstruct.setFinalType(finalType);
                fmMapConstruct.generate();

            }
            else if(name.equals("StreamAscendingTimeStamp")){
                AscendingTimeStamp ascendingTimeStamp = (AscendingTimeStamp) operator;
                FMAscendingTimeStamp fmAscendingTimeStamp = new FMAscendingTimeStamp();
                BeanUtils.copyProperties(ascendingTimeStamp,fmAscendingTimeStamp);
//                String originalType = ascendingTimeStamp.getOriginalType();
//                originalType = originalType + dataId;
//                fmAscendingTimeStamp.setOriginalType(originalType);
                fmAscendingTimeStamp.generate();
            }
            else if(name.equals("StreamFilterDataClassOne")){
                FilterDataClassOne filterDataClassOne = (FilterDataClassOne) operator;
                FMFilterDataClassOne fmFilterDataClassOne = new FMFilterDataClassOne();
                //type的问题
                BeanUtils.copyProperties(filterDataClassOne,fmFilterDataClassOne);
//                String originalType = fmFilterDataClassOne.getOriginalType();
//                originalType = originalType +dataId;
//                fmFilterDataClassOne.setOriginalType(originalType);
                fmFilterDataClassOne.generate();
            }
            else if(name.equals("StreamKeyByDataClass")){
                KeyByDataClass keyByDataClass = (KeyByDataClass) operator;
                FMKeyByDataClass fmKeyByDataClass = new FMKeyByDataClass();
                BeanUtils.copyProperties(keyByDataClass,fmKeyByDataClass);
//                String originalType = fmKeyByDataClass.getOriginalType();
//                originalType = originalType + dataId;
//                fmKeyByDataClass.setOriginalType(originalType);
                fmKeyByDataClass.generate();
            }
            else if(name.equals("StreamTimeWindow")){
                TimeWindow timeWindow = (TimeWindow) operator;
                FMTimeWindow fmTimeWindow = new FMTimeWindow();
                BeanUtils.copyProperties(timeWindow,fmTimeWindow);
                fmTimeWindow.generate();
            }
            else if(name.equals("WindowViewCount")){
                WindowViewCount windowViewCount = (WindowViewCount) operator;
                FMWindowViewCount fmWindowViewCount = new FMWindowViewCount();
                BeanUtils.copyProperties(windowViewCount, fmWindowViewCount);
                fmWindowViewCount.setDataId("");
                fmWindowViewCount.generate();
                operators.remove(i);
            }
            else if(name.equals("StreamAggregate")){
                Aggregate aggregate = (Aggregate) operator;
                FMAggregate fmAggregate = new FMAggregate();
                BeanUtils.copyProperties(aggregate, fmAggregate);
                String originalType = fmAggregate.getOriginalType();
                String[] list = originalType.split(",");
                originalType = list[0];
                fmAggregate.setOriginalType(originalType);
                fmAggregate.generate();
            }
            else if(name.equals("StreamProcessListState")){
                ProcessListState processListState = (ProcessListState) operator;
                FMProcessListState fmProcessListState = new FMProcessListState();
                BeanUtils.copyProperties(processListState, fmProcessListState);
                String originalType = fmProcessListState.getOriginalType();
                String[] list = originalType.split(",");
                originalType = list[0];
                fmProcessListState.setOriginalType(originalType);
                fmProcessListState.generate();
            }

        }
        FMCombination fmCombination = new FMCombination();
        fmCombination.setId(combinationId);
        fmCombination.setStreamList(operators);
        fmCombination.generate();
    }

    public void compile(String combinationId) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        CodeGenerate codeGenerate = new CodeGenerate();
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        codeGenerate.javac(data.getClassName()+".java");
        int size = operatorIds.size();
        for(int i=0;i<size;i++){
            String operatorId = operatorIds.get(i);
            Operator operator = operatorRepo.findById(operatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
            String name = operator.getName();
            if(name.equals("StreamMapConstruct")){
                codeGenerate.javac("StreamMapConstruct"+operatorId + ".java");
            }
            else if(name.equals("StreamAscendingTimeStamp")){
                codeGenerate.javac("StreamAscendingTimeStamp"+operatorId + ".java");
            }
            else if(name.equals("StreamFilterDataClassOne")){
                codeGenerate.javac("StreamFilterDataClassOne"+operatorId + ".java");
            }
            else if(name.equals("StreamKeyByDataClass")){
                codeGenerate.javac("StreamKeyByDataClass"+operatorId + ".java");
            }
            else if(name.equals("StreamTimeWindow")){
                codeGenerate.javac("StreamTimeWindow"+operatorId + ".java");
            }
            else if(name.equals("WindowViewCount")){
                codeGenerate.javac("WindowViewCount"+ ".java");
            }
            else if(name.equals("StreamAggregate")){
                codeGenerate.javac("StreamAggregate"+operatorId+".java");
            }
            else if(name.equals("StreamProcessListState")){
                codeGenerate.javac("StreamProcessListState"+operatorId+".java");
            }
        }
        codeGenerate.javac("StreamCombination"+combinationId+".java");
        Class<?> clazz = codeGenerate.java("StreamCombination"+combinationId);
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("run",String.class);
//        //得到sessionId
        Object[] sessionId = {"0"};
        method.invoke(object,sessionId);

    }
}
