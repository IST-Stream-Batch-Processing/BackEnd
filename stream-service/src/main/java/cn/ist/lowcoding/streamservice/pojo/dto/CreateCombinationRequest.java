package cn.ist.lowcoding.streamservice.pojo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCombinationRequest {
    private String dataId; // 一个编排所对应的数据源输入
}
