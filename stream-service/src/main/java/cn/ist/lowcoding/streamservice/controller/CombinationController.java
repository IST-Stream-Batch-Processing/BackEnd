package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.service.CombinationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CombinationController {
    @Autowired
    CombinationService combinationService;

    @ApiOperation(value = "向指定的数据源注册编排")
    @PostMapping("/combination/{dataId}")
    public Result<String> registerCombination(@PathVariable("dataId") String dataId) {
        String combinationId = combinationService.registerCombination(dataId);

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
    public Result<String> deleteCombinationById(@PathVariable String combinationId) {
        // 未实现
        combinationService.deleteCombinationById(combinationId);
        return ResultUtil.success();
    }

    @GetMapping("/combination/data/{dataId}")
    public Result<List<Combination>> getAllCombinationsByDataId(@PathVariable String dataId) {
        //未实现
        return null;
    }
}
