package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.service.OperatorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public Result<List<Operator>> getAllOperators() {
        List<Operator> res = operatorService.getAllOperators();
        return ResultUtil.success(res);
    }

    @ApiOperation(value = "获取指定的算子")
    @GetMapping("/operator/{id}")
    public Result<Operator> getOperatorById(@PathVariable("id") String operatorId) {
        Operator res = operatorService.getOperatorById(operatorId);
        return ResultUtil.success(res);
    }

    @ApiOperation(value = "删除指定的算子")
    @DeleteMapping("/operator/{id}")
    public Result<String> deleteOperatorById(@PathVariable("id") String operatorId) {
        operatorService.deleteOperatorById(operatorId);
        return ResultUtil.success();
    }
}
