package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.service.AscendingTimeStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AscendingTimeStampController {
    @Autowired
    AscendingTimeStampService ascendingTimeStampService;

    @GetMapping("/ascendingTimeStamp/combination/{combinationId}")
    public Result<AscendingTimeStamp> getMapConstructDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        AscendingTimeStamp res = ascendingTimeStampService.getAscendingTimeStampServiceDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
