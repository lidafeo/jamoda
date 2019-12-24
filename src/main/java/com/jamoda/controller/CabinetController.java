package com.jamoda.controller;

import com.jamoda.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CabinetController {

    private MainService mainService;

    @GetMapping("/cabinet")
    public String cabinet(Model model) {
        mainService.getSessionModel(model);
        return "cabinet";
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
}
