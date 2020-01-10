package com.jamoda.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void Active() {
        User user = new User();
        user.setActive(true);
        user.setName("123");
        Assertions.assertEquals(user.getName(), "123");
        Assertions.assertEquals(user.isActive(), true);
        Assertions.assertEquals(user.isEnabled(), true);
        Assertions.assertEquals(user.isAccountNonExpired(), true);
        Assertions.assertEquals(user.isAccountNonLocked(), true);
        Assertions.assertEquals(user.isCredentialsNonExpired(), true);
    }

    @Test
    void UsernamePass() {
        User user = new User();
        user.setLogin("123");
        user.setPassword("123");
        Assertions.assertEquals(user.getUsername(), "123");
        Assertions.assertEquals(user.getPassword(), "123");
    }
}