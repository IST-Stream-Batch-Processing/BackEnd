package cn.ist.lowcoding.streamservice.model.combination;


import cn.ist.lowcoding.streamservice.model.stream.Operator;
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

    private String name; // 编排的名称

    private String dataId; // 一个编排所对应的数据源输入

    private List<String> operatorIds = new ArrayList<>(); // 一个编排下所包含的 operator id  TODO: 未来重构可以考虑删除该属性

    private List<Operator> operators = new ArrayList<>(); // 一个编排下所包含的operator

    private List<String> finalTypes = new ArrayList<>(); // 记录每一个算子的用户输出类型
}
