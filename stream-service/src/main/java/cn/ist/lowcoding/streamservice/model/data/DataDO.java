package cn.ist.lowcoding.streamservice.model.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataDO {
    private String id = ""; // 数据源 ID

    private String filePath = ""; // 文件路径

    private String userId = ""; // DataSource 所对应的用户 ID

    private List<String> process = new ArrayList<>(); // DataSource 所对应的编排


    // 数据源对应的数据
    private String className; // 设置的数据名称

    private List<TypeAndName> attributeList = new ArrayList<>(); // 注册数据类中属性类型与名称

    private Boolean isTimeStamp; // 是否使用时间戳,如果为是，则指定的时间戳类型必须为Long

    private String timeStampName; // 时间戳属性名称
}
