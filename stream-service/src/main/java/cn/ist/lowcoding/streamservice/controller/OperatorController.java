package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperatorController {
    @Autowired
    OperatorService operatorService;

    @GetMapping("/operator")
    public List<Operator> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @GetMapping("/operator/{id}")
    public Operator getOperatorById(@PathVariable("id") String operatorId) {
        return operatorService.getOperatorById(operatorId);
    }


}
