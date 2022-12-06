package ru.mishucov.spring.springSecurityApp.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mishucov.spring.springSecurityApp.model.Role;
import ru.mishucov.spring.springSecurityApp.model.User;
import ru.mishucov.spring.springSecurityApp.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDTOImpl implements ConverterDTO {

    private final RoleService roleService;

    @Autowired
    public ConverterDTOImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User convertToUser(UserDTO userDTO) {
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

    @Override
    public UserDTO convertToUserDTO(User user) {
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
