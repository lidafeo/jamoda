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

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
