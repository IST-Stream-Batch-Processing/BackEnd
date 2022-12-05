package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateAscendingTimeStampRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.AscendingTimeStampVO;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AscendingTimeStampService extends OperatorService {

    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    OperatorRepo operatorRepo;

    public AscendingTimeStampVO getAscendingTimeStampServiceDisplayByCombinationId(String combinationId) {
        AscendingTimeStampVO ascendingTimeStampVO = new AscendingTimeStampVO();
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        String dataId = combination.getDataId();
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));

        String timeStampName = data.getTimeStampName();
        ascendingTimeStampVO.setTimeStampName(timeStampName);

        //得到上一个算子的输出
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        String operatorFinalType = operator.getFinalType();

        ascendingTimeStampVO.setOriginalType(operatorFinalType);
        ascendingTimeStampVO.setFinalType(operatorFinalType);

        return ascendingTimeStampVO;
    }

    public String registerAscendingTimeStamp(CreateAscendingTimeStampRequest request) {
        AscendingTimeStamp ascendingTimeStamp = new AscendingTimeStamp();
        BeanUtils.copyProperties(request, ascendingTimeStamp);
        ascendingTimeStamp.generateInput();
        ascendingTimeStamp.generateOutput();
        operatorRepo.save(ascendingTimeStamp);

        registerOperatorToCombination(ascendingTimeStamp);

        return ascendingTimeStamp.getId();
    }
}
