package com.abes.demos;
import java.time.LocalDate;

import com.abes.entity.Customer;
import com.abes.entity.Order;

import jakarta.persistence.*;

public class TestOrder {
public static void main(String[] a) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("abes");
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	Customer c = em.find(Customer.class, 1);

	Order order = new Order();
	order.setOrderDate(LocalDate.now());
	order.setOrderAmt(5000.0);

	order.setCustomer(c);

	em.persist(order);
	
	em.getTransaction().commit();
	em.close();
	emf.close();
}
}
