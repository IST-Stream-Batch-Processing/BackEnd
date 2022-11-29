package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.data.DataDO;
import cn.ist.lowcoding.streamservice.pojo.data.DataPO;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataRepoImpl extends AbstractBaseRepositoryImpl<DataDO, DataPO> implements DataRepo {
    protected DataRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected DataDO fromPO(DataPO dataPO) {
        return dataPO.to();
    }

    @Override
    protected DataPO toPO(DataDO dataDO) {
        return DataPO.from(dataDO);
    }
}
