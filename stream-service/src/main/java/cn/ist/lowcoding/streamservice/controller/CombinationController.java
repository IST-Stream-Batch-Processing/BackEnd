package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateCombinationRequest;
import cn.ist.lowcoding.streamservice.service.CombinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CombinationController {
    @Autowired
    CombinationService combinationService;

    @PostMapping("/combination")
    public Result<String> registerCombination(@RequestBody CreateCombinationRequest createCombinationRequest) {
        String combinationId = combinationService.registerCombination(createCombinationRequest);
        return ResultUtil.success(combinationId);
    }

    @GetMapping("/combination")
    public Result<List<Combination>> getAllCombinations(){
        List<Combination> res = combinationService.getAllCombinations();
        return ResultUtil.success(res);
    }

}
