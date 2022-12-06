package cn.ist.lowcoding.streamservice.pojo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeWindowVO extends OperatorVO{
    private String keyType;

    private String keyName;
}
