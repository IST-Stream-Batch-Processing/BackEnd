package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.pojo.combination.CombinationPO;
import cn.ist.lowcoding.streamservice.repository.CombinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class CombinationRepoImpl extends AbstractBaseRepositoryImpl<Combination, CombinationPO> implements CombinationRepo {
    protected CombinationRepoImpl(@Autowired  MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected Combination fromPO(CombinationPO combinationPO) {
        return combinationPO.to();
    }

    @Override
    protected CombinationPO toPO(Combination combiantion) {
        return CombinationPO.from(combiantion);
    }

}
