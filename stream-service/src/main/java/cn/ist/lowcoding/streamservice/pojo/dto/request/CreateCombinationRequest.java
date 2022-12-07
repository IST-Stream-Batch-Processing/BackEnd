package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateCombinationRequest {
    private String dataId; // 一个编排所对应的数据源输入

    private String name; // 编排的名称
}
