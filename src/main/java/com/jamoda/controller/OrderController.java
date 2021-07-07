package com.jamoda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamoda.model.*;
import com.jamoda.service.CustomerService;
import com.jamoda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@Controller
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    @PostMapping("/order_page")
    public String order(@RequestParam("count") Integer count,
                        @RequestParam("price") Integer price,
                        Model model,
                        @AuthenticationPrincipal User user) {
        if(user != null) {
            model.addAttribute("customer", customerService.findByUser(user));
        }
        else {
            model.addAttribute("customer", user);
        }
        model.addAttribute("count", count);
        model.addAttribute("price", price);
        return "parts/order";
    }

    @PostMapping(value = "/order", produces = "application/json")
    public String saveOrder(@RequestParam String mycart,
                            Order order,
                            Model model) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cart cart = mapper.readValue(mycart, Cart.class);
        //проверяем, есть ли что-то в корзине
        if(cart.getCount() == 0 || cart.getPrice() == 0) {
            model.addAttribute("error", "Корзина пуста");
            return "json";
        }
        //проверяем наличие товаров
        boolean check = orderService.checkProductInWarehouse(cart);
        if(!check) {
            model.addAttribute("error", "Товара нет в наличии");
            return "json";
        }
        order.setSum(cart.getPrice());
        order.setDate(new Date());
        if(order.getPayment().equals("online")) {
            order.setPaid(true);
        }
        Order saveOrder = orderService.saveOrder(order);
        //сохраняем ссылки на купленные продукты и удаляем их со склада
        orderService.saveCart(cart, order);

        model.addAttribute("message", order.getId());
        model.addAttribute("order", saveOrder);
        return "json";
    }

    /*
    @PostMapping("/stripe")
    public String stripe(@RequestParam("count") Integer count,
                        @RequestParam("price") Integer price,
                        Model model,
                         @AuthenticationPrincipal User user) {
        //model.addAttribute("categories", categoryService.findMainCategory());
        //model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("count", count);
        model.addAttribute("price", price);
        model.addAttribute("customer", user);
        return "parts/order";
    }*/

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
