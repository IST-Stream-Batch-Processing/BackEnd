package cn.ist.lowcoding.streamservice.pojo.dto;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateDataRequest {
//    private String id = ""; // 数据源 ID

    private String filePath; // 数据源文件路径（例如 UserBehavior.csv 在服务器端的存储路径）

    private String userId; // 数据源 所对应的用户 ID

    private List<String> combinations; // 数据源 所对应的流程编排 ID 列表（详见 combinationDO）

    // TODO: Application needs update


    // 数据源对应的数据
    private String className; // 设置的数据名称

    private List<TypeAndName> attributeList; // 注册数据类中属性类型与名称

    private Boolean isTimeStamp; // 是否使用时间戳,如果为是，则指定的时间戳类型必须为Long

    private String timeStampName; // 时间戳属性名称
}
