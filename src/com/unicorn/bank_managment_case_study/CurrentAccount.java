package com.unicorn.bank_managment_case_study;


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
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void withdrow(double amount) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deposite(double amount) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
