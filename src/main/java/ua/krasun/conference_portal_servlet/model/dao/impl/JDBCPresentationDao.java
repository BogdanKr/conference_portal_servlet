package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.PresentationDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPresentationDao implements PresentationDao {
    private String queryAdd = "INSERT INTO presentation (theme, user_id, conference_id) VALUES (? ,?, ?)";
    private String queryFindById = "SELECT * FROM presentation " +
            "left join user on presentation.user_id = user.id " +
            "left join conference on presentation.conference_id = conference.id " +
            "WHERE presentation.id = ?";
    private String queryFindAll = "SELECT * FROM presentation " +
            "left join user on presentation.user_id = user.id " +
            "left join conference on presentation.conference_id = conference.id";
    private String queryUpdateUser = "UPDATE presentation SET theme = ?, user_id = ?, conference_id = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM presentation  WHERE id = ?";
    private Connection connection;

    JDBCPresentationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Presentation entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryAdd)) {
            ps.setString(1, entity.getTheme());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setLong(3, entity.getConference().getId());
            ps.executeUpdate();
        }
    }

    @Override
    public Presentation findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(queryFindById)) {
            ps.setInt(1, id);
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
    public List<Presentation> findAll() {
        List<Presentation> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);
            while (rs.next()) {
                Presentation presentation = extractFromResultSet(rs);
                resultList.add(presentation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Presentation entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdateUser)) {
            ps.setString(1, entity.getTheme());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setLong(3, entity.getConference().getId());
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

    private Presentation extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        return Presentation.builder()
                .id(rs.getLong("id"))
                .theme(rs.getString("theme"))
                .conference(conferenceMapper.extractFromResultSet(rs))
                .author(userMapper.extractFromResultSet(rs))
                .build();
    }

}
