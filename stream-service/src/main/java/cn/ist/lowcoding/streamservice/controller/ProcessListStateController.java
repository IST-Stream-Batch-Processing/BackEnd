package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessListStateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.ProcessListStateVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.service.ProcessListStateService;
import cn.ist.lowcoding.streamservice.service.TimeWindowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProcessListStateController {
    @Autowired
    ProcessListStateService processListStateService;

    @ApiOperation(value = "注册ProcessListState算子")
    @PostMapping("/processListState")
    public Result<String> registerProcessListState(@RequestBody CreateProcessListStateRequest request){
        String processListStateId = processListStateService.registerProcessListState(request);
        return ResultUtil.success(processListStateId);
    }

    @ApiOperation(value = "获取指定编排对应的ProcessListState算子展示表单")
    @GetMapping("/processListState/combination/{combinationId}")
    public Result<ProcessListStateVO> getProcessListStateDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        ProcessListStateVO res = processListStateService.getProcessListStateDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
