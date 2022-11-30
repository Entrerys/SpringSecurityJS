package ru.mishucov.spring.springSecurityApp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mishucov.spring.springSecurityApp.dto.UserDTO;
import ru.mishucov.spring.springSecurityApp.model.Role;
import ru.mishucov.spring.springSecurityApp.model.User;
import ru.mishucov.spring.springSecurityApp.service.RoleService;
import ru.mishucov.spring.springSecurityApp.service.UsersService;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@RestController //Controller +@ResponseBody над всеми методами
@RequestMapping("/api")
public class MyRestController {

    private final UsersService usersService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public MyRestController(UsersService usersService, RoleService roleService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/admin/info")
    public ResponseEntity<UserDTO> getAdminInfo(Principal principal) {
        User user = usersService.getUserByUsername(principal.getName());
        UserDTO userDTO = convertToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> index() {
        List<UserDTO> all = usersService.getAllUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PatchMapping("/admin/edit")
    public ResponseEntity<Void> editUser(@RequestBody UserDTO userDTO) {
        User user = convertToUser(userDTO);
        user.setId(userDTO.getId());
        usersService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return usersService.getAllUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/admin/{id}")
    public UserDTO getById(@PathVariable("id") int id) {
        return convertToUserDTO(usersService.getUserById(id));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<UserDTO> getUserInfo(Principal principal) {
        User user = usersService.getUserByUsername(principal.getName());
        UserDTO userDTO = convertToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping(value = "/admin/create")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDTO userDTO) {
        User user = convertToUser(userDTO);
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        usersService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        List<Role> list = new ArrayList<>();
        if (userDTO.getRole().contains("ADMIN"))
            list.add(roleService.getRoleByName("ROLE_ADMIN"));
        if (userDTO.getRole().contains("USER"))
            list.add(roleService.getRoleByName("ROLE_USER"));
        user.setRoles(list);
        return user;
    }

    // Бин не настроил с ролью
    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRoles().toString());
        return userDTO;
    }

}
