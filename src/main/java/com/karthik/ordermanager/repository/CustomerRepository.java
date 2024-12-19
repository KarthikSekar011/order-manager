package com.karthik.ordermanager.repository;

import com.karthik.ordermanager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContaining(String name);
}
