package com.jamoda.config;

import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WebSecurityConfigTest {

    public static String fileName = "./src/main/java/com/jamoda/config/WebSecurityConfig.java";

//    @Test
//    void configure() throws IOException {
//
//        Stream<String> stream = Files.lines(Paths.get(fileName));
//        List<String> line = stream.collect(Collectors.toList());
//        String str = line.get(21);
//        Assertions.assertTrue(str.contains("http.authorizeRequests()"));
//
//        WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
//        UserService userService = Mockito.mock(UserService.class);
//        webSecurityConfig.setUserService(userService);
//    }

    @Test
    void testConfigure() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> line = stream.collect(Collectors.toList());
        String str1 = line.get(40);
        Assertions.assertTrue(str1.contains("protected void configure(AuthenticationManagerBuilder auth) throws Exception"));
        String str2 = line.get(41);
        Assertions.assertTrue(str2.contains("auth.userDetailsService(userService)"));
        String str3 = line.get(42);
        Assertions.assertTrue(str3.contains(".passwordEncoder(NoOpPasswordEncoder.getInstance());"));

        WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
        UserService userService = Mockito.mock(UserService.class);
        webSecurityConfig.setUserService(userService);
    }
}