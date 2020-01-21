package com.jamoda.controller.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Test
    void adminTest() {
        AdminController adminController = new AdminController();
        Model model = mock(Model.class);
        Assertions.assertEquals( "admin/admin",
                adminController.admin(model));
    }
}