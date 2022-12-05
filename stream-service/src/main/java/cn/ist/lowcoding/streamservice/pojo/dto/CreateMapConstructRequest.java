package cn.ist.lowcoding.streamservice.pojo.dto;

import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import lombok.Data;

import java.util.List;

@Data
public class CreateMapConstructRequest extends CreateOperatorRequest {
    private Boolean isSplit; // 是否需要分割数据流字符串

    private String delimiter; // 分隔符

    private String timeStampType; // 数据流中时间戳属性的类型

    private String regexFormat; // 数据流中时间戳属性的格式（当timeStampType为String时才需要输入）

    private Integer timeStampIndex; // 时间戳在分割后字符串列表中的位置（当timeStampType为String时才需要输入）

    private List<TypeAndIndex> dataList; // 数据属性类型与其在数据流中的对应位置

//    private List<TypeAndName> attributeList; // 注册数据类中属性类型与名称

}
