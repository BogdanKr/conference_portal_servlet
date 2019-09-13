package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;

import java.util.List;

public interface PresentationDao extends GenericDao<Presentation> {
    List<Presentation> findByConferenceID(Long id);
}
