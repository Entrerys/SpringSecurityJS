package ru.mishucov.spring.springSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mishucov.spring.springSecurityApp.model.Role;
import ru.mishucov.spring.springSecurityApp.model.User;
import ru.mishucov.spring.springSecurityApp.service.RoleService;
import ru.mishucov.spring.springSecurityApp.service.UsersService;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Util {

    private final RoleService roleService;
    private final UsersService usersService;

    @Autowired
    public Util(RoleService roleService, UsersService usersService) {
        this.roleService = roleService;
        this.usersService = usersService;

    }

    @PostConstruct
    public void initialization() {
        Role user1 = new Role("ROLE_USER");
        Role admin1 = new Role("ROLE_ADMIN");
        roleService.addRole(user1);
        roleService.addRole(admin1);

        List<Role> roleUser = new ArrayList<>();
        roleUser.add(user1);

        User user = new User("user", "user", "user", "user", 18, "user@mail.ru", roleUser);
        User admin = new User("admin", "admin", "admin", "admin", 30, "admin@mail.ru", roleService.getRoles());

        usersService.saveUser(user);
        usersService.saveUser(admin);

    }

}
