package DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexey on 25.06.2015.
 */
public interface DAOOperations<T> {
    @SuppressWarnings("unchecked")
    List<T> getAll();

    T get(Serializable id);

    void delete(Serializable id);

    T update(T entity);

    void add(T entity);
}
