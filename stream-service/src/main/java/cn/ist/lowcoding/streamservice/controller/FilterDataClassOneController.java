package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateFilterDataClassOneRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.FilterDataClassOneVO;
import cn.ist.lowcoding.streamservice.service.FilterDataClassOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FilterDataClassOneController {
    @Autowired
    FilterDataClassOneService filterDataClassOneService;

    @GetMapping("/filterDataClassOne/combination/{combinationId}")
    public Result<FilterDataClassOneVO> getFilterDataClassOneDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        FilterDataClassOneVO res = filterDataClassOneService.getFilterDataClassOneDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }

    @PostMapping("/filterDataClassOne")
    public Result<String> registerFilterDataClassOne(@RequestBody CreateFilterDataClassOneRequest request){
        String mapConstructId = filterDataClassOneService.registerFilterDataClassOne(request);
        return ResultUtil.success(mapConstructId);
    }

}
