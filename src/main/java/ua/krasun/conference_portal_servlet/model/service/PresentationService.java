package ua.krasun.conference_portal_servlet.model.service;

import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.PresentationDao;
import ua.krasun.conference_portal_servlet.model.entity.Conference;
import ua.krasun.conference_portal_servlet.model.entity.Presentation;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PresentationService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Presentation> findAllPresentation() {
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            List<Presentation> presentationList = presentationDao.findAll();
            presentationList.sort(Comparator.comparing(p -> p.getConference().getDate()));
            return presentationList;
        }
    }

    public void addPresentation(String theme, User user, Conference conference) throws WrongInputException {
        Presentation newPresentation = Presentation.builder()
                .theme(theme)
                .author(user)
                .conference(conference)
                .build();
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            presentationDao.add(newPresentation);
        }
    }

    public void presentationEdit(int presentationEditId, String theme,
                                 int chooseSpeakerID, int chooseConferenceID) throws WrongInputException {
        UserService userService = new UserService();
        ConferenceService conferenceService = new ConferenceService();
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            presentationDao.getConnection().setAutoCommit(false);
            Presentation presentation = presentationDao.findById(presentationEditId);
            presentation.setTheme(theme);
            presentation.setAuthor(userService.findUserById(chooseSpeakerID).orElseThrow());
            Conference conference = conferenceService.findById(chooseConferenceID)
                    .orElseThrow(() -> new WrongInputException("No such Conference"));
            presentation.setConference(conference);
            presentationDao.update(presentation);
            presentationDao.getConnection().commit();
        } catch (SQLException e) {
            throw new WrongInputException(e.getMessage());
        }
    }

    public void deletePresentation(long id) {
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            presentationDao.delete(id);
        }
    }

    public Optional<Presentation> findById(int id) {
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            return Optional.ofNullable(presentationDao.findById(id));
        }
    }

    public List<Presentation> findAllByAuthorId(long id) {
        return findAllPresentation().stream().filter(p -> p.getAuthor().getId() == id).collect(Collectors.toList());
    }
}
