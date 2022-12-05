package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import org.springframework.stereotype.Service;

@Service
public class FilterDataClassOneService {
    public MapConstruct getMapConstructDisplayByCombinationId(String combinationId) {
//        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
//        String dataId = combination.getDataId();
//
//        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
//        String className = data.getClassName();
//
//        FilterDataClassOneVO filterDataClassOneVo = new FilterDataClassOneVO();
        return null;

    }
}
