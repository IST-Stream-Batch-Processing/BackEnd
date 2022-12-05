package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
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
public class FilterDataClassOneService extends OperatorService {
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
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        String operatorFinalType = operator.getFinalType();

        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        List<TypeAndName> attributeList = data.getAttributeList();

        FilterDataClassOneVO filterDataClassOneVO = new FilterDataClassOneVO();
        filterDataClassOneVO.setOriginalType(operatorFinalType);
        filterDataClassOneVO.setFinalType(operatorFinalType);
        filterDataClassOneVO.setAttributeList(attributeList);
        return  filterDataClassOneVO;
    }

    public String registerFilterDataClassOne(CreateFilterDataClassOneRequest request) {
        FilterDataClassOne filterDataClassOne = new FilterDataClassOne();
        BeanUtils.copyProperties(request, filterDataClassOne);
        filterDataClassOne.generateInput();
        filterDataClassOne.generateOutput();
        operatorRepo.save(filterDataClassOne);

        registerOperatorToCombination(filterDataClassOne);

        return filterDataClassOne.getId();
    }
}
