package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateDataRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.service.MapConstructService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapConstructController {
    @Autowired
    MapConstructService mapConstructService;

    @PostMapping("/mapConstruct")
    public Result<String> registerMapConstruct(@RequestBody CreateMapConstructRequest request){
        String mapConstructId = mapConstructService.registerMapConstruct(request);
        return ResultUtil.success(mapConstructId);
    }

    @GetMapping("/mapConstruct/{combinationId}")
    public Result<MapConstruct> getMapConstructByCombinationId(@PathVariable("combinationId") String combinationId){
        MapConstruct res = mapConstructService.getMapConstructByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
