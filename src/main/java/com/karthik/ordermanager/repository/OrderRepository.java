package com.karthik.ordermanager.repository;

import com.karthik.ordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByJobNo(String jobNo);

    Order findByChallanNo(String challanNo);
}
