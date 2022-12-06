package cn.ist.lowcoding.streamservice.pojo.dto.response;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WindowViewCountVO extends OperatorVO{

    private String className = "WindowViewCount";

    private String keyType;//key类型

    private String keyName;//key名称

    private List<TypeAndName> attributeList = new ArrayList<>();
}
