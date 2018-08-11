package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.Customer;
import com.spring.model.Transaction;

@Repository
public class CustomerDaoImpl extends AbstractDao implements CustomerDao {

	@Override
	public void saveCustomer(Customer cust) {
		persist(cust);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAllCustomers() {
		Criteria criteria = getSession().createCriteria(Customer.class);
        return (List<Customer>) criteria.list();
		
	}

	@Override
	public void deleteCustomerById(int id) {
		Query query = getSession().createSQLQuery("delete from Customer where customerId = :customerId");
        query.setInteger("customerId", id);
        query.executeUpdate();
		
	}

	@Override
	public Customer findById(int id) {
		 Criteria criteria = getSession().createCriteria(Customer.class);
	        criteria.add(Restrictions.eq("customerId", id));
	        return (Customer) criteria.uniqueResult();
		
		
	}

	@Override
	public void updateCustomer(Customer cust) {
		 getSession().update(cust);
	}

	@Override
	public boolean findCustomer(Customer cust) {
		 Criteria criteria = getSession().createCriteria(Customer.class);
	        criteria.add(Restrictions.eq("name", cust.getName()));
	        criteria.add(Restrictions.eq("password", cust.getPassword()));
	        
	    	
	        cust = (Customer) criteria.uniqueResult();
	        
	        if(cust == null)
	        	return false;
	        
	        return true;
		
	}

}
