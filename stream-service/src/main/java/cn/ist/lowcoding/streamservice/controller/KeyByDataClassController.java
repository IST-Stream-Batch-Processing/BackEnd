package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateKeyByDataClassRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.KeyByDataClassVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.MapConstructVO;
import cn.ist.lowcoding.streamservice.service.KeyByDataClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KeyByDataClassController {
    @Autowired
    KeyByDataClassService keyByDataClassService;

    @PostMapping("/mapConstruct")
    public Result<String> registerKeyByDataClass(@RequestBody CreateKeyByDataClassRequest request){
        String mapConstructId = keyByDataClassService.registerKeyByDataClass(request);
        return ResultUtil.success(mapConstructId);
    }


    @GetMapping("/mapConstruct/combination/{combinationId}")
    public Result<KeyByDataClassVO> getKeyByDataClassDisplayByCombinationId(@PathVariable("combinationId") String combinationId){
        KeyByDataClassVO res = keyByDataClassService.getKeyByDataClassDisplayByCombinationId(combinationId);
        return ResultUtil.success(res);
    }
}
