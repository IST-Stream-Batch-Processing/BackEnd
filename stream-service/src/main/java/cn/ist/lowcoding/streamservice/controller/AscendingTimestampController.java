package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimestamp;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.service.AscendingTimestampService;
import cn.ist.lowcoding.streamservice.service.MapConstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AscendingTimestampController {
    @Autowired
    AscendingTimestampService ascendingTimestampService;

    @GetMapping("/ascendingTimestamp/combination/{combinationId}")
    public Result<AscendingTimestamp> getMapConstructDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        AscendingTimestamp res = ascendingTimestampService.getAscendingTimestampServiceDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
