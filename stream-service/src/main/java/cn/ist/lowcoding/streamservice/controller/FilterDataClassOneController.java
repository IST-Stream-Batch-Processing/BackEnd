package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.service.FilterDataClassOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterDataClassOneController {
    @Autowired
    FilterDataClassOneService filterDataClassOneService;

    @GetMapping("/mapConstruct/combination/{combinationId}")
    public Result<MapConstruct> getMapConstructDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        MapConstruct res = filterDataClassOneService.getMapConstructDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }

}
