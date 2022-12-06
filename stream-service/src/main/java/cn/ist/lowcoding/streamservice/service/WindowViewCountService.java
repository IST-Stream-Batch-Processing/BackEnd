package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.model.stream.KeyByDataClass;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import cn.ist.lowcoding.streamservice.model.stream.WindowViewCount;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateWindowViewCountRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.WindowViewCountVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WindowViewCountService extends OperatorService{
    public WindowViewCountVO getWindowViewCountDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        TimeWindow timeWindow = (TimeWindow)operator;
        String keyType = timeWindow.getKeyType();
        //String keyName = timeWindow.getKeyName();

        WindowViewCountVO windowViewCountVO = new WindowViewCountVO();
        windowViewCountVO.setKeyType(keyType);
//        windowViewCountVO.setKeyName();
        List<TypeAndName> attributeList = new ArrayList<>();
        TypeAndName typeAndName = new TypeAndName();
        typeAndName.setType(keyType);
//        typeAndName.setName(keyName);
        attributeList.add(typeAndName);
        TypeAndName typeAndName1 = new TypeAndName();
        typeAndName1.setType("Long");
        typeAndName1.setName("windowEnd");
        attributeList.add(typeAndName1);
        TypeAndName typeAndName2 = new TypeAndName();
        typeAndName2.setType("Long");
        typeAndName2.setName("count");
        attributeList.add(typeAndName2);
        windowViewCountVO.setAttributeList(attributeList);

        return  windowViewCountVO;

    }

    public String registerWindowViewCount(CreateWindowViewCountRequest request) {
        WindowViewCount windowViewCount = new WindowViewCount();
        BeanUtils.copyProperties(request, windowViewCount);
        windowViewCount.generateInput();

        operatorRepo.save(windowViewCount);

        registerOperatorToCombination(windowViewCount);

        return windowViewCount.getId();
    }
}
