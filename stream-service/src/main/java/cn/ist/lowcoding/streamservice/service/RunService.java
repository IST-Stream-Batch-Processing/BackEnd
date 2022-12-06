package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.freemaker.FMDataModel;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.util.CodeGenerate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {
    @Autowired
    CombinationRepo combinationRepo;
    @Autowired
    DataRepo dataRepo;
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
        codeGenerate.javac(data.getClassName());
//        int size = operatorIds.size();
//        for(int i=0;i<size;i++){
//
//        }

    }
}
