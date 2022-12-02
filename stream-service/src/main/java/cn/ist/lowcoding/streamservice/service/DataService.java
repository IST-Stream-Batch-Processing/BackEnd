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


    public void deleteData(String userId, String dataId) {
        // TODO: 如何验证用户类型是否可以删除dataDO

        //删除所有与data相关的combination、operator
        dataRepo.deleteById(dataId);
    }

    public Data getDataById(String userId, String dataId) {
        return dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("getDataById()抛出的异常"));
    }

    public List<Data> getAllData() {

        return dataRepo.findAll();
    }

    public List<Data> getAllDataByUserId(String userId) {
        //从getAllData()获取所有Data，再遍历寻找相同的userId
        return null;
    }

    public void updateData(CreateDataRequest request) {
    }

}
