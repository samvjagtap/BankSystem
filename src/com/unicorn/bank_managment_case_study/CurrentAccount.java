package com.bank.account;

import java.sql.Date;

public class Current extends Account{

	double overdraftLimit;

	public Current() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Current(long accountNumber, String accountHolderName, double balance, Date openingDate, Date closeingDate,
			String accountType, boolean isActive) {
		super(accountNumber, accountHolderName, balance, openingDate, closeingDate, accountType, isActive);
		// TODO Auto-generated constructor stub
	}



	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	


	@Override
	public void withdrow(double amount) {
	    // Withdrawal logic with overdraft consideration
	    if (amount <= getBalance() + overdraftLimit) { 
	        setBalance(getBalance() - amount); 
	        System.out.println("Withdrawal successful. Remaining balance: " + getBalance());
	        
	    } else {
	        System.out.println("Insufficient balance! Overdraft limit exceeded.");
	       
	    }
	}



	@Override
	public void deposite(double amount) {
	    // Deposit logic
	    if (amount > 0) { 
	        setBalance(getBalance() + amount); 
	        System.out.println("Deposit successful. Current balance: " + getBalance());
	    } else {
	        System.out.println("Invalid deposit amount."); 
	    }
	  
	}



	@Override
	public void calculateInterest() {
		// TODO Auto-generated method stub
		//current account does not have Interest
		
	}

	
	
	
}
