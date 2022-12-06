package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateWindowViewCountRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.WindowViewCountVO;
import cn.ist.lowcoding.streamservice.service.WindowViewCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WindowViewCountController {
    @Autowired
    WindowViewCountService windowViewCountService;

    @ApiOperation(value = "注册WindowViewCount算子")
    @PostMapping("/windowViewCount")
    public Result<String> registerWindowViewCount(@RequestBody CreateWindowViewCountRequest request){
        String windowViewCountId = windowViewCountService.registerWindowViewCount(request);
        return ResultUtil.success(windowViewCountId);
    }

    @ApiOperation(value = "获取指定编排对应的WindowViewCount算子展示表单")
    @GetMapping("/windowViewCount/combination/{combinationId}")
    public Result<WindowViewCountVO> getWindowViewCountDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        WindowViewCountVO res = windowViewCountService.getWindowViewCountDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
