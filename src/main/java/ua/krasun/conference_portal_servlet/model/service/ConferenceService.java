package ua.krasun.conference_portal_servlet.model.service;

import ua.krasun.conference_portal_servlet.model.dao.ConferenceDao;
import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConferenceService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Conference> findAllConference(long currentUserId) {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            List<Conference> conferenceList = conferenceDao.findAll(currentUserId);
            conferenceList.sort(Comparator.comparing(Conference::getDate));
            return conferenceList;
        }
    }

    public void addConference(User currentUser, LocalDate date, String subject) throws WrongInputException {
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
                               String subject) throws WrongInputException {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            Conference conference = conferenceDao.findById(Long.parseLong(id));
            conference.setDate(date);
            conference.setSubject(subject);
            conferenceDao.update(conference);
        }
    }

    public void deleteConference(long id) {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            conferenceDao.delete(id);
        }
    }

    public Optional<Conference> findById(long id) {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            return Optional.ofNullable(conferenceDao.findById(id));
        }
    }

    public void registration(long confId, long currentUserId) {
        try (ConferenceDao conferenceDao = daoFactory.createConferenceDao()) {
            conferenceDao.checkRegistrationAndAddOrDelete(confId, currentUserId);
        }
    }

    public List<Conference> findAllUserRegistrations(User currentUser) {
        List<Conference> resultList = findAllConference(currentUser.getId());
        return resultList.stream().filter(Conference::isCurrentUserRegistered).collect(Collectors.toList());
    }
}
