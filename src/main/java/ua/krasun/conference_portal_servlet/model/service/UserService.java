package ua.krasun.conference_portal_servlet.model.service;


import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.createUserDao();

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public void addUser(String email, String password) throws SQLException {
        User newUser = User.builder()
                .email(email)
                .password(password)
                .role(Role.USER)
                .active(true)
                .build();
        userDao.add(newUser);
    }

    public Optional<User> findUser(String email, String password){
        Optional<User> user = Optional.ofNullable((userDao.findByEmail(email)));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
