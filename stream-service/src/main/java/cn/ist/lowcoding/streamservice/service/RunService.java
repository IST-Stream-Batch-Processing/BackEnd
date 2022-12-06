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

    public void start(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        CodeGenerate codeGenerate = new CodeGenerate();
        FMDataModel fmDataModel = new FMDataModel();
        BeanUtils.copyProperties(data, fmDataModel);
        fmDataModel.setId("");
        fmDataModel.generate();
        codeGenerate.javac(data.getClassName() + ".java");

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
                codeGenerate.javac("StreamMapConstruct"+operatorId + ".java");

            }
            else if(name.equals("StreamAscendingTimeStamp")){
                AscendingTimeStamp ascendingTimeStamp = (AscendingTimeStamp) operator;
                FMAscendingTimeStamp fmAscendingTimeStamp = new FMAscendingTimeStamp();
                BeanUtils.copyProperties(ascendingTimeStamp,fmAscendingTimeStamp);
//                String originalType = ascendingTimeStamp.getOriginalType();
//                originalType = originalType + dataId;
//                fmAscendingTimeStamp.setOriginalType(originalType);
                fmAscendingTimeStamp.generate();
                codeGenerate.javac("StreamAscendingTimeStamp"+operatorId + ".java");
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
                codeGenerate.javac("StreamFilterDataClassOne"+operatorId + ".java");
            }
            else if(name.equals("StreamKeyByDataClass")){
                KeyByDataClass keyByDataClass = (KeyByDataClass) operator;
                FMKeyByDataClass fmKeyByDataClass = new FMKeyByDataClass();
                BeanUtils.copyProperties(keyByDataClass,fmKeyByDataClass);
//                String originalType = fmKeyByDataClass.getOriginalType();
//                originalType = originalType + dataId;
//                fmKeyByDataClass.setOriginalType(originalType);
                fmKeyByDataClass.generate();
                codeGenerate.javac("StreamKeyByDataClass"+operatorId + ".java");
            }
            else if(name.equals("StreamTimeWindow")){
                TimeWindow timeWindow = (TimeWindow) operator;
                FMTimeWindow fmTimeWindow = new FMTimeWindow();
                BeanUtils.copyProperties(timeWindow,fmTimeWindow);
                fmTimeWindow.generate();
                codeGenerate.javac("StreamTimeWindow"+operatorId + ".java");
            }
            else if(name.equals("WindowViewCount")){
                WindowViewCount windowViewCount = (WindowViewCount) operator;
                FMWindowViewCount fmWindowViewCount = new FMWindowViewCount();
                BeanUtils.copyProperties("WindowViewCount",windowViewCount);
                fmWindowViewCount.generate();
                codeGenerate.javac("WindowViewCount"+operatorId + ".java");
                operators.remove(i);
            }

        }
        FMCombination fmCombination = new FMCombination();
        fmCombination.setStreamList(operators);
        fmCombination.generate();
        codeGenerate.javac("StreamCombination"+combinationId+".java");
//        codeGenerate.java()

    }
}
