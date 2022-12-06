package ru.mishucov.spring.springSecurityApp.dto;

import ru.mishucov.spring.springSecurityApp.model.User;

public interface ConverterDTO {

    User convertToUser(UserDTO userDTO);

    UserDTO convertToUserDTO(User user);

}
