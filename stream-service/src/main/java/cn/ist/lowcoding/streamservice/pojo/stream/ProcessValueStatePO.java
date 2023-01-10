package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.model.stream.ProcessListState;
import cn.ist.lowcoding.streamservice.model.stream.ProcessValueState;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProcessValueStatePO extends OperatorPO{
    private String keyType;

    @Override
    public ProcessValueState to() {
        ProcessValueState res = new ProcessValueState();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static ProcessValueStatePO from(ProcessValueState processValueState) {
        ProcessValueStatePO res = new ProcessValueStatePO();
        BeanUtils.copyProperties(processValueState, res);
        return res;
    }
}
