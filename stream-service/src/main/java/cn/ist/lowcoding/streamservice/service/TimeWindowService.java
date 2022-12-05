package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateTimeWindowRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.FilterDataClassOneVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeWindowService extends OperatorService{
    public String registerTimeWindow(CreateTimeWindowRequest request) {
        return null;
    }

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
            timeWindowVO.setKey(true);
        }
        else {
            timeWindowVO.setKey(false);
        }
        return  timeWindowVO;
    }
}
