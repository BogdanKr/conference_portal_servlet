package ua.krasun.conference_portal_servlet.model.service;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ConferenceService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private PresentationService presentationService = new PresentationService();


    public List<Conference> findAllConference() {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            List<Conference> conferenceList = conferenceDao.findAll();
            conferenceList.sort(Comparator.comparing(Conference::getDate));
            return conferenceList;
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
                               String subject) throws SQLException {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            Conference conference = conferenceDao.findById(Integer.parseInt(id));
            conference.setDate(date);
            conference.setSubject(subject);
            conferenceDao.update(conference);
        }
    }

    public void deleteConference(long id) throws SQLException {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            conferenceDao.delete(id);
        }
    }

    public Optional<Conference> findById(long id) throws SQLException {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            return Optional.ofNullable(conferenceDao.findById(id));
        }
    }
}
