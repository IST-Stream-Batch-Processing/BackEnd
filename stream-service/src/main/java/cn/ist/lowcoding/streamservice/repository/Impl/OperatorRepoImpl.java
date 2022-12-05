package cn.ist.lowcoding.streamservice.repository.Impl;

import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.streamservice.model.stream.Operator;
import cn.ist.lowcoding.streamservice.pojo.stream.OperatorPO;
import cn.ist.lowcoding.streamservice.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OperatorRepoImpl extends AbstractBaseRepositoryImpl<Operator, OperatorPO> implements OperatorRepo {
    private final String COLLECTION_NAME = "operatorPO";

    protected OperatorRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected Operator fromPO(OperatorPO operatorPO) {
        return operatorPO.to();
    }

    @Override
    protected OperatorPO toPO(Operator operator) {
        try {
            String className = operator.getClass().getName();
            System.out.println("className:" + className);
            String[] temp = className.split("\\.");
            String simpleClassName = temp[temp.length - 1];
            Class<?> clazz = Class.forName( "cn.ist.lowcoding.streamservice.pojo.stream." + simpleClassName + "PO");
            Method method = clazz.getMethod("from", operator.getClass());
            return (OperatorPO) method.invoke(null, operator);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Operator domain) {
        OperatorPO po = toPO(domain);
        mongoTemplate.save(po, COLLECTION_NAME);
        try {
            String id = (String)PO_GET_ID_METHOD.invoke(po);
            DO_SET_ID_METHOD.invoke(domain, id);
        }
        catch (Exception e) {
            throw new RuntimeException("反射调用失败");
        }
    }

    @Override
    public Optional<Operator> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PO_CLAZZ, COLLECTION_NAME))
                .map(this::fromPO);
    }

    @Override
    public List<Operator> findAll() {
        return mongoTemplate.findAll(PO_CLAZZ, COLLECTION_NAME)
                .stream()
                .map(this::fromPO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        mongoTemplate.findAndRemove(Query.query(criteria), PO_CLAZZ, COLLECTION_NAME);
    }

    @Override
    public long count() {
        return mongoTemplate.count(new Query(), PO_CLAZZ, COLLECTION_NAME);
    }

}
