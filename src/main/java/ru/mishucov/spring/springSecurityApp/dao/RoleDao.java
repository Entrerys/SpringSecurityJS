package ru.mishucov.spring.springSecurityApp.dao;



import ru.mishucov.spring.springSecurityApp.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    List<Role> getRoles();

    Role getRoleByName(String name);

    List<Role> getListByName(List<String> name);
}
