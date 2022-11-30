package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.CreateDataRequest;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    DataRepo dataRepo;

    public String registerData(CreateDataRequest request) {
        //得到DataDO
        Data data = new Data();
        BeanUtils.copyProperties(request, data);

        //存入数据库
        dataRepo.save(data);
        return data.getId();
    }

    // TODO: 如何验证用户类型是否可以删除dataDO
    public void deleteData(String userId, String dataId) {
        dataRepo.deleteById(dataId);
    }

    public Data getDataById(String userId, String dataId) {
        return dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException(""));
    }

    public List<Data> getAllData() {

        return dataRepo.findAll();
    }

    public List<Data> getAllDataByUserId(String userId) {
        return null;
    }


//
//    private void setMapConstructFinalType() {
//        findTargetCombinationByOperatorId()
//
//
//    }
}
