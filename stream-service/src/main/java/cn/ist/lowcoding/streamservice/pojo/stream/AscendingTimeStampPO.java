package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AscendingTimeStampPO extends OperatorPO{

    private String typeStampName;//时间戳名

    private String unit;//时间戳单位

    public AscendingTimeStamp to() {
        AscendingTimeStamp res = new AscendingTimeStamp();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static AscendingTimeStampPO from(AscendingTimeStamp ascendingTimeStamp) {
        AscendingTimeStampPO res = new AscendingTimeStampPO();
        BeanUtils.copyProperties(ascendingTimeStamp, res);
        return res;
    }
}
