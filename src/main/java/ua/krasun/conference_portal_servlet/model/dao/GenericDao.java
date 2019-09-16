package ua.krasun.conference_portal_servlet.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findById(int id);

    List<T> findAll();

    void update(T entity) throws SQLException;

    void delete(long id) throws SQLException;

    void close();
}
