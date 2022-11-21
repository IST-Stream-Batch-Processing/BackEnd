package cn.ist.lowcoding.framework.persistence;

import cn.ist.lowcoding.framework.pojo.request.QueryCondition;

import java.util.List;
import java.util.Optional;

public interface Persistence {
    <T> Optional<T> findById(String tableAlias, Integer id, Class<T> tableClass);

    <T> List<T> findAll(String tableAlias, Class<T> tableClass);

    <T> Optional<T> save(String tableAlias, T t, Class<T> tableClass);

    <T> Optional<T> updateById(String tableAlias, Integer id, T t, Class<T> tableClass);

    <T> void deleteById(String tableAlias, Integer id);

    <T> List<T> query(String tableAlias, QueryCondition condition, Class<T> tableClass);
}
