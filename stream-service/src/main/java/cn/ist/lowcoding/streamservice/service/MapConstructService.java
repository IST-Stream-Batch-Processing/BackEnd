package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MapConstructService {

//    public  getDataById(String userId, String dataId) {
//        return dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException(""));
//    }

    public MapConstruct getMapConstructByCombinationId(String combinationId) {
        return new MapConstruct();
    }
}
