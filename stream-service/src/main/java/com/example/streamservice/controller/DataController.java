package com.example.streamservice.controller;

import com.example.streamservice.freemaker.FMDataModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @PostMapping("/registerData")
    public void registerData(){
        //得到DataDO
        //存入数据库
        //用DataDO生成FMDataModel
        FMDataModel fmDataModel = new FMDataModel();
//        fmDataModel.setClassName();
//        fmDataModel.setType();
//        fmDataModel.setName();
//        fmDataModel.setUserId();
//        fmDataModel.generate();
    }
}
