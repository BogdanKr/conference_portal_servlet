package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.Conference;

import java.time.LocalDate;

public interface ConferenceDao extends GenericDao<Conference> {
    Conference findByDate(LocalDate date);
}
