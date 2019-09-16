package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;

public interface PresentationDao extends GenericDao<Presentation> {
    void upDateWithParam(Long presentationEditId, String theme,
                         Long chooseSpeakerID, Long chooseConferenceID);
}
