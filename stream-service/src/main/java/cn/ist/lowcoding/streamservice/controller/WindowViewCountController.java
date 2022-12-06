package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateWindowViewCountRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.WindowViewCountVO;
import cn.ist.lowcoding.streamservice.service.WindowViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WindowViewCountController {
    @Autowired
    WindowViewCountService windowViewCountService;

    @GetMapping("/windowViewCount/combination/{combinationId}")
    public Result<WindowViewCountVO> getWindowViewCountDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        WindowViewCountVO res = windowViewCountService.getWindowViewCountDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }

    @PostMapping("/windowViewCount")
    public Result<String> registerWindowViewCount(@RequestBody CreateWindowViewCountRequest request){
        String windowViewCountId = windowViewCountService.registerWindowViewCount(request);
        return ResultUtil.success(windowViewCountId);
    }
}
