package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateProcessListStateRequest extends CreateOperatorRequest {
    private String keyType;

    private Boolean isTop;//是否全部输出前N

    private int topSize;

    private Boolean isSort;//是否排序

    private Boolean isSmaller;//是否从大到小排
}
