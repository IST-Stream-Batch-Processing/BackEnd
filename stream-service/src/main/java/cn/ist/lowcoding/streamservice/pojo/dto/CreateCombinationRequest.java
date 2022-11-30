package cn.ist.lowcoding.streamservice.pojo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCombinationRequest {

        private String dataId; // 一个编排所对应的数据源输入

        private List<String> operatorIds; // 一个编排下所包含的 operator id

        private List<String> finalTypes; // 记录每一个算子的用户输出类型{
}
