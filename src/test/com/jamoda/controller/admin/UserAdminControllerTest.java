package com.jamoda.controller.admin;

import com.jamoda.model.User;
import com.jamoda.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import java.io.IOException;
import static org.mockito.Mockito.when;

class UserAdminControllerTest {

    UserService userService = Mockito.mock(UserService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageAddUser() {
        UserAdminController userAdminController = new UserAdminController();
        userAdminController.setUserService(userService);

        Assertions.assertNotNull(userAdminController.pageAddUser(model));
        Assertions.assertEquals( "admin/addUser",
                userAdminController.pageAddUser(model));
    }

    @Test
    void addUser() throws IOException {
        UserAdminController userAdminController = new UserAdminController();
        userAdminController.setUserService(userService);

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
        userAdminController.setUserService(userService);

        User user = new User();
        user.setLogin("qwerty");

        when(userService.findByLogin(user.getLogin())).thenReturn(null);

        Assertions.assertNotNull(userAdminController.addUser(user, model));
        Assertions.assertEquals( "admin/addUser",
                userAdminController.addUser(user, model));
    }
}