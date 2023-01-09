package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.freemaker.*;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.*;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateCombinationRequest;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.util.CodeGenerate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CombinationService {
    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    OperatorService operatorService;

    public String registerCombination(CreateCombinationRequest request) {
        Combination combination = new Combination();
        BeanUtils.copyProperties(request, combination);
        combinationRepo.save(combination);

        // 更新编排对应的数据源
        String dataId = request.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        List<String> combinationIds = data.getCombinationIds();
        String combinationId = combination.getId();
        combinationIds.add(combinationId);
        dataRepo.save(data);

        return combinationId;
    }

    private void fillOperatorsInCombination(Combination combination) {
        List<Operator> operators = combination.getOperators();
        List<String> operatorIds = combination.getOperatorIds();
        for (String operatorId : operatorIds) {
            Operator operator = operatorService.getOperatorById(operatorId);
            operators.add(operator);
        }
    }

    public List<Combination> getAllCombinations() {
        List<Combination> allCombinations = combinationRepo.findAll();
        for (Combination combination : allCombinations) {
            fillOperatorsInCombination(combination);
        }
        return allCombinations;
    }

    public Combination getCombinationById(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        fillOperatorsInCombination(combination);
        return combination;
    }

    public void deleteCombinationById(String combinationId) {
        // 删除编排中包含的所有算子
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        for (String operatorId : operatorIds) {
            operatorService.deleteOperatorById(operatorId);
        }

        // 更新编排对应的数据源
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        List<String> combinationIds = data.getCombinationIds();
        combinationIds.remove(combinationId);
        List<Combination> combinations = data.getCombinations();
        combinations.remove(combination);
        dataRepo.save(data);

        combinationRepo.deleteById(combinationId);
    }

    public void generateCombinationById(String combinationId) {
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
            Operator operator = operatorService.getOperatorById(operatorId);
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

    public void runCombinationById(String combinationId, String sessionId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        CodeGenerate codeGenerate = new CodeGenerate();
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        codeGenerate.javac(data.getClassName()+".java");
        int size = operatorIds.size();
        for(int i=0;i<size;i++){
            String operatorId = operatorIds.get(i);
            Operator operator = operatorService.getOperatorById(operatorId);
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
                codeGenerate.javac("WindowViewCount" + ".java");
            }
            else if(name.equals("StreamAggregate")){
                codeGenerate.javac("StreamAggregate"+operatorId+".java");
            }
            else if(name.equals("StreamProcessListState")){
                codeGenerate.javac("StreamProcessListState"+operatorId+".java");
            }
        }
        codeGenerate.javac("StreamCombination"+combinationId+".java");
        try {
            Class<?> clazz = codeGenerate.java("StreamCombination"+combinationId);
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("run", String.class);
            method.invoke(object, sessionId);
        } catch (Exception e) {
            throw new RuntimeException("运行编排时出错");
        }
    }
}
