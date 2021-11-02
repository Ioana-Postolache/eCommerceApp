package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserController userController;
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CartRepository cartRepository = mock(CartRepository.class);
    private final BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp(){
        userController = new UserController();
        TestUtils.injectObjects(userController, "userRepository", userRepository);
        TestUtils.injectObjects(userController, "cartRepository", cartRepository);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", encoder);
    }

    @Test
    public void testCreateUserHappyPath(){
        ResponseEntity<User> response = createUserResponseEntity();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        User user = response.getBody();
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertEquals("test", user.getUsername());
        assertEquals("thisIsHashed", user.getPassword());
    }

    @Test
    public void testCreateUserWithSmallPassword(){
        when(encoder.encode("1234")).thenReturn("thisIsHashed");
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("test");
        userRequest.setPassword("1234");
        userRequest.setConfirmPassword("1234");

        ResponseEntity<User> response = userController.createUser(userRequest);
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());

        User user = response.getBody();
        assertNull(user);
    }

    @Test
    public void testFindUserByIdHappyPath(){
        ResponseEntity<User> userResponse = createUserResponseEntity();
        User createdUser = userResponse.getBody();
        assertNotNull(createdUser);

        when(userRepository.findById(createdUser.getId())).thenReturn(Optional.of(createdUser));

        ResponseEntity<User> response = userController.findById(createdUser.getId());

        User foundUser = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(foundUser);
        assertEquals(0, foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("thisIsHashed", foundUser.getPassword());
    }

    @Test
    public void testFindUserByIdThatDoesntExist(){
        ResponseEntity<User> response = userController.findById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testFindUserByUserName(){
        ResponseEntity<User> userResponse = createUserResponseEntity();
        User createdUser = userResponse.getBody();
        assertNotNull(createdUser);

        when(userRepository.findByUsername(createdUser.getUsername())).thenReturn(createdUser);

        ResponseEntity<User> response = userController.findByUserName(createdUser.getUsername());

        User foundUser = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(foundUser);
        assertEquals(0, foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("thisIsHashed", foundUser.getPassword());
    }

    @Test
    public void testFindUserByUserNameThatDoesntExist(){
        ResponseEntity<User> response = userController.findByUserName("no-username");
        assertEquals(404, response.getStatusCodeValue());
    }

    private ResponseEntity<User> createUserResponseEntity() {
        when(encoder.encode("password")).thenReturn("thisIsHashed");
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("test");
        userRequest.setPassword("password");
        userRequest.setConfirmPassword("password");

        return userController.createUser(userRequest);
    }

}
