package com.jamoda.service;

import com.jamoda.model.Order;
import com.jamoda.model.User;
import com.jamoda.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void findByLoginTest() {
        UserService userService = new UserService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        userService.setUserRepository(userRepository);

        Assertions.assertEquals(userRepository.findByLogin("example"),
                userService.findByLogin("example"));
    }

    @Test
    void saveUserTest() {
        UserService userService = new UserService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        userService.setUserRepository(userRepository);

        User user = new User();
        user.setLogin("1");
        user.setPassword("1");
        user.setName("1");
        userService.saveUser(user);

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

    }
}