package cn.ist.lowcoding.streamservice.pojo.dto.request;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateWindowViewCountRequest extends CreateOperatorRequest{

    private String className = "WindowViewCount";

    private String keyType;//key类型

    private String keyName;//key名称

    private List<TypeAndName> attributeList = new ArrayList<>();
}
