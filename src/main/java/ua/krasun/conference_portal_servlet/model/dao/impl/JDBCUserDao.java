package ua.krasun.conference_portal_servlet.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private String queryAdd = "INSERT INTO user (first_name, email , password , role , active) VALUES (? ,? ,? , ?, ?)";
    private String queryFindByEmail = "SELECT * FROM user WHERE email = ?";
    private String queryFindById = "SELECT * FROM user WHERE id = ?";
    private String queryFindAll = "SELECT * FROM user";
    private String queryUpdateUser = "UPDATE user SET first_name = ?, email = ? , password = ?, role = ?, active = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM user  WHERE id = ?";
    private String queryFindAllSpeaker = "SELECT * FROM user WHERE role = 2";

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(User entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(queryAdd)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setInt(4, entity.getRole().ordinal());
            ps.setBoolean(5, entity.isActive());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement ps = connection.prepareStatement
                (queryFindByEmail)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.info("findByEmail SQLException: " + e.getMessage());
        }
        return null;
    }

    public List<User> findAllSpeaker() {
        return getUsers(queryFindAllSpeaker);
    }

    @Override
    public List<User> findAll() {
        return getUsers(queryFindAll);
    }

    private List<User> getUsers(String queryFindAllSpeaker) {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAllSpeaker);
            while (rs.next()) {
                User result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            logger.info("findAll SQLException: " + e.getMessage());
        }
        return resultList;
    }


    @Override
    public User findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement
                (queryFindById)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.info("findById SQLException: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void update(User entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(queryUpdateUser)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setInt(4, entity.getRole().ordinal());
            ps.setBoolean(5, entity.isActive());
            ps.setLong(6, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareStatement(queryDeleteById)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("delete() SQLException: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.info("close() SQLException: " + e.getMessage());
        }
    }

    private User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.values()[rs.getInt("role")])
                .active(rs.getBoolean("active"))
                .build();
    }
}
