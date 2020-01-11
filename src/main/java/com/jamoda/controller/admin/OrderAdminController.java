package com.jamoda.controller.admin;

import com.jamoda.model.Order;
import com.jamoda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderAdminController {
    private OrderService orderService;

    @GetMapping("/admin/list_orders")
    public String pageListOrders(Model model,
                                 @PageableDefault(size = 12) Pageable pageable) {
        Page<Order> orders = orderService.getOrders(pageable);
        model.addAttribute("page", orders);
        return "admin/listOrders";
    }

    @PostMapping("/confirm")
    public String confirmOrder(Model model,
                                 int id) {
        Order order = orderService.confirmOrder(id);
        if(order != null) {
            model.addAttribute("message", id);
        }
        else {
            model.addAttribute("error", "Не удалось подтвердить заказ");
        }
        return "json";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
