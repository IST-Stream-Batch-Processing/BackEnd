package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.freemaker.FMDataModel;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateDataRequest;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    DataRepo dataRepo;

    public void registerData(CreateDataRequest request) {
        //得到DataDO
        //存入数据库
        //用DataDO生成FMDataModel
        Data data = new Data();
        BeanUtils.copyProperties(request, data);
        FMDataModel fmDataModel = new FMDataModel();
        BeanUtils.copyProperties(data, fmDataModel);
        fmDataModel.generate();
    }

    // TODO: 如何验证用户类型是否可以删除dataDO
    public void deleteData(String userId, String dataId) {
        dataRepo.deleteById(dataId);
    }

    public Data getDataById(String userId, String dataId) {
        return dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException(""));
    }


//
//    private void setMapConstructFinalType() {
//        findTargetCombinationByOperatorId()
//
//
//    }
}