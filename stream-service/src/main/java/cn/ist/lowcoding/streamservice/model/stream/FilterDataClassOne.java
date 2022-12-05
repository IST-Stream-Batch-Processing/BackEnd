package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

import java.util.List;

@Data
public class FilterDataClassOne extends Operator{

    private String filterName;//筛选目标属性名称

    private String value;//筛选目标值

    private Boolean isRegex;//是否需要正则表达

    private String regex;//正则表达式

    public FilterDataClassOne(){
        this.setName("StreamFilterDataClassOne");
        this.generateInAndOutType();
    }
}
