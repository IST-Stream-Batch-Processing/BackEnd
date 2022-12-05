package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.MapConstructVO;
import cn.ist.lowcoding.streamservice.service.MapConstructService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapConstructController {
    @Autowired
    MapConstructService mapConstructService;

    @ApiOperation(value = "注册")
    @PostMapping("/mapConstruct")
    public Result<String> registerMapConstruct(@RequestBody CreateMapConstructRequest request){
        String mapConstructId = mapConstructService.registerMapConstruct(request);
        return ResultUtil.success(mapConstructId);
    }

//    @DeleteMapping("/mapConstruct/{id}")
//    public Result<String> deleteMapConstructById(@PathVariable String mapConstructId) {
//        mapConstructService.deleteOperatorById(mapConstructId);
//        return ResultUtil.success();
//    }


    // TODO: 删除方法未实现
    // TODO: 更新方法待定

    @ApiOperation(value = "获取指定编排对应的MapConstruct算子展示表单")
    @GetMapping("/mapConstruct/combination/{combinationId}")
    public Result<MapConstructVO> getMapConstructDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        MapConstructVO res = mapConstructService.getMapConstructDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
