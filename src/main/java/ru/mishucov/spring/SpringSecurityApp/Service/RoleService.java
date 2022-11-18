package ru.mishucov.spring.SpringSecurityApp.Service;

import ru.mishucov.spring.SpringSecurityApp.Model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    List<Role> getRoles();

    Role getRoleByName(String name);

    List<Role> listByRole(List<String> name);
}
