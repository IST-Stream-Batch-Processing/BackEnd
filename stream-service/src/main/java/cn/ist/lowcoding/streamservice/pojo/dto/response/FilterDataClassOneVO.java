package cn.ist.lowcoding.streamservice.pojo.dto.response;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDataClassOneVO extends OperatorVO{
    private List<TypeAndName> attributeList = new ArrayList<>();
}
