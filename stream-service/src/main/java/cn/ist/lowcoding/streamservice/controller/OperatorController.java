package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.service.OperatorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperatorController {
    @Autowired
    OperatorService operatorService;

    @ApiOperation(value = "获取所有算子")
    @GetMapping("/operator")
    public List<Operator> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @ApiOperation(value = "获取指定的算子")
    @GetMapping("/operator/{id}")
    public Operator getOperatorById(@PathVariable("id") String operatorId) {
        return operatorService.getOperatorById(operatorId);
    }


}
