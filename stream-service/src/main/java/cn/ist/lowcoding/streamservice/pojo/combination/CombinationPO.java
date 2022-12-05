package cn.ist.lowcoding.streamservice.pojo.combination;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CombinationPO {
    @Id
    private String id; // 编排 ID

    private String dataId; // 一个编排所对应的数据源输入

    private List<String> operatorIds; // 一个编排下所包含的 operator id

    private List<String> finalTypes; // 记录每一个算子的用户输出类型

    public Combination to() {
        Combination res = new Combination();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static CombinationPO from(Combination combination) {
        CombinationPO res = new CombinationPO();
        BeanUtils.copyProperties(combination, res);
        return res;
    }
}
