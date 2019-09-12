package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCConferenceDao implements ConferenceDao {
    private String queryAdd = "INSERT INTO conference (date, subject, user_id) VALUES (? ,?, ?)";
    private String queryFindByDate = "SELECT * FROM conference left join user on user_id = user.id WHERE date = ?";
    private String queryFindById = "SELECT * FROM conference left join user on user_id = user.id WHERE conference.id = ? ";
    private String queryFindAll = "SELECT * FROM conference left join user on user_id = user.id";
    private String queryUpdateUser = "UPDATE conference SET date = ?, subject = ?, user_id = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM conference  WHERE id = ?";
    private Connection connection;
    private ConferenceMapper conferenceMapper = new ConferenceMapper();

    JDBCConferenceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Conference entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryAdd)) {
            ps.setString(1, String.valueOf(entity.getDate()));
            ps.setString(2, entity.getSubject());
            ps.setLong(3, entity.getAuthor().getId());
            ps.executeUpdate();
        }
    }

    @Override
    public Conference findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(queryFindById)) {
            UserMapper userMapper = new UserMapper();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Conference conference = conferenceMapper.extractFromResultSet(rs);
                User author = userMapper.extractFromResultSet(rs);
                conference.setAuthor(author);
                return conference;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Conference findByDate(LocalDate date) {
        try (PreparedStatement ps = connection.prepareStatement(queryFindByDate)) {
            UserMapper userMapper = new UserMapper();
            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Conference conference = conferenceMapper.extractFromResultSet(rs);
                User author = userMapper.extractFromResultSet(rs);
                conference.setAuthor(author);
                return conference;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Conference> findAll() {
        List<Conference> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                Conference conference = conferenceMapper.extractFromResultSet(rs);
                User author = userMapper.extractFromResultSet(rs);
                conference.setAuthor(author);
                resultList.add(conference);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Conference entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdateUser)) {
            ps.setDate(1, Date.valueOf(entity.getDate()));
            ps.setString(2, entity.getSubject());
            ps.setLong(3, entity.getAuthor().getId());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareStatement(queryDeleteById)) {
            ps.setLong(1, id);
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
}
