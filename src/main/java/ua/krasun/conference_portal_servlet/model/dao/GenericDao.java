package ua.krasun.conference_portal_servlet.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findByEmail(String email);

    T findById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close();
}
