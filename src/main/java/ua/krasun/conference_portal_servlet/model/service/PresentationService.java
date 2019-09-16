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

public class PresentationService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Presentation> findAllPresentation() {
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            List<Presentation> presentationList = presentationDao.findAll();
            presentationList.sort(Comparator.comparing(p -> p.getConference().getDate()));
            return presentationList;
        }
    }

    public void addPresentation(String theme, User user, Conference conference) throws SQLException {
        Presentation newPresentation = Presentation.builder()
                .theme(theme)
                .author(user)
                .conference(conference)
                .build();
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            presentationDao.add(newPresentation);
        }
    }

    public void presentationEdit(String presentationEditId, String theme,
                                 String chooseSpeakerID, String chooseConferenceID) {
        UserService userService = new UserService();
        ConferenceService conferenceService = new ConferenceService();
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            Presentation presentation = presentationDao.findById(Integer.parseInt(presentationEditId));
            presentation.setTheme(theme);
            presentation.setAuthor(userService.findUserById(Integer.parseInt(chooseSpeakerID)).orElseThrow());
            int chooseConferenceIDinteger = Integer.parseInt(chooseConferenceID);
            Conference conference = conferenceService.findById(chooseConferenceIDinteger)
                    .orElseThrow(() -> new WrongInputException("No such Conference"));
            presentation.setConference(conference);
            presentationDao.update(presentation);

//            presentationDao.upDateWithParam(Long.parseLong(presentationEditId), theme,
//                    Long.parseLong(chooseSpeakerID),Long.parseLong(chooseConferenceID));
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
    }

    public void deletePresentation(long id) throws SQLException {
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            presentationDao.delete(id);
        }
    }

    public Optional<Presentation> findById(int id){
        try (PresentationDao presentationDao = daoFactory.createPresentationDao()) {
            return Optional.ofNullable(presentationDao.findById(id));
        }
    }
}
