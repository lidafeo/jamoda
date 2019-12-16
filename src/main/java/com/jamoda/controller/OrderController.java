package com.jamoda.controller;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.*;

import static com.jamoda.controller.CartController.getCart;
import static com.jamoda.controller.MainController.getModel;

@Controller
public class OrderController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @GetMapping("/order")
    public String order(Model model,
                        HttpSession session) {
        //getCommonInfo(model, session);
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        model.addAttribute("cart", getCart(session, clothesRepository));
        return "order";
    }

    @PostMapping("/order")
    public String saveOrder(@RequestParam("summa") String summa,
                            Order order,
                            Model model,
                            HttpSession session) {
        //getCommonInfo(model, session);
        if(order.getPayment().equals("online")) {
            order.setPaid(true);
        }
        //String res = summa.replaceAll("\\u00A0", "");
        //order.setSum(Integer.parseInt(summa.replaceAll("\\s", "")));
        model.addAttribute("categories", categoryRepository.findAllByType("main"));
        model.addAttribute("cart", getCart(session, clothesRepository));
        Cart cart = getCart(session, clothesRepository);
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
        cleanCart(session);
        model.addAttribute("message", "success");
        model.addAttribute("order", saveOrder);
        return "order";
    }


    public Model getCommonInfo(Model model, HttpSession session) {
        return getModel(model, session, categoryRepository, filterRepository, attributeValueRepository);
    }

    public void cleanCart(HttpSession session) {
        session.removeAttribute("SIZES");
        session.removeAttribute("PRODUCTS");
    }

}
