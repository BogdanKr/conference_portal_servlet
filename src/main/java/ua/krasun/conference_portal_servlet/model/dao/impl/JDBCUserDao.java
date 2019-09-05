package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private String queryAdd = "INSERT INTO user (email , password , role , active) VALUES (? ,? ,? , ?)";
    private String queryFindByEmail = "SELECT * FROM user WHERE email = ?";
    private String queryFindAll = "SELECT * FROM user";
    private String queryUpdateUser = "UPDATE user SET email = ? , password = ?, role = ?, active = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM user  WHERE id = ?";
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryAdd)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, Arrays.asList(Role.values()).indexOf(entity.getRole()));
            ps.setBoolean(4, entity.isActive());
            ps.executeUpdate();
        }
//        catch (SQLException e) {
//            throw new RuntimeException("Invalid input");
//        }
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
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);

            while (rs.next()) {
                User result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdateUser)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, Arrays.asList(Role.values()).indexOf(entity.getRole()));
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                queryDeleteById)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User extractFromResultSet(ResultSet rs)
            throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.values()[rs.getInt("role")])
                .active(rs.getBoolean("active"))
                .build();
    }
}
