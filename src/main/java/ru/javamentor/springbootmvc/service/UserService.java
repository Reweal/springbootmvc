package ru.javamentor.springbootmvc.service;
import ru.javamentor.springbootmvc.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    User show(int id);
    void update(int id, User updateUser);
    void delete(int id);
//    User isExistById(User user);
}