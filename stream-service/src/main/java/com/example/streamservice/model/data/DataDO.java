package com.example.streamservice.model.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataDO {
    private String dataSourceId;//对应的数据源id
    private String userId;//对应的用户id
    private String dataId;//自己的id标识
    private String className; //设置的数据名称
    private List<TypeAndName> attributeList = new ArrayList<>();//注册数据类中属性类型与名称
}
