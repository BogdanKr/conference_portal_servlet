package ua.krasun.conference_portal_servlet.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity);

    T findByEmail(String email);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close();
}
