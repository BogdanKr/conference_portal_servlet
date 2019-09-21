package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.Conference;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

public interface ConferenceDao extends GenericDao<Conference> {
    List<Conference> findAll(long currentUserId);
    void addConfRegistration(long conferenceId, long userId);
    void deleteConfRegistration(long conferenceId, long userId);
    void checkRegistrationAndAddOrDelete(long conferenceId, long userId);
    Connection getConnection();
}
