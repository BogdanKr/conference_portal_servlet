package ua.krasun.conference_portal_servlet.model.dao.impl;

import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();


    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
