package ua.krasun.conference_portal_servlet.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(long id);

    void close();
}
