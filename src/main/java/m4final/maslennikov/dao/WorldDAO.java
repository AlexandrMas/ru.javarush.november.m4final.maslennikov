package m4final.maslennikov.dao;

import java.util.List;

public interface WorldDAO<T> {
    List<T> getAll();

    List<T> getItems(int offset, int count);

    T getById(int id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(final int entityId);

    int getTotalCount();
}
