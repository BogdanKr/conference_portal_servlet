package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.mapper.ConferenceMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.PresentationMapper;
import ua.krasun.conference_portal_servlet.model.dao.mapper.UserMapper;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConferenceDao implements ConferenceDao {
    private String queryAdd = "INSERT INTO conference (date, subject, user_id) VALUES (? ,?, ?)";
    private String queryFindById = "select * from conference c " +
            "left join presentation p on c.id = p.conference_id " +
            "left join user u on p.user_id = u.id where c.id = ?";
    private String queryFindAll = "select * from conference " +
            "left join presentation p on conference.id = p.conference_id " +
            "left join user u on p.user_id = u.id " +
            "left join conference_registrations cr on conference.id = cr.conference_id " +
            "left join user on cr.user_id=user.id";
    private String queryUpdateUser = "UPDATE conference SET date = ?, subject = ?, user_id = ? WHERE id = ?";
    private String queryDeleteById = "DELETE FROM conference  WHERE id = ?";
    private String queryAddConfReg = "INSERT INTO conference_registrations (conference_id, user_id) VALUES (?, ?)";
    private String queryDeleteConfReg = "DELETE FROM conference_registrations WHERE conference_id = ? AND user_id = ?";
    private String queryCheckReg = "select sum(if((conference_id=? and user_id=?), 1,0)) as is_user_reg from conference_registrations";

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
    public Conference findById(long id) {
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(queryFindById)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Conference conference = new Conference();
            while (rs.next()) {
                conference = extractFromResultSet(rs);
                conference.setId(rs.getLong(1));
                conference = conferenceMapper.makeUnique(conferenceMap, conference);
                Presentation presentation = presentationMapper.extractFromResultSet(rs);
                presentation.getAuthor().setId(rs.getLong(7));
                if (presentation.getAuthor().getId() != 0) conference.getPresentations().add(presentation);
            }
            return conference;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Conference> findAll() {
        return null;
    }

    @Override
    public List<Conference> findAll(long currentUserId) {
        List<Conference> resultList;
        PresentationMapper presentationMapper = new PresentationMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        UserMapper userMapper = new UserMapper();
        Map<Long, Conference> conferenceMap = new HashMap<>();
        Map<Long, User> regUserMap = new HashMap<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(queryFindAll);
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
            resultList = new ArrayList<>(conferenceMap.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void addConfRegistration(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(queryAddConfReg)) {
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConfRegistration(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(queryDeleteConfReg)) {
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int checkRegistration(long conferenceId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(queryCheckReg)) {
            ps.setLong(1, conferenceId);
            ps.setLong(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("is_user_reg");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Conference entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                queryUpdateUser)) {
            ps.setString(1, String.valueOf(entity.getDate()));
            ps.setString(2, entity.getSubject());
            ps.setLong(3, entity.getAuthor().getId());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
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
