package com.unicorn.bank_managment_case_study;

import java.util.Date;

public abstract class Account {
	protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected String accountType;
    protected Date createdOn, lastTransactionOn;
    
    public Account(String accountNumber, String holderName, double balance, String accountType, Date createdOn) {
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.balance = balance;
		this.accountType = accountType;
//		this.createdOn = new Date();
		this.createdOn = createdOn;
		this.lastTransactionOn = new Date();
	}
    
    public void setLastTransactionOn(Date lastTransactionOn) {
    	this.lastTransactionOn = lastTransactionOn;
    }
    public Date getLastTransactionDate() {
    	return this.lastTransactionOn;
    }
    
    public void setCreatedOn(Date createdOn) {
    	this.createdOn = createdOn;
    }
    public Date getCreatedOn() {
    	return this.createdOn;
    }
    
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	public void deposit(double depositAmt) {
		this.balance += depositAmt;
	}
	
	public abstract void withdraw(double withdrawAmt);
	public abstract double calculateInterest();
	public abstract boolean checkAccountStatus();
    
    
	
    
}
