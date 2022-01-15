package repositories;

import java.util.List;

public interface BaseDao<T, V> {
    T save(T entity);
    T update(T entity);
    T findById(V id);
    void delete(V id);
    List<T> findAll();
}
