package cn.ist.lowcoding.streamservice.model.stream;

import lombok.Data;

@Data
public class MapAndKeyByRandom extends Operator{
    private int randomSize;

    public MapAndKeyByRandom() {
        this.setName("StreamMapAndKeyByRandom");
        this.generateInAndOutType();
        this.setFinalType("Tuple2<Integer, Long>,Integer");
        this.generateOutput();
    }
}
