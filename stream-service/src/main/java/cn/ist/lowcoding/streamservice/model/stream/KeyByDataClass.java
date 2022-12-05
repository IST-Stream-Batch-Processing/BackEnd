package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;
import org.apache.zookeeper.Op;

import java.util.List;

@Data
public class KeyByDataClass extends Operator {

    private String keyName;//key对应的属性名称

    private String keyType;//key对应的属性类型

    public KeyByDataClass(){
        this.setName("StreamKeyByDataClass");
        this.generateInAndOutType();
    }
}
