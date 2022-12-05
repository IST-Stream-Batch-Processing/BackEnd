package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.FilterDataClassOne;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class FilterDataClassOnePO extends OperatorPO {

    private String filterName;//筛选目标属性名称

    private String value;//筛选目标值

    private Boolean isRegex;//是否需要正则表达

    private String regex;//正则表达式

    @Override
    public FilterDataClassOne to() {
        FilterDataClassOne res = new FilterDataClassOne();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static FilterDataClassOnePO from(FilterDataClassOne filterDataClassOne) {
        FilterDataClassOnePO res = new FilterDataClassOnePO();
        BeanUtils.copyProperties(filterDataClassOne, res);
        return res;
    }
}
