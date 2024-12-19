package com.karthik.ordermanager.service;

import com.karthik.ordermanager.entity.Order;
import com.karthik.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);

        existingOrder.setJobNo(updatedOrder.getJobNo());
        existingOrder.setDate(updatedOrder.getDate());
        existingOrder.setChallanNo(updatedOrder.getChallanNo());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setMaterialName(updatedOrder.getMaterialName());
        return saveOrder(existingOrder);
    }
}
