package cn.ist.lowcoding.userservice.repository.impl;

import cn.ist.lowcoding.common.model.QueryCondition;
import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.common.repository.AbstractBaseRepositoryImpl;
import cn.ist.lowcoding.userservice.model.User;
import cn.ist.lowcoding.userservice.pojo.UserPO;
import cn.ist.lowcoding.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepoImpl extends AbstractBaseRepositoryImpl<User, UserPO> implements UserRepo {

    public UserRepoImpl(@Autowired MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected User fromPO(UserPO userPO) {
        return userPO.to();
    }

    @Override
    protected UserPO toPO(User user) {
        return UserPO.from(user);
    }

    @Override
    public List<User> findByRole(Role role) {
        Query query = new Query(Criteria.where("roles").in(role));
        return mongoTemplate.find(query, PO_CLAZZ)
                .stream()
                .map(this::fromPO)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByIds(List<String> ids) {
        Query query = new Query(Criteria.where("id").in(ids));
        return mongoTemplate.find(query, PO_CLAZZ)
                .stream()
                .map(this::fromPO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return Optional.ofNullable(mongoTemplate.findOne(query, PO_CLAZZ)).map(this::fromPO);
    }

    @Override
    public List<User> queryByConditions(List<QueryCondition> queryConditions) {
        if (queryConditions == null || queryConditions.isEmpty()) {
            return findAll();
        } else {
            Criteria criteria = null;
            for (QueryCondition queryCondition : queryConditions) {
                if (criteria == null) {
                    criteria = Criteria.where(queryCondition.getFieldName()).is(queryCondition.getValue());
                } else {
                    criteria.and(queryCondition.getFieldName()).is(queryCondition.getValue());
                }
            }
            Query query = new Query(criteria);
            return mongoTemplate.find(query, PO_CLAZZ).stream().map(this::fromPO).collect(Collectors.toList());
        }
    }
}
