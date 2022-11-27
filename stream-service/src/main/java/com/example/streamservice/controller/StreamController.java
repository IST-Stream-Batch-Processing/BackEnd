package com.example.streamservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {

    //展示mapCounstruct
    @GetMapping("/displayMapCounstruct")
    public void display(){
        //finalType：dataSourceId对应的数据的className
        //originalType：固定为String
        //输入isSpilt：是否需要分割数据流
        //是：输入分割符delimiter
        //输入数据流中时间戳属性类型timeStampType
        //判断是否为String
        //是：输入数据流中时间戳属性格式regexFormat和在分割后字符串列表中的位置timeStampIndex（从0开始）
        //构造：展示时左侧为属性类型和属性名，右侧输入对应在数据流中的位置（从0开始）
    }
    @GetMapping("/generateMapCounstruct")
    public void generate(){
        //得到MapConstructDO


    }
}
