package com.jamoda.controller.admin;

import com.jamoda.model.Role;
import com.jamoda.model.User;
import com.jamoda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class UserAdminController {

    private UserService userService;

    @GetMapping("/admin/add_user")
    public String pageAddUser(Model model) {
        return "addUser";
    }

    @PostMapping("/admin/add_user")
    public String addUser(User user, Model model) {
        User userFromDb = userService.findByLogin(user.getLogin());
        if(userFromDb != null) {
            model.addAttribute("error", "Такой пользователь уже существует!");
            return "addUser";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userService.saveUser(user);
        model.addAttribute("message", "success");
        return "addUser";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
