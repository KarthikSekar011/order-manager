package com.karthik.ordermanager.controller;

import com.karthik.ordermanager.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";  // Dashboard HTML template
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {
        // Return the view to create a new customer
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

//    @GetMapping("/orders")
//    public String viewOrders(Model model) {
//        // Fetch and display orders
//        return "redirect:/order-list";  // The page that lists all orders
//    }

    @GetMapping("/invoices")
    public String viewInvoices(Model model) {
        // Fetch and display invoices
        return "invoice-list";  // The page that lists all invoices
    }
}

