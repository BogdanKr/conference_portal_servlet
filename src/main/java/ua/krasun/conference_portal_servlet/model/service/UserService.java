package ua.krasun.conference_portal_servlet.model.service;


import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;
import ua.krasun.conference_portal_servlet.model.entity.exception.WrongInputException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<User> findAllUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public void addUser(String email, String password) throws SQLException, WrongInputException {
        if (!email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$"))
            throw new WrongInputException("Wrong email");
        User newUser = User.builder()
                .firstName("")
                .email(email)
                .password(password)
                .role(Role.USER)
                .active(true)
                .build();
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.add(newUser);
        }
    }

    public Optional<User> findUser(String email, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            Optional<User> user = Optional.ofNullable((userDao.findByEmail(email)));
            if (user.isPresent() && user.get().getPassword().equals(password) && user.get().isActive()) {
                return user;
            }
        }
        return Optional.empty();
    }

    public Optional<User> findUserById(long id) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return Optional.ofNullable((userDao.findById(id)));
        }
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    public void userEdit(String id,
                         String firstName,
                         String email,
                         String password,
                         String active,
                         String role) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findById(Integer.parseInt(id));
            user.setFirstName(firstName);
            user.setEmail(email);
            if (!password.isEmpty()) user.setPassword(password);
            if (Optional.ofNullable(active).isPresent()) user.setActive(true);
            else user.setActive(false);
            switch (role) {
                case "USER":
                    user.setRole(Role.USER);
                    break;
                case "ADMIN":
                    user.setRole(Role.ADMIN);
                    break;
                case "SPEAKER":
                    user.setRole(Role.SPEAKER);
                    break;
            }
            userDao.update(user);
        }
    }

    public User userEditIfNotAdmin(String id,
                                   String firstName,
                                   String email,
                                   String password) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findById(Integer.parseInt(id));
            user.setFirstName(firstName);
            user.setEmail(email);
            if (!password.isEmpty()) user.setPassword(password);
            userDao.update(user);
            return user;
        }
    }

    public Optional<User> findUserByEmail(String email) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return Optional.ofNullable((userDao.findByEmail(email)));
        }
    }

    public void deleteUser(long id) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.delete(id);
        }
    }

    public List<User> findAllSpeaker() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAllSpeaker();
        }
    }

}
