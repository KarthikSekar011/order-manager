package com.karthik.ordermanager.service;

import com.karthik.ordermanager.entity.Customer;
import com.karthik.ordermanager.entity.Order;
import com.karthik.ordermanager.repository.CustomerRepository;
import com.karthik.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    public Page<Order> filterOrders(String jobNo, String fromDate, String toDate, String challanNo, String customer, int page, int size) {
        Specification<Order> spec = Specification.where(null);

        if (jobNo != null && !jobNo.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jobNo"), jobNo));
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate from = LocalDate.parse(fromDate, formatter);
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("date"), from));
        }
        if (toDate != null && !toDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate to = LocalDate.parse(toDate, formatter);
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("date"), to));
        }
        if (challanNo != null && !challanNo.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("challanNo"), challanNo));
        }
        if (customer != null && !customer.isEmpty()) {
            List<Long> customerList = customerRepository.findByNameContaining(customer).stream().map(Customer::getId).toList();
            spec = spec.and((root, query, criteriaBuilder) -> {
                if (!customerList.isEmpty()) {
                    return root.get("customer").get("id").in(customerList);
                } else {
                    return criteriaBuilder.disjunction(); // Returns an empty predicate if no IDs are found
                }
            });
        }

        return orderRepository.findAll(spec, PageRequest.of(page, size));
    }

}
