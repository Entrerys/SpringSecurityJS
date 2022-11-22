package ru.mishucov.spring.springSecurityApp.service;

import ru.mishucov.spring.springSecurityApp.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    List<Role> getRoles();

    Role getRoleByName(String name);

    List<Role> getListByRole(List<String> name);
}
