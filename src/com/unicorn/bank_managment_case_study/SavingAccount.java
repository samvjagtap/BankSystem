package com.unicorn.bank_managment_case_study;

import java.util.Date;

public class SavingAccount extends Account {
	static final double min_Balance = 10000;
	static final double rate_Of_Interest = 0.5;
	
	public SavingAccount(String accountNumber, String holderName, double balance, String accountType, Date createdOn) {
		super(accountNumber, holderName, balance, accountType, createdOn);
	}
	
	@Override
	public void withdraw(double withdrawAmt) {
		if (this.balance - withdrawAmt > min_Balance) {
			this.balance -= withdrawAmt;
			System.out.println("Withdrawing money... " + withdrawAmt);
            System.out.println("Updated Balance: " + this.balance);
		} else {
			System.out.println("Not possible to withdraw of "+withdrawAmt+" Amount Minimum balance requirement not met.");
		}
	}

	@Override
	public double calculateInterest() {
		return this.balance * rate_Of_Interest / 12;
	}

	@Override
	public boolean checkAccountStatus() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
	    return super.toString(); // Leverage the base class's toString
	}
	
}
