package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class OperatorPO {
    @Id
    private String id;

    private String combinationId;

    private String name;

    private String originalType;

    private String finalType;

    private String input;

    private String output;

    private String inputType;

    private String outputType;

    public abstract Operator to();
}