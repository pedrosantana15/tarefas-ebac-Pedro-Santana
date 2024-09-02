package br.com.spedro.User.controller;

import br.com.spedro.User.dto.UserRequestDTO;
import br.com.spedro.User.dto.UserResponseDTO;
import br.com.spedro.User.entities.User;
import br.com.spedro.User.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO user) {
        LOGGER.info("Creating new User: {}", Instant.now());
        UserResponseDTO response = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(value = "/find-by-id/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable(value = "id") String id) {
        LOGGER.info("Finding User with ID: {}", id);
        UserResponseDTO response = userService.findUserById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        LOGGER.info("Returning all Users...");
        List<UserResponseDTO> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/is-registered/{id}")
    public ResponseEntity<Boolean> isUserRegistered(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(userService.isRegistered(id));
    }

}
