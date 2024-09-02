package br.com.spedro.User;

import br.com.spedro.User.dto.UserRequestDTO;
import br.com.spedro.User.dto.UserResponseDTO;
import br.com.spedro.User.entities.User;
import br.com.spedro.User.services.UserService;
import br.com.spedro.User.utils.UserUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import br.com.spedro.User.controller.UserController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    public UserRequestDTO buildUser() {
        UserRequestDTO user = new UserRequestDTO();
        user.setUsername("Pedro");
        user.setEmail("pedro@gmail.com");
        return user;
    }

    /**
     * The method "createMeme" uses URI Servlet and requires an HTTP context. We can't simulate this context in a
     *     unit test. That's why this method can't work.
     * @deprecated
     */
//    @Test
//    public void testCreateUser_HappyPath() {
//        UserRequestDTO userDto = buildUser();
//        User user = UserUtils.convertRequestDtoToEntity(userDto);
//
//        Mockito.when(userService.createUser(userDto)).thenReturn(UserUtils.convertEntityToResponseDTO(user));
//
//        ResponseEntity<UserResponseDTO> userCreated = userController.createUser(userDto);
//
//        assertNotNull(userCreated);
//        assertEquals(userDto.getUsername(), userCreated.getBody().getUsername());
//        assertEquals(userDto.getEmail(), userCreated.getBody().getEmail());
//    }

    @Test
    public void testFindUserById_HappyPath() {
        UserRequestDTO userDto = buildUser();
        User user = UserUtils.convertRequestDtoToEntity(userDto);
        user.setId("1");

        Mockito.when(userService.findUserById("1")).thenReturn(UserUtils.convertEntityToResponseDTO(user));

        ResponseEntity<UserResponseDTO> userConsulted = userController.findUserById("1");

        assertNotNull(userConsulted);
        assertEquals(user.getUsername(), userConsulted.getBody().getUsername());
    }

    @Test
    public void testFindUserById_InvalidId() {
        Mockito.when(userService.findUserById("2")).thenReturn(null);

        ResponseEntity<UserResponseDTO> userConsulted = userController.findUserById("2");

        assertNull(userConsulted.getBody());
    }

    @Test
    public void testFindAllUsers_HappyPath() {
        UserRequestDTO user1 = buildUser();
        UserRequestDTO user2 = buildUser();
        user2.setUsername("Joao");

        List<User> userList = new ArrayList<>();
        userList.add(UserUtils.convertRequestDtoToEntity(user1));
        userList.add(UserUtils.convertRequestDtoToEntity(user2));

        Mockito.when(userService.findAll()).thenReturn(UserUtils.convertEntityListToResponseDtoList(userList));

        ResponseEntity<List<UserResponseDTO>> list = userController.findAll();

        assertNotNull(list);
        assertEquals(2, list.getBody().size());
    }

    @Test
    public void testIsRegistered_HappyPath() {
        UserRequestDTO userDto = buildUser();
        User user = UserUtils.convertRequestDtoToEntity(userDto);
        user.setId("A1");

        Mockito.when(userService.isRegistered("A1")).thenReturn(true);

        ResponseEntity<Boolean> isRegistered = userController.isUserRegistered("A1");

        assertNotNull(isRegistered);
        assertEquals(true, isRegistered.getBody());
    }

    @Test
    public void testIsRegistered_NotRegistered() {
        UserRequestDTO userDto = buildUser();
        User user = UserUtils.convertRequestDtoToEntity(userDto);
        user.setId("A1");

        Mockito.when(userService.isRegistered("A1")).thenReturn(false);

        ResponseEntity<Boolean> isRegistered = userController.isUserRegistered("A1");

        assertNotNull(isRegistered);
        assertEquals(false, isRegistered.getBody());
    }
}



