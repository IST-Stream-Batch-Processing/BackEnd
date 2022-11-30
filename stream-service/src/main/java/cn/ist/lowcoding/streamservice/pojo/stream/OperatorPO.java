package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

@Data
public class OperatorPO {
    @Id
    private String operatorId;

    private String name;

    private String originalType;

    private String finalType;

    private String inputId;

    private String outputId;

    public Operator to() {
        Operator res = new Operator();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static OperatorPO from(Operator operator) {
        OperatorPO res = new OperatorPO();
        BeanUtils.copyProperties(operator, res);
        return res;
    }
}