package ua.krasun.conference_portal_servlet.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);
    private static String PROPERTIES_FILE = "../../src/main/resources/database.properties";
    private static final Properties PROPERTY = new Properties();

    static {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            PROPERTY.load(inputStream);
            logger.info("Load database.properties file");
        } catch (IOException ex) {
            logger.warn("Warning: file database.properties not found" );
        }
    }


    static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(PROPERTY.getProperty("db.driver"));
                    ds.setUrl(PROPERTY.getProperty("db.url"));
                    ds.setUsername(PROPERTY.getProperty("db.username"));
                    ds.setPassword(PROPERTY.getProperty("db.password"));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
