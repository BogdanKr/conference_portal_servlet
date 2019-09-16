package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.PresentationMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConferenceDao implements ConferenceDao {
    private String queryAdd = "INSERT INTO conference (date, subject, user_id) VALUES (? ,?, ?)";
    private String queryFindById = "select * from conference " +
            "left join presentation p on conference.id = p.conference_id " +
            "left join user u on p.user_id = u.id where conference_id = ?";
    private String queryFindAll = "select * from conference " +
            "left join presentation p on conference.id = p.conference_id " +
            "left join user u on p.user_id = u.id";
    private String queryUpdateUser = "UPDATE conference SET date = ?, subject = ?, user_id = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM conference  WHERE id = ?";
    private Connection connection;

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
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(queryFindById)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Conference conference = new Conference();
            while (rs.next()) {
                conference = extractFromResultSet(rs);
                conference.setId(rs.getLong(1));
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                Presentation presentation = presentationMapper.extractFromResultSet(rs);
                presentation.getAuthor().setId(rs.getLong(7));
                conference.getPresentations().add(presentation);
            }
            return conference;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Conference> findAll() {
        List<Conference> resultList;
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);
            while (rs.next()) {
                Conference conference = conferenceMapper.extractFromResultSet(rs);
                conference.setId(rs.getLong(1));
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                Presentation presentation = presentationMapper.extractFromResultSet(rs);
                presentation.getAuthor().setId(rs.getLong(7));
                conference.getPresentations().add(presentation);
            }
            resultList = new ArrayList<>(conferenceMap.values());
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
    public void delete(long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryDeleteById)) {
            ps.setLong(1, id);
            ps.executeUpdate();
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

    private Conference extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        User user = new User();
        user.setId(rs.getLong("user_id"));
        return Conference.builder()
                .id(rs.getLong("id"))
                .date(rs.getDate("date").toLocalDate())
                .subject(rs.getString("subject"))
                .author(user)
                .build();
    }
}
