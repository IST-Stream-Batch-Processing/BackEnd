package cn.ist.lowcoding.streamservice.pojo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateVO extends OperatorVO{
    private String keyType;//key数据类型
}
