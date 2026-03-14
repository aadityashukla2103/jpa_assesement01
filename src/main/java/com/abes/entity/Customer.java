package com.abes.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="abes_customer")
public class Customer {

    @Id
    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="customer_name", length=45, nullable=false)
    private String customerName;

    @OneToMany(mappedBy="customer")
    private List<Order> orders;

    public Customer() {}

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        String orderInfo = "";

        if(orders != null) {
            for(Order o : orders) {
                orderInfo += o.getOrderId() + " ";
            }
        }

        return "Customer [customerId=" + customerId +
               ", customerName=" + customerName +
               ", orders=" + orderInfo +
               "]";
    }
}
