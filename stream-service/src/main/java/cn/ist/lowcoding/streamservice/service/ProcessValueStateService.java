package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.stream.ProcessListState;
import cn.ist.lowcoding.streamservice.model.stream.ProcessValueState;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessListStateRequest;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateProcessValueStateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProcessValueStateService extends OperatorService{
    public String registerProcessValueState(CreateProcessValueStateRequest request) {
        ProcessValueState processValueState = new ProcessValueState();
        BeanUtils.copyProperties(request, processValueState);
        processValueState.generateInput();
        processValueState.generateOutput();
        operatorRepo.save(processValueState);

        registerOperatorToCombination(processValueState);

        return processValueState.getId();
    }
}
