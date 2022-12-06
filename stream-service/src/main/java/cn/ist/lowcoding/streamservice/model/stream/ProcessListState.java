package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class ProcessListState extends Operator{
    private String keyType;

    private Boolean isTop;//是否全部输出前N

    private int topSize;

    private Boolean isSort;//是否排序

    private Boolean isDescending;//是否从大到小排

    public ProcessListState(){
        this.setName("StreamProcessListState");
        this.generateInAndOutType();
    }
}
