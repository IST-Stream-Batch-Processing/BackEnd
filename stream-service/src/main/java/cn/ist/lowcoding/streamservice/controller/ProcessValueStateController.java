package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessListStateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessValueStateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.ProcessListStateVO;
import cn.ist.lowcoding.streamservice.service.ProcessListStateService;
import cn.ist.lowcoding.streamservice.service.ProcessValueStateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProcessValueStateController {
    @Autowired
    ProcessValueStateService processValueStateService;

    @ApiOperation(value = "注册ProcessValueState算子")
    @PostMapping("/processValueState")
    public Result<String> registerProcessValueState(@RequestBody CreateProcessValueStateRequest request){
        String processValueStateId = processValueStateService.registerProcessValueState(request);
        return ResultUtil.success(processValueStateId);
    }


}
