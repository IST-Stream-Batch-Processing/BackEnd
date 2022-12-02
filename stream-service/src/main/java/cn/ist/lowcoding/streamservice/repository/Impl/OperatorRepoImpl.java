package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.stream.OperatorPO;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class OperatorRepoImpl extends AbstractBaseRepositoryImpl<Operator, OperatorPO> implements OperatorRepo {
    protected OperatorRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected Operator fromPO(OperatorPO operatorPO) {
        return operatorPO.to();
    }

    @Override
    protected OperatorPO toPO(Operator operator) {
        return OperatorPO.from(operator);
    }
}
