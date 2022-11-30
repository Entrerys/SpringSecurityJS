package ru.mishucov.spring.springSecurityApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mishucov.spring.springSecurityApp.model.User;
import ru.mishucov.spring.springSecurityApp.service.RoleService;
import ru.mishucov.spring.springSecurityApp.service.UsersService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index() {
        return "admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("usernew") User user, @RequestParam(value = "role", required = false) List<String> roles) {
        user.setRoles(roleService.getListByRole(roles));
        usersService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") int id, Model model) {
        User user = usersService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        usersService.removeUserById(user.getId());
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id") int id, Model model) {
        User user = usersService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PatchMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "role", required = false) List<String> roles) {
        user.setRoles(roleService.getRoles());
        user.setRoles(roleService.getListByRole(roles));
        usersService.update(user);
        return "redirect:/admin";
    }


}
