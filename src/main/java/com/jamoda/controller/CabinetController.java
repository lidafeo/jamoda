package com.jamoda.controller;

import com.jamoda.model.Customer;
import com.jamoda.model.Order;
import com.jamoda.model.Role;
import com.jamoda.model.User;
import com.jamoda.service.CustomerService;
import com.jamoda.service.MainService;
import com.jamoda.service.OrderService;
import com.jamoda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Controller
public class CabinetController {

    private MainService mainService;
    private UserService userService;
    private CustomerService customerService;
    private OrderService orderService;

    @GetMapping("/cabinet")
    public String cabinet(Model model, @AuthenticationPrincipal User user) {
        Customer customer = customerService.findByUser(user);
        mainService.getSessionModel(model);
        model.addAttribute("customer", customer);
        return "cabinet";
    }

    @PostMapping("/cabinet")
    public String editProfile(Customer customer, @AuthenticationPrincipal User user, Model model) {
        mainService.getSessionModel(model);
        Customer customerFromDb = customerService.findByEmail(user.getLogin());
        if(customerFromDb == null) {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "Не удалось сохранить информацию");
            return "cabinet";
        }
        model.addAttribute("customer", customerService.updateInfo(customerFromDb, customer));
        model.addAttribute("message", "Информация сохранена");
        return "cabinet";
    }

    @PostMapping("/detail")
    public String getDetailOrder(int number,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        Customer customer = customerService.findByUser(user);
        Order order = orderService.findById(number);
        if((order.getCustomer() == customer && customer != null) || user.getRoles().contains(Role.ADMIN)) {
            if(user.getRoles().contains(Role.ADMIN) && !user.getRoles().contains(Role.USER)) {
                model.addAttribute("admin", true);
            }
            model.addAttribute("order", order);
        }
        return "parts/detail";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        User userFromDb = userService.findByLogin(user.getLogin());
        if(userFromDb != null) {
            model.addAttribute("error", "Пользователь с такой электронной почтой уже зарегистрирован");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        User savedUser = userService.saveUser(user);
        customerService.saveCustomer(new Customer(savedUser, savedUser.getLogin()));
        return "redirect:/login";
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
