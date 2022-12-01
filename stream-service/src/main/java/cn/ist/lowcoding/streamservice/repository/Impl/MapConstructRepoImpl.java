package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.stream.MapConstruct;
import cn.ist.lowcoding.streamservice.pojo.stream.MapConstructPO;
import cn.ist.lowcoding.streamservice.repository.MapConstructRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MapConstructRepoImpl extends AbstractBaseRepositoryImpl<MapConstruct, MapConstructPO> implements MapConstructRepo {
    protected MapConstructRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected MapConstruct fromPO(MapConstructPO mapConstructPO) {
        return mapConstructPO.to();
    }

    @Override
    protected MapConstructPO toPO(MapConstruct mapConstruct) {
        return MapConstructPO.from(mapConstruct);
    }
}
