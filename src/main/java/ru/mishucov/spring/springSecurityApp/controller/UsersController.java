package ru.mishucov.spring.springSecurityApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mishucov.spring.springSecurityApp.service.RoleService;
import ru.mishucov.spring.springSecurityApp.service.UsersService;


@Controller
@RequestMapping()
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage() {
        return "user";
    }

}
