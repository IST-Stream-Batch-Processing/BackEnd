package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {
    @Autowired
    OperatorRepo operatorRepo;

    @Autowired
    CombinationRepo combinationRepo;

    public List<Operator> getAllOperators() {
        return operatorRepo.findAll();
    }

    public Operator getOperatorById(String operatorId) {
        return operatorRepo.findById(operatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
    }

    public void deleteOperatorById(String operatorId) {
        Operator operator = getOperatorById(operatorId);

        // 更新算子对应的编排
        String combinationId = operator.getCombinationId();
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        List<String> operatorFinalTypes = combination.getFinalTypes();
        int operatorIndex = operatorIds.indexOf(operatorId);
        operatorIds.remove(operatorIndex);
        operatorFinalTypes.remove(operatorIndex);
        combinationRepo.save(combination);

        operatorRepo.deleteById(operatorId);
    }

    protected void updateCombinationByOperator(Operator operator) {
        String combinationId = operator.getCombinationId();
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));

        List<String> operatorIds = combination.getOperatorIds();
        operatorIds.add(operator.getId());

        List<String> finalTypes = combination.getFinalTypes();
        finalTypes.add(operator.getFinalType());

        combinationRepo.save(combination);
    }
}
