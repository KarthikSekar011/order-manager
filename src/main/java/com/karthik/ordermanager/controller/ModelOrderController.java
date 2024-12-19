package com.karthik.ordermanager.controller;

import com.karthik.ordermanager.entity.Customer;
import com.karthik.ordermanager.entity.Order;
import com.karthik.ordermanager.service.CustomerService;
import com.karthik.ordermanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class ModelOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

//    @GetMapping
//    public String listOrders(Model model) {
//        List<Order> orders = orderService.getAllOrders();
//        model.addAttribute("orders", orders);
//        return "order-list"; // Points to templates/order-list.html
//    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        List<Customer> customers = customerService.getAllCustomers();  // Fetch all customers
        model.addAttribute("customers", customers);
        model.addAttribute("order", order);
        return "order-form"; // Points to templates/order-form.html
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderUi(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @GetMapping("/new")
    public String showOrderForm(Model model) {
        List<Customer> customers = customerService.getAllCustomers();  // Fetch all customers
        model.addAttribute("customers", customers);  // Pass customers to the view
        model.addAttribute("order", new Order());  // Create a new empty order
        return "order-form";  // Return the view name
    }

    @PostMapping("/save-order")
    public String saveOrder(Order order) {
        orderService.saveOrder(order);  // Save the order to the database
        return "redirect:/orders";  // Redirect back to the form after saving
    }

//    @GetMapping
//    public String listOrders(
//            @RequestParam(required = false) String jobNo,
//            @RequestParam(required = false) String date,
//            @RequestParam(required = false) String challanNo,
//            @RequestParam(required = false) String customer,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            Model model) {
//
//        Page<Order> orders = orderService.filterOrders(jobNo, date, challanNo, customer, page, size);
//        model.addAttribute("orders", orders);
//        model.addAttribute("jobNo", jobNo);
//        model.addAttribute("date", date);
//        model.addAttribute("challanNo", challanNo);
//        model.addAttribute("customer", customer);
//        return "order-list";
//    }

    @GetMapping
    public String getFilteredOrders(
            @RequestParam(required = false) String jobNo,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String challanNo,
            @RequestParam(required = false) String customer,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {

        Page<Order> orders = orderService.filterOrders(jobNo, fromDate, toDate, challanNo, customer, page, size);
        model.addAttribute("orders", orders);
        model.addAttribute("jobNo", jobNo);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("challanNo", challanNo);
        model.addAttribute("customer", customer);
        return "order-list";
    }

}
