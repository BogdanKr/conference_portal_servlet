package ua.krasun.conference_portal_servlet.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.PresentationMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.krasun.conference_portal_servlet.ConferencePortal.QUERY_PROPERTY;

public class JDBCConferenceDao implements ConferenceDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCConferenceDao.class);

    JDBCConferenceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(Conference entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.add"))) {
            ps.setString(1, String.valueOf(entity.getDate()));
            ps.setString(2, entity.getSubject());
            ps.setLong(3, entity.getAuthor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
        }
    }

    @Override
    public Conference findById(long id) {
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        Conference conference = new Conference();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.find.by.id"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                conference = extractFromResultSet(rs);
                conference.setId(rs.getLong(1));
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                Presentation presentation = presentationMapper.extractFromResultSet(rs);
                presentation.getAuthor().setId(rs.getLong(7));
                if (presentation.getAuthor().getId() != 0) conference.getPresentations().add(presentation);
            }
        } catch (SQLException e) {
            logger.info("findById SQLException: " + e.getMessage());
        }
        return conference;
    }


    @Override
    public List<Conference> findAll() {
        return findAll(0);
    }

    @Override
    public List<Conference> findAll(long currentUserId) {
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        UserMapper userMapper = new UserMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        Map<Long, User> regUserMap = new HashMap<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_PROPERTY.getProperty("conference.find.all"));
            while (rs.next()) {
                Conference conference = extractFromResultSet(rs);
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                Presentation presentation = presentationMapper.extractFromResultSet(rs);
                presentation.getAuthor().setId(rs.getLong(7));
                if (presentation.getAuthor().getId() != 0 && !conference.getPresentations().contains(presentation))
                    conference.getPresentations().add(presentation);
                User regUser = extractRegUser(rs);
                regUser = userMapper.makeUnique(regUserMap, regUser);
                if (regUser.getId() == 0) continue;
                if (!regUser.getRegOnConferences().contains(conference)) {
                    regUser.getRegOnConferences().add(conference);
                    conference.getUserRegistrations().add(regUser);
                }
                if (regUser.getId() == currentUserId) conference.setCurrentUserRegistered(true);
            }
        } catch (SQLException e) {
            logger.info("findAll SQLException: " + e.getMessage());
        }
        return new ArrayList<>(conferenceMap.values());
    }

    @Override
    public void addConfRegistration(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.add.conf.reg"))) {
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("addConfRegistration SQLException: " + e.getMessage());
        }
    }

    @Override
    public void deleteConfRegistration(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.delete.conf.reg"))) {
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("deleteConfRegistration SQLException: " + e.getMessage());
        }
    }

    @Override
    public void checkRegistrationAndAddOrDelete(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.check.conf.reg"))) {
            int check = 0;
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            connection.setAutoCommit(false);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = rs.getInt("is_user_reg");
            }
            if (check == 0) addConfRegistration(conferenceId, userId);
            else deleteConfRegistration(conferenceId, userId);
            connection.commit();
        } catch (SQLException e) {
            logger.info("checkRegistrationAndAddOrDelete SQLException: " + e.getMessage());
        }

    }

    @Override
    public void update(Conference entity) throws WrongInputException {
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY_PROPERTY.getProperty("conference.update"))) {
            ps.setString(1, String.valueOf(entity.getDate()));
            ps.setString(2, entity.getSubject());
            ps.setLong(3, entity.getAuthor().getId());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_PROPERTY.getProperty("conference.delete.by.id"))) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("delete SQLException: " + e.getMessage());
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

    private Conference extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        return Conference.builder()
                .id(rs.getLong("id"))
                .date(rs.getDate("date").toLocalDate())
                .subject(rs.getString("subject"))
                .author(user)
                .build();
    }

    private User extractRegUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong(17))
                .firstName(rs.getString(22))
                .email(rs.getString(18))
                .password(rs.getString(19))
                .role(Role.values()[rs.getInt(20)])
                .active(rs.getBoolean(21))
                .build();
    }
}
