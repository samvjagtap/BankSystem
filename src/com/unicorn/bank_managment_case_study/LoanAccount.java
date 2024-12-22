package com.unicorn.bank_managment_case_study;


import java.sql.Date;

public class Loan  extends Account {

	String accountHolderName;
	double amountloan;
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Loan(long accountNumber, String accountHolderName, double balance, Date openingDate, Date closeingDate,
			String accountType, boolean isActive) {
		super(accountNumber, accountHolderName, balance, openingDate, closeingDate, accountType, isActive);
		// TODO Auto-generated constructor stub
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public double getAmountloan() {
		return amountloan;
	}
	public void setAmountloan(double amountloan) {
		this.amountloan = amountloan;
	}
	
	
	
	
}
