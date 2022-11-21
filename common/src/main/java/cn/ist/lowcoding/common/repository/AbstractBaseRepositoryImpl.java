package cn.ist.lowcoding.common.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractBaseRepositoryImpl<DO, PO> implements BaseRepository<DO> {
    final protected Class<PO> PO_CLAZZ;

    final protected Method PO_GET_ID_METHOD;

    final protected Class<DO> DOMAIN_CLAZZ;

    final protected Method DO_SET_ID_METHOD;

    protected MongoTemplate mongoTemplate;

    protected AbstractBaseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.PO_CLAZZ = (Class<PO>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        try {
            this.PO_GET_ID_METHOD = this.PO_CLAZZ.getMethod("getId");
            this.PO_GET_ID_METHOD.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("PO:" + this.PO_CLAZZ + "需要有getId方法");
        }
        this.DOMAIN_CLAZZ = (Class<DO>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            this.DO_SET_ID_METHOD = this.DOMAIN_CLAZZ.getMethod("setId", String.class);
            this.DO_SET_ID_METHOD.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("DO:" + this.DOMAIN_CLAZZ + "需要有setId方法");
        }
        this.mongoTemplate = mongoTemplate;
    }

    protected abstract DO fromPO(PO po);

    protected abstract PO toPO(DO domain);

    @Override
    public void save(DO domain) {
        PO po = toPO(domain);
        mongoTemplate.save(po);
        try {
            String id = (String)PO_GET_ID_METHOD.invoke(po);
            DO_SET_ID_METHOD.invoke(domain, id);
        }
        catch (Exception e) {
            throw new RuntimeException("反射调用失败");
        }
    }

    @Override
    public Optional<DO> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PO_CLAZZ))
            .map(this::fromPO);
    }

    @Override
    public List<DO> findAll() {
        return mongoTemplate.findAll(PO_CLAZZ)
            .stream()
            .map(this::fromPO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        mongoTemplate.findAndRemove(Query.query(criteria), PO_CLAZZ);
    }

    @Override
    public long count() {
        return mongoTemplate.count(new Query(), PO_CLAZZ);
    }
}
