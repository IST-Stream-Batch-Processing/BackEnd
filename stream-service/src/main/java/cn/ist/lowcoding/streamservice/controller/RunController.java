package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;

import cn.ist.lowcoding.streamservice.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {
    @Autowired
    RunService runService;

    @GetMapping("/run/{combinationId}")
    public Result<String> run(@PathVariable("combinationId") String combinationId){
        runService.start(combinationId);
        return ResultUtil.success();
    }
}
