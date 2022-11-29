package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/registerData")
    public Result<String> registerData(){
        dataService.registerData();
        return ResultUtil.success();
    }
}
