package cn.ist.lowcoding.streamservice.pojo.dto;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateDataRequest {
    private String id;//对应的数据源id
    private String userId;//对应的用户id
    private String className; //设置的数据名称
    private List<TypeAndName> attributeList = new ArrayList<>();//注册数据类中属性类型与名称
    private Boolean isTimeStamp;//是否使用时间戳,如果为是，则指定的时间戳类型必须为Long
    private String timeStampName;//时间戳属性名称
}
