package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.PresentationDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCPresentationDao implements PresentationDao {
    private String queryAdd = "INSERT INTO presentation (theme, user_id, conference_id) VALUES (? ,?, ?)";
    private String queryFindById = "select * from presentation " +
            "left join user u on presentation.user_id = u.id " +
            "left join conference c on presentation.conference_id = c.id where presentation.id = ?";
    private String queryFindAll = "SELECT * FROM presentation " +
            "left join user on presentation.user_id = user.id " +
            "left join conference on presentation.conference_id = conference.id";
    private String queryUpdatePresentation = "UPDATE presentation SET theme = ?, user_id = ?, conference_id = ? WHERE id = ?";
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
    public Presentation findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(queryFindById)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Presentation presentation = extractFromResultSet(rs);
                presentation.getConference().getPresentations().add(presentation);
                return presentation;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Presentation> findAll() {
        List<Presentation> resultList = new ArrayList<>();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);
            while (rs.next()) {
                Presentation presentation = extractFromResultSet(rs);
                Conference conference = conferenceMapper.extractFromResultSet(rs);
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                presentation.setConference(conference);
                conference.getPresentations().add(presentation);
                resultList.add(presentation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Presentation entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdatePresentation)) {
            ps.setString(1, entity.getTheme());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setLong(3, entity.getConference().getId());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void upDateWithParam(Long presentationEditId, String theme,
                                Long chooseSpeakerID, Long chooseConferenceID) {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdatePresentation)) {
            ps.setString(1, theme);
            ps.setLong(2, chooseSpeakerID);
            ps.setLong(3, chooseConferenceID);
            ps.setLong(4, presentationEditId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Can't Update");
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

    private Presentation extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        return Presentation.builder()
                .id(rs.getLong("id"))
                .theme(rs.getString("theme"))
                .author(userMapper.extractFromResultSet(rs))
                .conference(conferenceMapper.extractFromResultSet(rs))
                .build();
    }

}
