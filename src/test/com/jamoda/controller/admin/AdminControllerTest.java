package com.jamoda.controller.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;

class AdminControllerTest {

    @Test
    void adminTest() {
        AdminController adminController = new AdminController();
        Model model = mock(Model.class);
        model.addAttribute("0", 0);
        Assertions.assertNotNull(adminController.admin(model));
        Assertions.assertEquals( "admin/admin",
                adminController.admin(model));
    }
}