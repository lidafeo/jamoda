package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import com.jamoda.service.CartService;
import com.jamoda.service.CategoryService;
import com.jamoda.service.MainService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.*;

@Controller
public class OrderController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/order")
    public String order(Model model,
                        HttpSession session) {
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("cart", cartService.getCart(session));
        return "order";
    }

    @PostMapping("/order")
    public String saveOrder(@RequestParam("summa") String summa,
                            Order order,
                            Model model,
                            HttpSession session) {
        if(order.getPayment().equals("online")) {
            order.setPaid(true);
        }
        model.addAttribute("categories", categoryService.findMainCategory());
        model.addAttribute("cart", cartService.getCart(session));
        Cart cart = cartService.getCart(session);
        //проверяем, есть ли что-то в корзине
        if(cart == null) {
            model.addAttribute("error", "void");
            return "order";
        }
        order.setSum(cart.getPrice());
        Order saveOrder = orderRepository.saveAndFlush(order);
        //сохраняем ссылки на купленные продукты
        Map<String, ProductInCart> cartProducts =  cart.getProducts();
        for (String product : cartProducts.keySet()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setClothes(cartProducts.get(product).getClothes());
            orderProduct.setCount(cartProducts.get(product).getCount());
            orderProduct.setPrice(cartProducts.get(product).getPrice());
            orderProduct.setSize(cartProducts.get(product).getSize());
            orderProduct.setOrder(saveOrder);
            orderProductRepository.saveAndFlush(orderProduct);
        }
        //очищаем корзину
        cartService.cleanCart(session);
        model.addAttribute("message", "success");
        model.addAttribute("order", saveOrder);
        return "order";
    }
}
