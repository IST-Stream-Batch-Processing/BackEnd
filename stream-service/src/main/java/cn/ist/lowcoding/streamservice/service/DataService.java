package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.freemaker.FMDataModel;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    DataRepo dataRepo;

    public void registerData() {
        //得到DataDO
        //存入数据库
        //用DataDO生成FMDataModel
        FMDataModel fmDataModel = new FMDataModel();
//        fmDataModel.setClassName();
//        fmDataModel.setType();
//        fmDataModel.setName();
//        fmDataModel.setDataId();
//        fmDataModel.generate();
    }
}
