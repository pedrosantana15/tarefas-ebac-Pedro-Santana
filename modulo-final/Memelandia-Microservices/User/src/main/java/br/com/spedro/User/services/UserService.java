package br.com.spedro.User.services;

import br.com.spedro.User.dto.UserRequestDTO;
import br.com.spedro.User.dto.UserResponseDTO;
import br.com.spedro.User.entities.User;
import br.com.spedro.User.repositories.IUserRepository;
import br.com.spedro.User.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userDto) {
        User user = UserUtils.convertRequestDtoToEntity(userDto);
        user.setRegistrationDate(Instant.now());
        userRepository.insert(user);

        return UserUtils.convertEntityToResponseDTO(user);
    }

    public UserResponseDTO findUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserUtils.convertEntityToResponseDTO(user);
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return UserUtils.convertEntityListToResponseDtoList(users);
    }

    public Boolean isRegistered(String id) {
        Optional<User> user = userRepository.findById(id);

        return user.isPresent();
    }

}
