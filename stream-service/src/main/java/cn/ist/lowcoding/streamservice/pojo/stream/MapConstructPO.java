package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import cn.ist.lowcoding.streamservice.model.stream.MapConstructDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class MapConstructPO extends OperatorPO {

    private Boolean isSpilt;

    private String delimiter;

    private String timeStampType;

    private String regexFormat;

    private Integer timeStampIndex;

    private List<TypeAndIndex> dataList = new ArrayList<>();

    public MapConstructDO to() {
        MapConstructDO res = new MapConstructDO();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static MapConstructPO from(MapConstructDO mapConstruct) {
        MapConstructPO res = new MapConstructPO();
        BeanUtils.copyProperties(mapConstruct, res);
        return res;
    }
}