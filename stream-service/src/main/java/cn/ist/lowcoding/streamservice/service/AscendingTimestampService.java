package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.stream.AscendingTimestamp;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.apache.zookeeper.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AscendingTimestampService {

    @Autowired
    CombinationRepo combinationRepo;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    OperatorRepo operatorRepo;

    public AscendingTimestamp getAscendingTimestampServiceDisplayByCombinationId(String combinationId) {
        AscendingTimestamp ascendingTimestamp = new AscendingTimestamp();
        Optional<Combination> combination = combinationRepo.findById(combinationId);
        String dataId = combination.get().getDataId();

        Optional<Data> data = dataRepo.findById(dataId);
        String timeStampName = data.get().getTimeStampName();
        ascendingTimestamp.setTimeStampName(timeStampName);
        //得到上一个的输出
        List<String> operatorIds = combination.get().getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).get();
        String operatorFinalType = operator.getFinalType();

        ascendingTimestamp.setOriginalType(operatorFinalType);
        ascendingTimestamp.setFinalType(operatorFinalType);
        ascendingTimestamp.generateInput();
        ascendingTimestamp.generateOutput();

        return ascendingTimestamp;
    }
}
