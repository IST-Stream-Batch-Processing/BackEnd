package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateProcessValueStateRequest extends CreateOperatorRequest {
    private String keyType;
}
