package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;
import org.apache.zookeeper.Op;

@Data
public class TimeWindow extends Operator {
    private String keyType;//key对应的属性类型

    private Boolean isSlide;//是否为滑动窗口

    private String lengthUnit;//窗口长度单位：day，hour，mintue，second，millisecond

    private String length;//窗口长度

    private String intervalUnit;//滑动窗口间隔单位

    private String interval;//间隔长度

    public TimeWindow(){
        this.setName("StreamTimeWindow");
        this.generateInAndOutType();
    }
}
