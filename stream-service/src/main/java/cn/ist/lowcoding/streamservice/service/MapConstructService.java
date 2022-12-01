package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateMapConstructRequest;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.MapConstructRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MapConstructService {
    @Autowired
    MapConstructRepo mapConstructRepo;

    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    public MapConstruct getMapConstructByCombinationId(String combinationId) {
        Optional<Combination> combination = combinationRepo.findById(combinationId);
        String dataId = combination.get().getDataId();
        Optional<Data> data = dataRepo.findById(dataId);
        String className = data.get().getClassName();

        MapConstruct mapConstruct = new MapConstruct();
        mapConstruct.setFinalType(className);
        mapConstruct.setAttributeList(data.get().getAttributeList());
        mapConstruct.setCombinationId(combinationId);

        return mapConstruct;
    }


    public String registerMapConstruct(CreateMapConstructRequest request) {
        MapConstruct mapConstruct = new MapConstruct();
        BeanUtils.copyProperties(request, mapConstruct);
        mapConstructRepo.save(mapConstruct);

        String combinationId = mapConstruct.getCombinationId();
        Combination combination = combinationRepo.findById(combinationId).get();
        System.out.println("修改前数据库中的combination：" + combination.toString());
        List<String> operatorIds = combination.getOperatorIds();
        operatorIds.add(mapConstruct.getId());

        List<String> finalTypes = combination.getFinalTypes();
        finalTypes.add(mapConstruct.getFinalType());

        combinationRepo.save(combination);

        Combination newCombination = combinationRepo.findById(combinationId).get();
        System.out.println("修改后数据库中的combination：" + newCombination.toString());

        return mapConstruct.getId();
    }
}
