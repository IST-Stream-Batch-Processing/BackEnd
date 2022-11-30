package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.service.MapConstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapConstructController {
    @Autowired
    MapConstructService mapConstructService;

    @GetMapping("/mapConstruct/{combinationId}")
    public Result<MapConstruct> getMapConstructByCombinationId(@PathVariable("combinationId") String combinationId){
        MapConstruct res = mapConstructService.getMapConstructByCombinationId(combinationId);
//        dataService.getDataById(RequestUtil.getUserId(), dataId);
        return ResultUtil.success(res);
    }
}
