package org.example.dao;

public interface Dao<T> {
    T get(Long id);
    boolean create(T entity);
    void update(T entity);
}
