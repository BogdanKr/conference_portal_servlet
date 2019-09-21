package ua.krasun.conference_portal_servlet.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JDBCUserDaoTest {
    private Connection connection;
    private JDBCUserDao dao;

    @Before
    public void before() {
        try {
            String user = "root";
            String password = "12345678";
            String url = "jdbc:mysql://localhost:3306/conference_servlet_db?useTimezone=true&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, user, password);
            dao = new JDBCUserDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGetUserWhichExist() {
        final User user = dao.findByEmail("admin@gmail.com");
        final User expected = User.builder()
                .id(1L)
                .firstName("Bogdan")
                .email("admin@gmail.com")
                .password("1")
                .active(true)
                .role(Role.ADMIN)
                .build();

        assertThat(user, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void whenUserIsNotExist() {
        final User user = dao.findByEmail("xxx");
        assertThat(user.getId(), is(-1));
    }

    @Test
    public void whenAddUserWhichNotExist() throws WrongInputException {
        final User user = User.builder()
                .active(true)
                .role(Role.USER)
                .password("xxx")
                .email("xxx@xxx.xx")
                .build();

            dao.add(user);

        final User newUser = dao.findByEmail("xxx@xxx.xx");
        assertThat(user, is(newUser));
        //Clear test data.
        dao.delete(newUser.getId());
    }

    @Test
    public void whenUpdateExistUserThenPasswordUpdated() throws WrongInputException {
        final User user = User.builder()
                .active(true)
                .role(Role.USER)
                .password("xxx")
                .email("xxx@xxx.xx")
                .build();
        dao.add(user);
        final User getUser = dao.findByEmail("xxx@xxx.xx");
        assertThat(user, is(getUser));

        getUser.setPassword("updated");
        dao.update(getUser);
        final User updated = dao.findByEmail("xxx@xxx.xx");
        assertThat(updated.getPassword(), is("updated"));

        //Clear test data.
        dao.delete(updated.getId());
    }

}