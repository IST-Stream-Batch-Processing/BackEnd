package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateAggregateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.AggregateVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.service.AggregateService;
import cn.ist.lowcoding.streamservice.service.TimeWindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AggregateController {
    @Autowired
    AggregateService aggregateService;

    @GetMapping("/aggregate/combination/{combinationId}")
    public Result<AggregateVO> getAggregateDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        AggregateVO res = aggregateService.getAggregateDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }

    @PostMapping("/aggregate")
    public Result<String> registerAggregate(@RequestBody CreateAggregateRequest request){
        String aggregateId = aggregateService.registerAggregate(request);
        return ResultUtil.success(aggregateId);
    }
}
