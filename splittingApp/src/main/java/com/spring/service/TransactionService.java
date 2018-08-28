package com.spring.service;

import java.util.List;

import com.spring.model.Transaction;

public interface TransactionService {
	void saveTransaction(Transaction usr);
    
    List<Transaction> findAllTransactions();
     
    void deleteTransactionById(int id);
     
    Transaction findById(int id);
     
    void updateTransaction(Transaction usr);
    
    boolean findTransaction(Transaction usr);

}
