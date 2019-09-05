package ua.krasun.conference_portal_servlet.model.service;


import ua.krasun.conference_portal_servlet.model.dao.DaoFactory;
import ua.krasun.conference_portal_servlet.model.dao.UserDao;
import ua.krasun.conference_portal_servlet.model.entity.Role;
import ua.krasun.conference_portal_servlet.model.entity.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.createUserDao();

    public List<User> findAllUsers() {
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

    public Optional<User> findUser(String email, String password) {
        Optional<User> user = Optional.ofNullable((userDao.findByEmail(email)));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable((userDao.findById(id)));
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    public void userEdit(String id,
                         String email,
                         String password,
                         String active,
                         String role) {
        User user = userDao.findById(Integer.parseInt(id));
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
            case "GUEST":
                user.setRole(Role.GUEST);
                break;
        }
        userDao.update(user);
    }

}
