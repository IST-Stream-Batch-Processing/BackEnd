package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.RequestUtil;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateDataRequest;
import cn.ist.lowcoding.streamservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/data")
    public Result<String> registerData(@RequestBody CreateDataRequest request){
        dataService.registerData(request);
        return ResultUtil.success();
    }

    @PutMapping("/data")
    public Result<String> updateData(@RequestBody CreateDataRequest request){
        dataService.registerData(request);
        return ResultUtil.success();
    }

    @DeleteMapping("/data/{id}")
    public Result<String> deleteData(@PathVariable("id") String dataId){
        dataService.deleteData(RequestUtil.getUserId(), dataId);
        return ResultUtil.success();
    }

    @GetMapping("/data")
    public Result<List<Data>> getAllData(){
        List<Data> res = dataService.getAllData();
        return ResultUtil.success(res);
    }

//    @GetMapping("/data/{userId}")
//    public Result<List<Data>> getAllDataByUserId(@PathVariable String userId) {
//        List<Data> res = dataService.getAllDataByUserId(userId);
//        return ResultUtil.success(res);
//    }
//
//    @GetMapping("/data/{id}")
//    public Result<Data> getDataById(@PathVariable("id") String dataId){
//        dataService.getDataById(RequestUtil.getUserId(), dataId);
//        return ResultUtil.success();
//    }
}
