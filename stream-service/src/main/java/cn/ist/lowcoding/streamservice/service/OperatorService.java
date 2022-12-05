package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {
    @Autowired
    OperatorRepo operatorRepo;

    public List<Operator> getAllOperators() {
        return operatorRepo.findAll();
    }

    public Operator getOperatorById(String operatorId) {
        return operatorRepo.findById(operatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
    }

    public void deleteOperatorById(String operatorId) {
        // TODO: 更新相关combination
        operatorRepo.deleteById(operatorId);
    }
}
