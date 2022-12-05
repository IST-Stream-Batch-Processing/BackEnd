package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinationService {
    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

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
        //TODO: 更新combination对应的data

        //TODO: 删除combination中包含的所有operator
    }
}
