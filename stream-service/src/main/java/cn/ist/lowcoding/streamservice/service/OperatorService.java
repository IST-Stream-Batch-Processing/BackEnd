package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {
    @Autowired
    OperatorRepo operatorRepo;

    public Optional<Operator> getOperatorById(String operatorId) {
        return operatorRepo.findById(operatorId);
    }

    public void deleteOperatorById(String operatorId) {
        operatorRepo.deleteById(operatorId);
    }

    public List<Operator> getAllOperators() {
        return operatorRepo.findAll();
    }
}
