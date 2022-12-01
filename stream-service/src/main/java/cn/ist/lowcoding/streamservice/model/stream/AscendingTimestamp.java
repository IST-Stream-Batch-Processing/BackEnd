package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class AscendingTimestamp extends Operator{

    private String typeStampName;//时间戳名(下拉列表可选择)

    private String unit;//时间戳单位（下拉列表可选择，ms,s,min）

    public AscendingTimestamp(){

    }

}
