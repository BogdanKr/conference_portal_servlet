package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;

import java.sql.Connection;

public interface PresentationDao extends GenericDao<Presentation> {
    Connection getConnection();
}
