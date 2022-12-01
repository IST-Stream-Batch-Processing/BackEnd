package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.AscendingTimestamp;
import cn.ist.lowcoding.streamservice.pojo.data.DataPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AscendingTimestampPO extends OperatorPO{

    private String typeStampName;//时间戳名

    private String unit;//时间戳单位

    public AscendingTimestamp to() {
        AscendingTimestamp res = new AscendingTimestamp();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static AscendingTimestampPO from(AscendingTimestamp ascendingTimestamp) {
        AscendingTimestampPO res = new AscendingTimestampPO();
        BeanUtils.copyProperties(ascendingTimestamp, res);
        return res;
    }
}
