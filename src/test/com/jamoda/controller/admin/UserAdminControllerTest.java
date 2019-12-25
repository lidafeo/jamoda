package com.jamoda.controller.admin;

import com.jamoda.model.User;
import com.jamoda.service.AttributeService;
import com.jamoda.service.ClothesService;
import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAdminControllerTest {

    @Test
    void pageAddUser() {
        UserAdminController userAdminController = new UserAdminController();
        UserService userService = Mockito.mock(UserService.class);
        userAdminController.setUserService(userService);

        Model model = mock(Model.class);

        Assertions.assertNotNull(userAdminController.pageAddUser(model));
        Assertions.assertEquals( "admin/addUser",
                userAdminController.pageAddUser(model));
    }

    @Test
    void addUser() throws IOException {
        UserAdminController userAdminController = new UserAdminController();
        UserService userService = Mockito.mock(UserService.class);
        userAdminController.setUserService(userService);

        Model model = mock(Model.class);
        User user = new User();
        user.setLogin("qwerty");

        when(userService.findByLogin(user.getLogin())).thenReturn(user);

        Assertions.assertNotNull(userAdminController.addUser(user, model));
        Assertions.assertEquals( "admin/addUser",
                userAdminController.addUser(user, model));
    }

    @Test
    void addUser1() {
        UserAdminController userAdminController = new UserAdminController();
        UserService userService = Mockito.mock(UserService.class);
        userAdminController.setUserService(userService);

        Model model = mock(Model.class);
        User user = new User();
        user.setLogin("qwerty");

        when(userService.findByLogin(user.getLogin())).thenReturn(null);

        Assertions.assertNotNull(userAdminController.addUser(user, model));
        Assertions.assertEquals( "admin/addUser",
                userAdminController.addUser(user, model));
    }
}