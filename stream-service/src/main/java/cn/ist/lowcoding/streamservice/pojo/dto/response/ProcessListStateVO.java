package cn.ist.lowcoding.streamservice.pojo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessListStateVO extends OperatorVO{
    private String keyType;
}