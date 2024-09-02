package br.com.spedro.User.utils;

import br.com.spedro.User.dto.UserRequestDTO;
import br.com.spedro.User.dto.UserResponseDTO;
import br.com.spedro.User.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {

    public static UserResponseDTO convertEntityToResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRegistrationDate(user.getRegistrationDate());

        return userResponseDTO;
    }

    public static UserRequestDTO convertEntityToRequestDTO(User user) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername(user.getUsername());
        userRequestDTO.setEmail(user.getEmail());

        return userRequestDTO;
    }

    public static List<UserResponseDTO> convertEntityListToResponseDtoList(List<User> userList) {
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        userList.forEach(user -> userResponseDTOList.add(convertEntityToResponseDTO(user)));
        return userResponseDTOList;
    }

    public static User convertRequestDtoToEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());

        return user;
    }

}
