package cn.ist.lowcoding.userservice.repository;

import cn.ist.lowcoding.common.model.QueryCondition;
import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.common.repository.BaseRepository;
import cn.ist.lowcoding.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends BaseRepository<User> {
    List<User> findByRole(Role role);

    List<User> findByIds(List<String> ids);

    Optional<User> findByUsername(String username);

    List<User> queryByConditions(List<QueryCondition> queryConditions);
}
