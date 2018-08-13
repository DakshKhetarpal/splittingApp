package com.spring.dao;

import java.util.List;

import com.spring.model.Customer;


public interface CustomerDao {
	
	void saveCustomer(Customer cust);
    
    List<Customer> findAllCustomers();
     
    void deleteCustomerById(int id);
     
    Customer findById(int id);
    List<Customer> findByNumberAndPassword(String number,String password);
     
    void updateCustomer(Customer cust);
    
    boolean findCustomer(Customer cust);
}