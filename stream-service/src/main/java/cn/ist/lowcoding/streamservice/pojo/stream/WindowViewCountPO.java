package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import cn.ist.lowcoding.streamservice.model.stream.WindowViewCount;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class WindowViewCountPO extends OperatorPO {
    private String className = "WindowViewCount";

    private String keyType;//key类型

    private String keyName;//key名称

    private List<TypeAndName> attributeList = new ArrayList<>();

    @Override
    public WindowViewCount to() {
        WindowViewCount res = new WindowViewCount();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static WindowViewCountPO from(WindowViewCount windowViewCount) {
        WindowViewCountPO res = new WindowViewCountPO();
        BeanUtils.copyProperties(windowViewCount, res);
        return res;
    }
}
