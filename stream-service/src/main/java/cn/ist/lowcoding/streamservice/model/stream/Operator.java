package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

import java.util.stream.Stream;

/**
 * Operator
 * Operator
 */
@Data
public class Operator {

    private String id; // 算子 Id

    private String combinationId; // 所属 combination 的 Id

    private String name; // 属于哪种类型算子，如 StreamMapConstruct

    private String originalType; // 用户需要输入的具体类型，如 String，注册数据名（UserBehavior）

    private String finalType; // 用户需要输出的具体类型，如 String，注册窗口名（ItemViewCount）
//    private String inputId; // originalType 对应的算子 Id
//    private String outputId; // finalType 对应的算子 Id

    // 自动生成
    private String input; // inputType+"<"+originalType+inputType+">"，最终的输入类型名称

    private String output; // outputType+"<"+finalType+outputId+">"，最终的输出类型名称

    private String inputType; // 算子需要的输入类型，如 DataStream

    private String outputType; // 算子需要的输出类型，如 DataStream

    public void generateInAndOutType() {
        if (this.name.equals("StreamMapConstruct")) {
            this.inputType="DataStream";
            this.outputType="DataStream";
        }
        else if(this.name.equals("StreamAscendingTimeStamp")){
            this.inputType="DataStream";
            this.outputType="DataStream";
        }
        else if(this.name.equals("StreamFilterDataClassOne")){
            this.inputType="DataStream";
            this.outputType="DataStream";
        }
        else if(this.name.equals("StreamKeyByDataClass")){
            this.inputType="DataStream";
            this.outputType="KeyedStream";
        }
        else if(this.name.equals("StreamTimeWindow")){
            this.inputType="KeyedStream";
            this.outputType="WindowedStream";
        }
        else if(this.name.equals("WindowViewCount")){
            this.inputType="";
            this.outputType="";
        }
        else if(this.name.equals("StreamAggregate")){
            this.inputType="WindowedStream";
            this.outputType="DataStream";
        }
        else if(this.name.equals("StreamProcessListState")){
            this.inputType="DataStream";
            this.outputType="KeyedStream";
        }
    }

    public void generateInput() {
        this.input = this.inputType+"<"+this.originalType+">";
    }

    public void generateOutput() {
        this.output = this.outputType+"<"+this.finalType+">";
    }

}
