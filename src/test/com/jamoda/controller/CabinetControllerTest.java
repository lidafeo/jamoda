package com.jamoda.controller;

import com.jamoda.service.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class CabinetControllerTest {

    @Test
    void cabinet() {
        CabinetController cabinetController = new CabinetController();
        MainService mainService = Mockito.mock(MainService.class);
        cabinetController.setMainService(mainService);

        Model model = Mockito.mock(Model.class);

        Assertions.assertNotNull(cabinetController.cabinet(model));
        Assertions.assertEquals( "cabinet",
                cabinetController.cabinet(model));
    }
}