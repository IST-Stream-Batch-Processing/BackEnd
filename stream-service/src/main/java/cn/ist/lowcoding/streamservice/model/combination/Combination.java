package cn.ist.lowcoding.streamservice.model.combination;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination
 * Combination 用于表示 编排 信息
 * 编排是指一系列算子组成的序列
 */
@Data
public class Combination {
    private String id; // 编排 ID

    private String dataId; // 一个编排所对应的数据源输入

    private List<String> operatorIds; // 一个编排下所包含的 operator id

    private List<String> finalTypes; // 记录每一个算子的用户输出类型
}
