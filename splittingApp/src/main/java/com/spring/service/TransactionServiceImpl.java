package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.TransactionDao;
import com.spring.model.Transaction;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionDao dao;

	public void saveTransaction(Transaction usr) {
		dao.saveTransaction(usr);
	}

	public List<Transaction> findAllTransactions() {
		return dao.findAllTransactions();
	}

	public void deleteTransactionById(int id) {
		dao.deleteTransactionById(id);
	}

	public Transaction findById(int id) {
		return dao.findById(id);
	}

	public void updateTransaction(Transaction User) {
		dao.updateTransaction(User);
	}
	
	public boolean findTransaction(Transaction usr) {
		return dao.findTransaction(usr);
	}
}
