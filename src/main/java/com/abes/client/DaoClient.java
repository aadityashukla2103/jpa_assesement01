package com.abes.client;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.abes.dao.*;
import com.abes.entity.*;

public class DaoClient {

static OrderDao dao = new OrderDaoImpl();
static Scanner scan = new Scanner(System.in);

public static void main(String[] args) {

String opt = null;

do {

System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME");

int mtype = scan.nextInt();

processMenu(mtype);

System.out.println("press y to continue");

opt = scan.next();

} while(opt.equalsIgnoreCase("y"));

}

public static void processMenu(int mtype) {

switch(mtype) {

case 1:
addOrder();
break;

case 2:
viewOrderByOrderID();
break;

case 3:
viewOrdersByCustName();
break;

default:
System.out.println("Invalid option");

}

}

public static void addOrder() {

Order order = new Order();

System.out.println("Enter Order Date (yyyy-mm-dd)");
String dt = scan.next();
order.setOrderDate(LocalDate.parse(dt));

System.out.println("Enter Order Amount");
order.setOrderAmt(scan.nextDouble());

System.out.println("Enter Customer ID");
int cid = scan.nextInt();

boolean status = dao.addOrder(order, cid);

if(status)
System.out.println("Order Added Successfully");
else
System.out.println("Customer Not Found");

}

public static void viewOrderByOrderID() {

System.out.println("Enter Order ID");

int id = scan.nextInt();

Order order = dao.getOrder(id);

System.out.println(order);

}

public static void viewOrdersByCustName() {

System.out.println("Enter Customer Name");

String name = scan.next();

List<Order> list = dao.getOrders(name);

for(Order o : list) {

System.out.println(o);

}

}

}