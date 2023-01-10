package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class ProcessValueState  extends Operator{
    private String keyType;

    public ProcessValueState(){
        this.setName("StreamProcessValueState");
        this.generateInAndOutType();
    }
}
