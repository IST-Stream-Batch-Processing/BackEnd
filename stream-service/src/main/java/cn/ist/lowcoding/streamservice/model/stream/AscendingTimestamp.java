package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class AscendingTimestamp extends Operator{

    private String timeStampName;//时间戳名，后端给

    private String unit;//时间戳单位

    public AscendingTimestamp(){
        this.setName("StreamAscendingTimestamp");
        this.generateInAndOutType();
    }
}
