package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateAscendingTimeStampRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.AscendingTimeStampVO;
import cn.ist.lowcoding.streamservice.service.AscendingTimeStampService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AscendingTimeStampController {
    @Autowired
    AscendingTimeStampService ascendingTimeStampService;

    @ApiOperation(value = "注册AscendingTimeStamp算子")
    @PostMapping("/ascendingTimeStamp")
    public Result<String> registerAscendingTimeStamp(@RequestBody CreateAscendingTimeStampRequest request) {
        String ascendingTimeStampId = ascendingTimeStampService.registerAscendingTimeStamp(request);
        return ResultUtil.success(ascendingTimeStampId);
    }

    @ApiOperation("获取指定编排对应的AscendingTimeStamp算子展示表单")
    @GetMapping("/ascendingTimeStamp/combination/{combinationId}")
    public Result<AscendingTimeStampVO> getAscendingTimeStampDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        AscendingTimeStampVO res = ascendingTimeStampService.getAscendingTimeStampServiceDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
