package ua.krasun.conference_portal_servlet.model.service;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ConferenceService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Conference> findAllConference() {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            return conferenceDao.findAll();
        }
    }

    public void addConference(User currentUser, LocalDate date, String subject) throws SQLException {
        Conference newConference = Conference.builder()
                .date(date)
                .subject(subject)
                .author(currentUser)
                .build();
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            conferenceDao.add(newConference);
        }
    }

    public void conferenceEdit(String id,
                         LocalDate date,
                         String subject) {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            Conference conference = conferenceDao.findById(Integer.parseInt(id));
            conference.setDate(date);
            conference.setSubject(subject);
            conferenceDao.update(conference);
        }
    }

    public void deleteConference(long id){
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            conferenceDao.delete(id);
        }
    }
}
