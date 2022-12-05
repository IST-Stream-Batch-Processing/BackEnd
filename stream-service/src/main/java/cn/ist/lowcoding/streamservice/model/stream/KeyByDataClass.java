package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;
import org.apache.zookeeper.Op;

import java.util.List;

@Data
public class KeyByDataClass extends Operator {

    private List<String> attributeList;//注册数据对应的属性类型和名称

    private String keyName;//key对应的属性名称

    private String keyType;//key对应的属性类型

    public KeyByDataClass(){
        this.setName("StreamKeyByDataClass");
        this.generateInAndOutType();
    }
}
