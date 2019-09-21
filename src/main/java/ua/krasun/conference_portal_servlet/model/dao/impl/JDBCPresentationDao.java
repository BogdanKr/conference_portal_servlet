package ua.krasun.conference_portal_servlet.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.dao.PresentationDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

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
    private static final Logger logger = LogManager.getLogger(JDBCPresentationDao.class);

    JDBCPresentationDao(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(Presentation entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(queryAdd)) {
            ps.setString(1, entity.getTheme());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setLong(3, entity.getConference().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
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
            logger.info("findById SQLException: " + e.getMessage());
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
            logger.info("findAll SQLException: " + e.getMessage());
        }
        return resultList;
    }

    @Override
    public void update(Presentation entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdatePresentation)) {
            ps.setString(1, entity.getTheme());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setLong(3, entity.getConference().getId());
            ps.setLong(4, entity.getId());
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
