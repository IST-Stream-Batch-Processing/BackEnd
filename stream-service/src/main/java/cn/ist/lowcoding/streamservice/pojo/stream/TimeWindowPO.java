package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class TimeWindowPO extends OperatorPO {
    private Boolean isSlide;

    private String lengthUnit;

    private String length;

    private String intervalUnit;

    private String interval;

    private String keyType;

    private String keyName;

    @Override
    public TimeWindow to() {
        TimeWindow res = new TimeWindow();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static TimeWindowPO from(TimeWindow timeWindow) {
        TimeWindowPO res = new TimeWindowPO();
        BeanUtils.copyProperties(timeWindow, res);
        return res;
    }
}
