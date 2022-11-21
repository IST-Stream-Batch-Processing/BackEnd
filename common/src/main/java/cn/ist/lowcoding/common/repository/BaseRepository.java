package cn.ist.lowcoding.common.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<DO> {
    void save(DO domain);

    Optional<DO> findById(String id);

    List<DO> findAll();

    void deleteById(String id);

    long count();
}
