package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.stream.FilterDataClassOne;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateFilterDataClassOneRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.FilterDataClassOneVO;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterDataClassOneService {
    @Autowired
    CombinationRepo combinationRepo;
    @Autowired
    DataRepo dataRepo;
    @Autowired
    OperatorRepo operatorRepo;

    public FilterDataClassOneVO getFilterDataClassOneDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).get();
        String operatorFinalType = operator.getFinalType();

        FilterDataClassOneVO filterDataClassOneVO = new FilterDataClassOneVO();
        filterDataClassOneVO.setOriginalType(operatorFinalType);
        filterDataClassOneVO.setFinalType(operatorFinalType);
        return  filterDataClassOneVO;
    }

    public String registerfilterDataClassOne(CreateFilterDataClassOneRequest request) {
        FilterDataClassOne filterDataClassOne = new FilterDataClassOne();

        BeanUtils.copyProperties(request, filterDataClassOne);
        filterDataClassOne.generateOutput();
        operatorRepo.save(filterDataClassOne);

        String combinationId = filterDataClassOne.getCombinationId();
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));

        List<String> operatorIds = combination.getOperatorIds();
        operatorIds.add(filterDataClassOne.getId());

        List<String> finalTypes = combination.getFinalTypes();
        finalTypes.add(filterDataClassOne.getFinalType());

        combinationRepo.save(combination);

        return filterDataClassOne.getId();
    }
}
