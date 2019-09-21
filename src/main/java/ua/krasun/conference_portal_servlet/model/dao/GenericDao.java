package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws WrongInputException;

    T findById(long id);

    List<T> findAll();

    void update(T entity) throws WrongInputException;

    void delete(long id);

    void close();
}
