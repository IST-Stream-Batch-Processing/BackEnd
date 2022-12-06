package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class Aggregate extends Operator{
    private String keyType;//key数据类型

    public Aggregate(){
        this.setName("StreamAggregate");
        this.generateInAndOutType();
    }
}
