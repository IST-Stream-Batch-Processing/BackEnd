package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateCombinationRequest;
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
}
