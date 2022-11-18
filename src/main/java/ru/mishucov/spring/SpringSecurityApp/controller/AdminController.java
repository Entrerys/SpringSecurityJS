package ru.mishucov.spring.SpringSecurityApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mishucov.spring.SpringSecurityApp.Model.User;
import ru.mishucov.spring.SpringSecurityApp.Service.RoleService;
import ru.mishucov.spring.SpringSecurityApp.Service.UsersService;

import java.security.Principal;
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
    public String getAllUsers(Model model, Principal principal) {
        User user = usersService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("usernew", new User());
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("rolelist", roleService.getRoles());
        return "admin";
    }


    @PostMapping("/new")
    public String create(@ModelAttribute("usernew") User user, @RequestParam(value = "role", required = false) List<String> roles) {
        user.setRoles(roleService.listByRole(roles));
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
        user.setRoles(roleService.listByRole(roles));
        usersService.update(user);
        return "redirect:/admin";
    }


}
