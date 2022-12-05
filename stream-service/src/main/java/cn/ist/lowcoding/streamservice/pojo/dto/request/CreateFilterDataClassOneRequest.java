package cn.ist.lowcoding.streamservice.pojo.dto.request;

import lombok.Data;

@Data
public class CreateFilterDataClassOneRequest extends CreateOperatorRequest{

    private String filterName;//筛选目标属性名称

    private String value;//筛选目标值

    private Boolean isRegex;//是否需要正则表达

    private String regex;//正则表达式
}
