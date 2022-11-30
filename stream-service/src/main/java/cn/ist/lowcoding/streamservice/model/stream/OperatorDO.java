package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class OperatorDO {
    private String operatorId;//算子标识：建议userId+processId
    private String name;//属于哪种类型算子，如StreamMapConStruct
    private String originalType; //输入具体类型，如String，注册数据名（UserBehavior）
    private String finalType; //输出具体类型，如String，注册窗口名（ItemViewCount）
    private String inputId; //originalType对应的注册数据Id
    private String outputId; //finalType对应的注册数据Id

    //自动生成
    private String input;//inputType+"<"+originalType+inputType+">"
    private String output;//outputType+"<"+finalType+outputId+">"
    private String inputType; //输入类型，如DataStream
    private String outputType; //输出类型，如DataStream
    //根据name
    public void generateInAndOutType(){
        if(this.name.equals("StreamMapConStruct")){
            this.inputType="DataStream";
            this.outputType="DataStream";
        }
    }
    public void generateInput(){
        this.input = this.inputType+"<"+this.originalType+this.inputId+">";
    }
    public void generateOutput(){
        this.output = this.outputType+"<"+this.finalType+this.outputId+">";
    }

}
