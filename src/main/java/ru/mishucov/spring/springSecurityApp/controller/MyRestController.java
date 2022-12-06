package ru.mishucov.spring.springSecurityApp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mishucov.spring.springSecurityApp.dto.ConverterDTO;
import ru.mishucov.spring.springSecurityApp.dto.ConverterDTOImpl;
import ru.mishucov.spring.springSecurityApp.dto.UserDTO;
import ru.mishucov.spring.springSecurityApp.model.User;
import ru.mishucov.spring.springSecurityApp.service.RoleService;
import ru.mishucov.spring.springSecurityApp.service.UsersService;

import java.security.Principal;

import java.util.List;
import java.util.stream.Collectors;

@RestController //Controller +@ResponseBody над всеми методами
@RequestMapping("/api")
public class MyRestController {

    private final UsersService usersService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final ConverterDTO converterDTO;

    @Autowired
    public MyRestController(UsersService usersService, RoleService roleService, ModelMapper modelMapper, ConverterDTOImpl converterDTO) {
        this.usersService = usersService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.converterDTO = converterDTO;
    }

    @GetMapping(value = "/admin/info")
    public ResponseEntity<UserDTO> getAdminInfo(Principal principal) {
        User user = usersService.getUserByUsername(principal.getName());
        UserDTO userDTO = converterDTO.convertToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> index() {
        List<UserDTO> all = usersService.getAllUsers().stream().map(converterDTO::convertToUserDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PatchMapping("/admin/edit")
    public ResponseEntity<Void> editUser(@RequestBody UserDTO userDTO) {
        User user = converterDTO.convertToUser(userDTO);
        user.setId(userDTO.getId());
        usersService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(usersService.getAllUsers().stream().map(converterDTO::convertToUserDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(converterDTO.convertToUserDTO(usersService.getUserById(id)));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<UserDTO> getUserInfo(Principal principal) {
        User user = usersService.getUserByUsername(principal.getName());
        UserDTO userDTO = converterDTO.convertToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping(value = "/admin/create")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDTO userDTO) {
        User user = converterDTO.convertToUser(userDTO);
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        usersService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
