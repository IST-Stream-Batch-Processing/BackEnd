package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateTimeWindowRequest extends CreateOperatorRequest{
    // CreateOperatorRequest中的finalType可以为空

    private Boolean isSlide;

    private String lengthUnit;

    private String length;

    private String intervalUnit;

    private String interval;

    private String keyType;
}
