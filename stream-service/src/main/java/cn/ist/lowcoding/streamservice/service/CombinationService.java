package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateCombinationRequest;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinationService {
    @Autowired
    CombinationRepo combinationRepo;

    public String registerCombination(String dataId) {
        Combination combination = new Combination();
        combination.setDataId(dataId);
        combinationRepo.save(combination);
        return combination.getId();
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
