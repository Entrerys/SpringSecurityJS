package ru.mishucov.spring.springSecurityApp.dao;


import ru.mishucov.spring.springSecurityApp.model.User;

import java.util.List;

public interface UsersDao {

    void saveUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    void updateUser(int id, User user);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void update(User user);

}
