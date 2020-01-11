package com.jamoda.service;

import com.jamoda.model.*;
import com.jamoda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private WarehouseRepository warehouseRepository;
    private ClothesRepository clothesRepository;
    private CustomerRepository customerRepository;

    public Order saveOrder(Order order) {
        Customer customer = customerRepository.findByEmail(order.getEmail());
        if(customer != null) {
            order.setCustomer(customer);
        }
        return orderRepository.saveAndFlush(order);
    }

    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.saveAndFlush(orderProduct);
    }

    public Order confirmOrder(int id) {
        Order order = orderRepository.findById(id);
        order.setConfirm(true);
        return orderRepository.saveAndFlush(order);
    }

    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    public List<OrderProduct> getOrderProduct(Cart cart, Order order) {
        List<OrderProduct> orderProducts = new LinkedList<>();
        for(ProductInCart product: cart.getCart()) {
            orderProducts.add(new OrderProduct(product.getCount(),
                    product.getSize(),
                    product.getPrice()*product.getCount(),
                    order,
                    clothesRepository.findByArticle(product.getArticle())));
        }
        return orderProducts;
    }

    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByDateDesc(pageable);
    }

    public Boolean checkProductInWarehouse(Cart cart) {
        for(ProductInCart product: cart.getCart()) {
            Warehouse warehouse = warehouseRepository.findAllByArticleAndSize(product.getArticle(), product.getSize());
            if(warehouse.getCount() < product.getCount()) {
                return false;
            }
        }
        return true;
    }

    public void saveCart(Cart cart, Order order) {
        for(ProductInCart product: cart.getCart()) {
            Clothes clothes = clothesRepository.findByArticle(product.getArticle());
            OrderProduct orderProduct = new OrderProduct(product.getCount(),
                    product.getSize(),
                    product.getPrice()*product.getCount(),
                    order,
                    clothes);
            //удаляем продукт из склада
            deleteProductFromWarehouse(orderProduct);
            //сохраняем продукт в таблицу купленных товаров
            orderProductRepository.saveAndFlush(orderProduct);
            //проверяем остался ли этот товар на складе (для поля presence)
            checkPresence(clothes);
        }
    }

    public void checkPresence(Clothes clothes) {
        Integer count = 0;
        for(Warehouse warehouse : clothes.getWarehouses()) {
            count += warehouse.getCount();
        }
        if (count == 0) {
            clothes.setPresence(false);
            clothesRepository.saveAndFlush(clothes);
        }
    }

    public Warehouse deleteProductFromWarehouse(OrderProduct orderProduct) {
        Warehouse warehouse = warehouseRepository.findByClothesAndSize(orderProduct.getClothes(), orderProduct.getSize());
        warehouse.setCount(warehouse.getCount() - orderProduct.getCount());
        return warehouseRepository.saveAndFlush(warehouse);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public void setOrderProductRepository(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }
    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    @Autowired
    public void setClothesRepository(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
