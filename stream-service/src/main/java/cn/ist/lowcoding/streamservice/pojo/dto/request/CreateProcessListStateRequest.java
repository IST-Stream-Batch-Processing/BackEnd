package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateProcessListStateRequest extends CreateOperatorRequest {
    private String keyType;

    private Boolean isTop;//是否全部输出前N

    private Integer topSize;

    private Boolean isSort;//是否排序

    private Boolean isDescending;//是否从大到小排
}
