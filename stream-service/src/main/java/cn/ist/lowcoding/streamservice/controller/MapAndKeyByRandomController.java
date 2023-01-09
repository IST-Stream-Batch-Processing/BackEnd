package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateKeyByDataClassRequest;
import cn.ist.lowcoding.streamservice.service.KeyByDataClassService;
import cn.ist.lowcoding.streamservice.service.MapAndKeyByRandomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapAndKeyByRandomController {
    @Autowired
    MapAndKeyByRandomService mapAndKeyByRandomService;

    @ApiOperation(value = "注册MapAndKeyByRandom算子")
    @PostMapping("/mapAndKeyByRandom")
    public Result<String> registerMapAndKeyByRandom(@RequestBody CreateKeyByDataClassRequest request){
        String mapAndKeyByRandomId = mapAndKeyByRandomService.registerMapAndKeyByRandom(request);
        return ResultUtil.success(mapAndKeyByRandomId);
    }
}
