package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.Transaction;
 
@Repository
public class TransactionDaoImpl extends AbstractDao implements TransactionDao{
 
    public void saveTransaction(Transaction usr) {
        persist(usr);
    }
 
    @SuppressWarnings("unchecked")
    public List<Transaction> findAllTransactions() {
        Criteria criteria = getSession().createCriteria(Transaction.class);
        return (List<Transaction>) criteria.list();
    }
 
    public void deleteTransactionById(int id) {
        Query query = getSession().createSQLQuery("delete from Transaction where transactionId = :transactionId");
        query.setInteger("transactionId", id);
        query.executeUpdate();
    }
 
     
    public Transaction findById(int id){
        Criteria criteria = getSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("transactionId", id));
        return (Transaction) criteria.uniqueResult();
    }
     
    public void updateTransaction(Transaction usr){
        getSession().update(usr);
    }
    
    public boolean findTransaction(Transaction usr) {
        Criteria criteria = getSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("transactionId", usr.getTransactionId()));
    	
        usr = (Transaction) criteria.uniqueResult();
        
        if(usr == null)
        	return false;
        
        return true;
    }
}