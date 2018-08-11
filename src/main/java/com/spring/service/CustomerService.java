package com.spring.service;

import java.util.List;

import com.spring.model.Customer;

public interface CustomerService {
	void saveCustomer(Customer usr);
    
    List<Customer> findAllCustomers();
     
    void deleteCustomerById(int id);
     
    Customer findById(int id);
     
    void updateCustomer(Customer usr);
    
    boolean findCustomer(Customer usr);

}