package cn.ist.lowcoding.streamservice.model.stream;

import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * MapConstruct
 * MapConstruct 负责把一条数据流转换成一个数据注册类
 */
@Data
public class MapConstruct extends Operator {

    private Boolean isSpilt; // 是否需要分割数据流字符串

    private String delimiter; // 分隔符

    private String timeStampType; // 数据流中时间戳属性的类型

    private String regexFormat; // 数据流中时间戳属性的格式

    private Integer timeStampIndex; // 时间戳在分割后字符串列表中的位置

    private List<TypeAndIndex> dataList = new ArrayList<>(); // 数据属性类型与其在数据流中的对应位置


    public MapConstruct() {
        this.setName("StreamMapConstruct");
        this.generateInAndOutType();
        this.setOriginalType("String");
        this.generateInput();

        this.setFinalType("");
        this.generateOutput();

        // final相关
        //
    }

//    private getFinal() {
//
//        x = findTargetCombinationByOperatorId(this.getCombinationId());
//
//        y = findDataByCombinationId(x.getCombinationId);
//
//        this.setFinalType(y.classname);
//
//    }
}
