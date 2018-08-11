package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.CustomerDao;
import com.spring.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao dao;

	public void saveCustomer(Customer cust) {
		dao.saveCustomer(cust);
	}

	public List<Customer> findAllCustomers() {
		return dao.findAllCustomers();
	}

	public void deleteCustomerById(int id) {
		dao.deleteCustomerById(id);
	}

	public Customer findById(int id) {
		return dao.findById(id);
	}

	public void updateCustomer(Customer cust) {
		dao.updateCustomer(cust);
	}
	
	public boolean findCustomer(Customer cust) {
		return dao.findCustomer(cust);
	}
}

