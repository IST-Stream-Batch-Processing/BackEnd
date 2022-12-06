package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.stream.*;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateAggregateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.AggregateVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregateService extends OperatorService{
    public AggregateVO getAggregateDisplayByCombinationId(String combinationId) {

        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        WindowViewCount windowViewCount = (WindowViewCount)operator;
        String keyType = windowViewCount.getKeyType();
        String className = windowViewCount.getClassName();
        String finalType = windowViewCount.getFinalType();
        AggregateVO aggregateVO = new AggregateVO();
        aggregateVO.setKeyType(keyType);
        aggregateVO.setOriginalType(finalType);
        aggregateVO.setFinalType(className);

        return  aggregateVO;
    }

    public String registerAggregate(CreateAggregateRequest request) {
        Aggregate aggregate = new Aggregate();
        BeanUtils.copyProperties(request, aggregate);
        aggregate.generateInput();
        aggregate.generateOutput();
        operatorRepo.save(aggregate);

        registerOperatorToCombination(aggregate);

        return aggregate.getId();
    }
}
