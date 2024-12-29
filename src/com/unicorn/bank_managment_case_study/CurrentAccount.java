package com.unicorn.bank_managment_case_study;

import java.util.Date;

public class CurrentAccount extends Account {
	static double overDraftLimit = 50000.0;
	static final double rate_Of_Interest = 0.9;
	
	public CurrentAccount(String accountNumber, String holderName, double balance, String accountType, Date createdOn) {
		super(accountNumber, holderName, balance, accountType, createdOn);
	}

	@Override
	public void withdraw(double withdrawAmt) {
	    // Calculate the maximum amount that can be withdrawn (balance + overdraft limit)
	    double maxWithdrawableAmount = this.getBalance() + overDraftLimit;

	    // Check if the withdrawal amount exceeds the limit
	    if (withdrawAmt > maxWithdrawableAmount) {
	        System.out.println("Withdrawal denied! Amount exceeds overdraft limit.");
	    } else {
	        // Deduct the amount from the balance
	        this.setBalance(this.getBalance() - withdrawAmt);
	        System.out.println("Withdrawal successful! Remaining balance: " + this.getBalance());
	    }
	}

	@Override
	public double calculateInterest() {
	    // If the balance is positive, calculate interest
	    if (this.getBalance() > 0) {
	        double interest = this.getBalance() * rate_Of_Interest / 100;
	        System.out.println("Interest calculated on balance: " + interest);
	        return interest;
	    } else {
	        // If in overdraft, no interest is accrued
	        System.out.println("No interest accrued as balance is negative or zero.");
	        return 0.0;
	    }
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
