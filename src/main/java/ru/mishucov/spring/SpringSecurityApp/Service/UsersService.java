package ru.mishucov.spring.SpringSecurityApp.Service;


import ru.mishucov.spring.SpringSecurityApp.Model.User;

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
