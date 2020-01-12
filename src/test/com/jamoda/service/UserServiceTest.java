package com.jamoda.service;

import com.jamoda.model.Order;
import com.jamoda.model.User;
import com.jamoda.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void findByLoginTest() throws NullPointerException{
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        User user = new User();
        user.setName("123");
        user.setLogin("123");
        Mockito.when(userRepository.findByLogin("123")).thenReturn(user);
        userService.loadUserByUsername(user.getUsername());

        Assertions.assertEquals(userService.findByLogin("123"),
                user);
    }

    @Test
    void saveUserTest() {
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);

        User user = new User();
        user.setLogin("1");
        user.setPassword("1");
        user.setName("1");
        userService.saveUser(user);

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

    }

    @Test
    void loadUserByUsername() {
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);

        User user = new User();
        user.setName("1");

        Mockito.when(userRepository.findByLogin("user")).thenReturn(user);
        boolean is;
        try {
            userService.loadUserByUsername("user");
            is = false;
        } catch (UsernameNotFoundException thrown) {
            is = true;
        }
        Assertions.assertTrue(is);
    }

}