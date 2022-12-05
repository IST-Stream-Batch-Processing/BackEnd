package cn.ist.lowcoding.streamservice.pojo.dto.response;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.Data;

import java.util.List;

@Data
public class KeyByDataClassVO extends OperatorVO{
    private List<TypeAndName> attributeList;//注册数据对应的属性类型和名称
}
