package com.abes.dao;

import java.util.List;

import com.abes.entity.Customer;
import com.abes.entity.Order;

import jakarta.persistence.*;

public class OrderDaoImpl implements OrderDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("abes");

    @Override
    public boolean addOrder(Order order, int custId) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer cust = em.find(Customer.class, custId);

        if(cust == null) {
            System.out.println("Customer not found");
            return false;
        }

        order.setCustomer(cust);

        em.persist(order);

        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public Order getOrder(int orderId) {

        EntityManager em = emf.createEntityManager();

        Order order = em.find(Order.class, orderId);

        em.close();

        return order;
    }

    @Override
    public List<Order> getOrders(String custName) {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Order> q = em.createQuery(
                "select o from Order o join fetch o.customer c where c.customerName = :name",
                Order.class);

        q.setParameter("name", custName);

        List<Order> list = q.getResultList();

        em.close();

        return list;
    }
}