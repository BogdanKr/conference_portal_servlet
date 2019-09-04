package ua.krasun.conference_portal_servlet.model.service;


import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.util.List;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.createUserDao();

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public void addUser(User user){
        userDao.add(user);
    }
    public User findUser(String email, String password){
        User user = (User) userDao.findAll().stream().filter(u -> u.getEmail().equals(email));
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
