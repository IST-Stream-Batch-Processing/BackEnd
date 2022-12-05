package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateOperatorRequest {
    private String combinationId; // 所属 combination 的 Id

    private String originalType; // 用户需要输入的具体类型，如 String，注册数据名（UserBehavior）

    private String finalType; // 用户需要输出的具体类型，如 String，注册窗口名（ItemViewCount）
//    private String inputId; // originalType 对应的算子 Id
//    private String outputId; // finalType 对应的算子 Id

    // 自动生成
//    private String input; // inputType+"<"+originalType+">"，最终的输入类型名称
//
//    private String output; // outputType+"<"+finalType+">"，最终的输出类型名称
//
//    private String inputType; // 算子需要的输入类型，如 DataStream
//
//    private String outputType; // 算子需要的输出类型，如 DataStream

}
