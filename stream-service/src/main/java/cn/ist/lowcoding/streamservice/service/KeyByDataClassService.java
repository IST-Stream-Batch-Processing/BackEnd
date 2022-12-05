package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.model.stream.KeyByDataClass;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateKeyByDataClassRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.FilterDataClassOneVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.KeyByDataClassVO;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyByDataClassService extends OperatorService {
    @Autowired
    CombinationRepo combinationRepo;
    @Autowired
    OperatorRepo operatorRepo;
    @Autowired
    DataRepo dataRepo;

    public String registerKeyByDataClass(CreateKeyByDataClassRequest request) {
        KeyByDataClass keyByDataClass = new KeyByDataClass();
        BeanUtils.copyProperties(request, keyByDataClass);
        keyByDataClass.generateInput();
        String originalType = keyByDataClass.getOriginalType();
        String keyType = keyByDataClass.getKeyType();

        keyByDataClass.setFinalType(originalType + "," + keyType);
        keyByDataClass.generateOutput();
        operatorRepo.save(keyByDataClass);

        registerOperatorToCombination(keyByDataClass);

        return keyByDataClass.getId();
    }

    public KeyByDataClassVO getKeyByDataClassDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        String operatorFinalType = operator.getFinalType();
        String dataId = combination.getDataId();

        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        List<TypeAndName> attributeList = data.getAttributeList();

        KeyByDataClassVO keyByDataClassVO = new KeyByDataClassVO();
        keyByDataClassVO.setOriginalType(operatorFinalType);
        keyByDataClassVO.setFinalType(operatorFinalType);
        keyByDataClassVO.setAttributeList(attributeList);

        return  keyByDataClassVO;
    }
}
