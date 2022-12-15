package cn.ist.lowcoding.streamservice.model.data;


import cn.ist.lowcoding.streamservice.model.combination.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 * DataDO
 * DataDO 用于表示 数据源 及其所对应的 数据格式
 */
@lombok.Data
public class Data {

    private String id; // 数据源 ID

    private String filePath; // 数据源文件路径（例如 UserBehavior.csv 在服务器端的存储路径）

    private String userId; // 数据源 所对应的用户 ID

    private List<String> combinationIds = new ArrayList<>(); // 数据源 所对应的流程编排 ID 列表（详见 combinationDO） TODO: 未来重构可以考虑删除该属性

    private List<Combination> combinations = new ArrayList<>();

    // 数据源对应的数据
    private String className; // 设置的数据名称

    private List<TypeAndName> attributeList; // 注册数据类中属性类型与名称

    private Boolean isTimeStamp; // 是否使用时间戳,如果为是，则指定的时间戳类型必须为Long

    private String timeStampName; // 时间戳属性名称
}
