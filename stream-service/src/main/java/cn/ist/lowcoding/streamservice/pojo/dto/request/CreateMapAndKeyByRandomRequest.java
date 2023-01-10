package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateMapAndKeyByRandomRequest extends CreateOperatorRequest {
    private String keyType;
    private int randomSize;
}
