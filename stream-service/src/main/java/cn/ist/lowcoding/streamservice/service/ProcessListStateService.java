package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.stream.KeyByDataClass;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.model.stream.ProcessListState;
import cn.ist.lowcoding.streamservice.model.stream.TimeWindow;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessListStateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.response.ProcessListStateVO;
import cn.ist.lowcoding.streamservice.pojo.dto.response.TimeWindowVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessListStateService extends OperatorService{
    public ProcessListStateVO getProcessListStateDisplayByCombinationId(String combinationId) {
        Combination combination = combinationRepo.findById(combinationId).orElseThrow(() -> new RuntimeException("找不到对应的编排"));
        List<String> operatorIds = combination.getOperatorIds();
        String lastOperatorId = operatorIds.get(operatorIds.size()-1);
        Operator operator = operatorRepo.findById(lastOperatorId).orElseThrow(() -> new RuntimeException("找不到对应的算子"));
        String preName = operator.getName();

        ProcessListStateVO processListStateVO = new ProcessListStateVO();
        if(preName.contains("Key")){
            String operatorFinalType = operator.getFinalType();
            processListStateVO.setOriginalType(operatorFinalType);
            processListStateVO.setFinalType("String");
            //TODO:抽取Key
            KeyByDataClass keyByDataClass = (KeyByDataClass)operator;
            String keyType = keyByDataClass.getKeyType();
            processListStateVO.setKeyType(keyType);
        }
        else {
            processListStateVO.setKeyType("");
        }


        return  processListStateVO;

    }

    public String registerProcessListState(CreateProcessListStateRequest request) {
        ProcessListState processListState = new ProcessListState();
        BeanUtils.copyProperties(request, processListState);
        processListState.generateInput();
        processListState.setFinalType("String");
        processListState.generateOutput();
        operatorRepo.save(processListState);

        registerOperatorToCombination(processListState);

        return processListState.getId();
    }
}
