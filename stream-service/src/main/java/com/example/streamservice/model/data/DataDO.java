package com.example.streamservice.model.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataDO {
    private String dataSourceId;//对应的数据源id
    private String userId;//对应的用户id
    private String className; //设置的数据名称
    private List<String> type = new ArrayList<>();
    private List<String> name = new ArrayList<>();
}
