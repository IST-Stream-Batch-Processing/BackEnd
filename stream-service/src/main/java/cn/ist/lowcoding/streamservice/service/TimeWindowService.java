package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.stream.KeyByDataClass;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeWindowService extends OperatorService{
    public TimeWindowVO getTimeWindowDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        String preName = operator.getName();
        TimeWindowVO timeWindowVO = new TimeWindowVO();
        if(preName.contains("Key")){
            String operatorFinalType = operator.getFinalType();
            timeWindowVO.setOriginalType(operatorFinalType);
            KeyByDataClass keyByDataClass = (KeyByDataClass)operator;
            String keyType = keyByDataClass.getKeyType();
            String keyName = keyByDataClass.getKeyName();
            timeWindowVO.setKeyType(keyType);
            timeWindowVO.setKeyName(keyName);
        }
        else {
            timeWindowVO.setKeyType("");
        }
        return  timeWindowVO;
    }

    public String registerTimeWindow(CreateTimeWindowRequest request) {
        TimeWindow timeWindow = new TimeWindow();
        BeanUtils.copyProperties(request, timeWindow);
        timeWindow.generateInput();
        String originalType = timeWindow.getOriginalType();
        timeWindow.setFinalType(originalType + "," + timeWindow.getKeyType() + ",TimeWindow");
        timeWindow.setOriginalType(originalType + "," + timeWindow.getKeyType());
        timeWindow.generateInput();
        timeWindow.generateOutput();
        operatorRepo.save(timeWindow);

        registerOperatorToCombination(timeWindow);

        return timeWindow.getId();
    }

}
