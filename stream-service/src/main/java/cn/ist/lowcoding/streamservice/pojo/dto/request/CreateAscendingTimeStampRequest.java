package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateAscendingTimeStampRequest extends CreateOperatorRequest {
    private String timeStampName;

    private String unit;
}
