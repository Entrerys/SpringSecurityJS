package ru.mishucov.spring.SpringSecurityApp.Dao;



import ru.mishucov.spring.SpringSecurityApp.Model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    List<Role> getRoles();

    Role getRoleByName(String name);

    List<Role> listByName(List<String> name);
}
