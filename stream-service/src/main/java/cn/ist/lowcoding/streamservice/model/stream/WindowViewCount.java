package cn.ist.lowcoding.streamservice.model.stream;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WindowViewCount extends Operator {

    private String className;

    private Boolean isKey;//是否有key
    private String keyType;//key类型
    private String keyName;//key名称
    private List<TypeAndName> attributes = new ArrayList<>();
}