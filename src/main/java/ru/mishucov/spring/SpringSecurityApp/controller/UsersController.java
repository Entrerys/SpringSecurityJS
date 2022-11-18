package ru.mishucov.spring.SpringSecurityApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mishucov.spring.SpringSecurityApp.Service.RoleService;
import ru.mishucov.spring.SpringSecurityApp.Service.UsersService;

import java.security.Principal;


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
    public String getUserPage(Principal pr, ModelMap model) {
        model.addAttribute("user", usersService.getUserByUsername(pr.getName()));
        return "user";
    }

}
