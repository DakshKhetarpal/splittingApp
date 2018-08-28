package com.spring.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONUSER")
public class Transaction implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	
	
	private int payeeId;

	private int  beneficiaryId;
	private int  amount ;
	private int noOfSplitters;
	
	
	public Transaction( int payeeId, int beneficiaryId, int amount, int noOfSplitters) {
		this.payeeId = payeeId;
		this.beneficiaryId = beneficiaryId;
		this.amount = amount;
		this.noOfSplitters = noOfSplitters;
	}
	
	public  Transaction()
	{
		
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(int payeeId) {
		this.payeeId = payeeId;
	}
	public int getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getNoOfSplitters() {
		return noOfSplitters;
	}
	public void setNoOfSplitters(int noOfSplitters) {
		this.noOfSplitters = noOfSplitters;
	}	
	
}