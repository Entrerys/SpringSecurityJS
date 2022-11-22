package ru.mishucov.spring.springSecurityApp.service;


import ru.mishucov.spring.springSecurityApp.model.User;

import java.util.List;

public interface UsersService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void updateUser(int id,User user);

    void removeUserById(int id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void update(User user);
}
