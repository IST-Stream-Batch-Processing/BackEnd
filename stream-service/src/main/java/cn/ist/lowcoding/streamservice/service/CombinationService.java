package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinationService {
    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    OperatorRepo operatorRepo;

    public String registerCombination(String dataId) {
        Combination combination = new Combination();
        combination.setDataId(dataId);
        combinationRepo.save(combination);

        String combinationId = combination.getId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));

        List<String> combinationIds = data.getCombinationIds();
        combinationIds.add(combinationId);
        dataRepo.save(data);

        return combinationId;
    }

    public List<Combination> getAllCombinations() {
        return combinationRepo.findAll();
    }

    public Combination getCombinationById(String combinationId) {
        return combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
    }

    public void deleteCombinationById(String combinationId) {
        // 删除编排中包含的所有算子
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        for (String operatorId : operatorIds) {
            operatorRepo.deleteById(operatorId);
        }

        // 更新编排对应的数据源
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        List<String> combinationIds = data.getCombinationIds();
        combinationIds.remove(combinationId);
        dataRepo.save(data);

        combinationRepo.deleteById(combinationId);
    }
}
