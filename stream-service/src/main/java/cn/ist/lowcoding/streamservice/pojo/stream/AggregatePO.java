package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.Aggregate;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AggregatePO extends OperatorPO{
    private String keyType;//key数据类型

    @Override
    public Aggregate to() {
        Aggregate res = new Aggregate();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static AggregatePO from(Aggregate aggregate) {
        AggregatePO res = new AggregatePO();
        BeanUtils.copyProperties(aggregate, res);
        return res;
    }
}
