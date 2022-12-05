package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateKeyByDataClassRequest extends CreateOperatorRequest {
    private String keyName;

    private String keyType;
}
