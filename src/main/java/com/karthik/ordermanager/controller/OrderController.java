package com.karthik.ordermanager.controller;

import com.karthik.ordermanager.entity.Order;
import com.karthik.ordermanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET: Retrieve all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // GET: Retrieve an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // POST: Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // PUT: Update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {

        Order savedOrder = orderService.updateOrder(id, updatedOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    // DELETE: Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
