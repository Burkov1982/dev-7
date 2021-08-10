package ua.goit.dao;

import java.util.Set;

public interface HibernateDAO<T> {
    T findById(int id) throws Exception;
    Integer create(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
    Set<T> getAll() throws Exception;
}
