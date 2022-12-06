package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateAggregateRequest extends CreateOperatorRequest{
    private String keyType;//key数据类型
}
