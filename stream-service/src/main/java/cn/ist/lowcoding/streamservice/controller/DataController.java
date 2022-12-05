package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.RequestUtil;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateDataRequest;
import cn.ist.lowcoding.streamservice.service.DataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @ApiOperation(value = "注册数据源")
    @PostMapping("/data")
    public Result<String> registerData(@RequestBody CreateDataRequest request){
        String dataId = dataService.registerData(request);
        return ResultUtil.success(dataId);
    }

    @ApiOperation(value = "获取所有数据源")
    @GetMapping("/data")
    public Result<List<Data>> getAllData(){
        List<Data> res = dataService.getAllData();
        return ResultUtil.success(res);
    }

    @ApiOperation(value = "获取指定的数据源")
    @GetMapping("/data/{id}")
    public Result<Data> getDataById(@PathVariable("id") String dataId){
        Data res = dataService.getDataById(RequestUtil.getUserId(), dataId);
        return ResultUtil.success(res);
    }

    @PutMapping("/data")
    public Result<String> updateData(@RequestBody CreateDataRequest request){
        //未实现
        dataService.updateData(request);
        return ResultUtil.success();
    }

    @DeleteMapping("/data/{id}")
    public Result<String> deleteData(@PathVariable("id") String dataId){
        // 未实现
        dataService.deleteData(RequestUtil.getUserId(), dataId);
        return ResultUtil.success();
    }

    @GetMapping("/data/user/{userId}")
    public Result<List<Data>> getAllDataByUserId(@PathVariable String userId) {
        // 未实现
        List<Data> res = dataService.getAllDataByUserId(userId);
        return ResultUtil.success(res);
    }
}
