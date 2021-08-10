package ua.goit.service;

import java.util.Set;

public interface HibernateService<T> {
    T create(T entity);
    String delete(T entity);
    T update(T entity);
    T findById(Integer id);
    Set<T> getAll();
}
