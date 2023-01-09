package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.stream.MapAndKeyByRandom;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateKeyByDataClassRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapAndKeyByRandomService extends OperatorService{

    public String registerMapAndKeyByRandom(CreateKeyByDataClassRequest request) {
        MapAndKeyByRandom mapAndKeyByRandom = new MapAndKeyByRandom();
        BeanUtils.copyProperties(request, mapAndKeyByRandom);
        mapAndKeyByRandom.generateInput();
        operatorRepo.save(mapAndKeyByRandom);

        registerOperatorToCombination(mapAndKeyByRandom);

        return mapAndKeyByRandom.getId();
    }
}
