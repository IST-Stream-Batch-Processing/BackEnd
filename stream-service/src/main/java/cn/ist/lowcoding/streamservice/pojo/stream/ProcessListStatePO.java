package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.ProcessListState;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProcessListStatePO extends OperatorPO{
    private String keyType;

    private Boolean isTop;//是否全部输出前N

    private int topSize;

    private Boolean isSort;//是否排序

    private Boolean isDescending;//是否从大到小排

    @Override
    public ProcessListState to() {
        ProcessListState res = new ProcessListState();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static ProcessListStatePO from(ProcessListState processListState) {
        ProcessListStatePO res = new ProcessListStatePO();
        BeanUtils.copyProperties(processListState, res);
        return res;
    }
}
