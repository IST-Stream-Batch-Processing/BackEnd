package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateCombinationRequest;
import cn.ist.lowcoding.streamservice.service.CombinationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CombinationController {
    @Autowired
    CombinationService combinationService;

    @ApiOperation(value = "注册编排")
    @PostMapping("/combination")
    public Result<String> registerCombination(@RequestBody CreateCombinationRequest request) {
        String combinationId = combinationService.registerCombination(request);

        return ResultUtil.success(combinationId);
    }

    @ApiOperation(value = "获取所有编排")
    @GetMapping("/combination")
    public Result<List<Combination>> getAllCombinations(){
        List<Combination> res = combinationService.getAllCombinations();
        return ResultUtil.success(res);
    }

    @ApiOperation(value = "获取指定的编排")
    @GetMapping("/combination/{id}")
    public Result<Combination> getCombinationById(@PathVariable("id") String combinationId) {
        //未测试
        Combination res = combinationService.getCombinationById(combinationId);
        return ResultUtil.success(res);
    }


    //TODO: 更新方法待定

    @DeleteMapping("/combination/{id}")
    public Result<String> deleteCombinationById(@PathVariable("id") String combinationId) {
        combinationService.deleteCombinationById(combinationId);
        return ResultUtil.success();
    }

    @GetMapping("/combination/data/{dataId}")
    public Result<List<Combination>> getAllCombinationsByDataId(@PathVariable String dataId) {
        //未实现
        return null;
    }
}
