package ru.javamentor.springbootmvc.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootmvc.dao.UserDao;
import ru.javamentor.springbootmvc.model.User;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void update(int id, User updateUser) {
        userDao.update(id, updateUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }
//
//    @Override
//    @Transactional
//    public User isExistById(User user) {
//        return userDao.isExistById(user);
//    }
}

