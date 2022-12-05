package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.MapConstructVO;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapConstructService extends OperatorService {

    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    public MapConstructVO getMapConstructDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        String dataId = combination.getDataId();

        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        String className = data.getClassName();
        boolean isTimeStamp = data.getIsTimeStamp();

        MapConstructVO mapConstructVO = new MapConstructVO();
        mapConstructVO.setTimeStamp(isTimeStamp);
        mapConstructVO.setOriginalType("String");
        mapConstructVO.setFinalType(className);
        mapConstructVO.setAttributeList(data.getAttributeList());

        return mapConstructVO;
    }


    public String registerMapConstruct(CreateMapConstructRequest request) {
        MapConstruct mapConstruct = new MapConstruct();
        BeanUtils.copyProperties(request, mapConstruct);
        mapConstruct.generateOutput();
        operatorRepo.save(mapConstruct);

        registerOperatorToCombination(mapConstruct);

        return mapConstruct.getId();
    }
}
