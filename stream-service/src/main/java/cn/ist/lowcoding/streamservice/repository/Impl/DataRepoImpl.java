package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.data.DataPO;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataRepoImpl extends AbstractBaseRepositoryImpl<Data, DataPO> implements DataRepo {
    protected DataRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected Data fromPO(DataPO dataPO) {
        return dataPO.to();
    }

    @Override
    protected DataPO toPO(Data data) {
        return DataPO.from(data);
    }
//
//    protected Combination findCombinationByOperatorId(String operatorId) {xxxxx}
//
//    protected Data findDataByCombinationId(String combinationId) {xxxxx}
}
