package com.karthik.ordermanager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_no", nullable = false, unique = true)
    private String jobNo;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @Column(name = "challan_no", nullable = false)
    private String challanNo;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") // Foreign key in the orders table
    private Customer customer;

    // Constructors
    public Order() {}

    public Order(String jobNo, LocalDate date, String challanNo, Integer quantity, String materialName) {
        this.jobNo = jobNo;
        this.date = date;
        this.challanNo = challanNo;
        this.quantity = quantity;
        this.materialName = materialName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", jobNo='" + jobNo + '\'' +
                ", date=" + date +
                ", challanNo='" + challanNo + '\'' +
                ", quantity=" + quantity +
                ", materialName='" + materialName + '\'' +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
