package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
    List<User> findAllSpeaker();
    Connection getConnection();
}
