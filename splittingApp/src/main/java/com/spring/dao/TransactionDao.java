package com.spring.dao;

import java.util.List;

import com.spring.model.Transaction;

public interface TransactionDao {
	void saveTransaction(Transaction usr);
    
    List<Transaction> findAllTransactions();
     
    void deleteTransactionById(int id);
     
    Transaction findById(int id);
     
    void updateTransaction(Transaction usr);
    
    boolean findTransaction(Transaction usr);
}
