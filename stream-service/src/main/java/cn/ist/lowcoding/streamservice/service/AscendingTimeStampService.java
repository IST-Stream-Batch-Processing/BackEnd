package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimeStamp;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AscendingTimeStampService {

    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    OperatorRepo operatorRepo;

    public AscendingTimeStamp getAscendingTimeStampServiceDisplayByCombinationId(String combinationId) {
        AscendingTimeStamp ascendingTimeStamp = new AscendingTimeStamp();
        Optional<Combination> combination = combinationRepo.findById(combinationId);
        String dataId = combination.get().getDataId();

        Optional<Data> data = dataRepo.findById(dataId);
        String timeStampName = data.get().getTimeStampName();
        ascendingTimeStamp.setTimeStampName(timeStampName);
        //得到上一个的输出
        List<String> operatorIds = combination.get().getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).get();
        String operatorFinalType = operator.getFinalType();

        ascendingTimeStamp.setOriginalType(operatorFinalType);
        ascendingTimeStamp.setFinalType(operatorFinalType);
        ascendingTimeStamp.generateInput();
        ascendingTimeStamp.generateOutput();

        return ascendingTimeStamp;
    }
}
