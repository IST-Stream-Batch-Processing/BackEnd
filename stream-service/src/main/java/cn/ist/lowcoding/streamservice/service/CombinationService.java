package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateCombinationRequest;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.BeanUtils;
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
        Data data = dataRepo.findById(dataId).get();

        List<String> combinationIds = data.getCombinationIds();
        combinationIds.add(combinationId);
        dataRepo.save(data);

        return combinationId;
    }

    public List<Combination> getAllCombinations() {
        return combinationRepo.findAll();
    }

    public Combination getCombinationById(String combinationId) {
        return null;
    }

    public void deleteCombinationById(String combinationId) {

    }
}
