package cn.ist.lowcoding.streamservice.pojo.stream;

import cn.ist.lowcoding.streamservice.model.stream.MapAndKeyByRandom;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MapAndKeyByRandomPO extends OperatorPO{
    private int randomSize;

    @Override
    public MapAndKeyByRandom to() {
        MapAndKeyByRandom res = new MapAndKeyByRandom();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static MapAndKeyByRandomPO from(MapAndKeyByRandom mapAndKeyByRandom) {
        MapAndKeyByRandomPO res = new MapAndKeyByRandomPO();
        BeanUtils.copyProperties(mapAndKeyByRandom, res);
        return res;
    }
}
