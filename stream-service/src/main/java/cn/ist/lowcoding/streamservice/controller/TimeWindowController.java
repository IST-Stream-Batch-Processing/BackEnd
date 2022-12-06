package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateFilterDataClassOneRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.FilterDataClassOneVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.service.FilterDataClassOneService;
import cn.ist.lowcoding.streamservice.service.TimeWindowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeWindowController {
    @Autowired
    TimeWindowService timeWindowService;

    @ApiOperation(value = "注册TimeWindow算子")
    @PostMapping("/timeWindow")
    public Result<String> registerTimeWindow(@RequestBody CreateTimeWindowRequest request){
        String timeWindowId = timeWindowService.registerTimeWindow(request);
        return ResultUtil.success(timeWindowId);
    }

    @ApiOperation(value = "获取指定编排对应的TimeWindow算子展示表单")
    @GetMapping("/timeWindow/combination/{combinationId}")
    public Result<TimeWindowVO> getTimeWindowDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        TimeWindowVO res = timeWindowService.getTimeWindowDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
