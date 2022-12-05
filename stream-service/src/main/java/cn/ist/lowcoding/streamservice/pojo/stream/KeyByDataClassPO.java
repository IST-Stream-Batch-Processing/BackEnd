package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.model.stream.KeyByDataClass;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class KeyByDataClassPO extends OperatorPO {
    private String keyName;

    private String keyType;

    @Override
    public Operator to() {
        KeyByDataClass res = new KeyByDataClass();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static KeyByDataClassPO from(KeyByDataClass keyByDataClass) {
        KeyByDataClassPO res = new KeyByDataClassPO();
        BeanUtils.copyProperties(keyByDataClass, res);
        return res;
    }
}
