package com.unicorn.bank_managment_case_study;

import java.util.Calendar;
import java.util.Date;

public class SalaryAccount extends Account {
	Date lastTransactionDate = new Date(); // Use LocalDate for date handling
	Date currentDate = new Date(); // Current date
    int accountStatus;
    static final double rate_Of_Interest = 0.7;
    
    public SalaryAccount(String accountNumber, String holderName, double balance, String accountType, int accountStatus, Date createdOn) {
		super(accountNumber, holderName, balance, accountType, createdOn);
		this.accountStatus = accountStatus;
	}
    
    public Date getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(Date lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
    
    public boolean checkAccountStatus() {
    	Date todayDate = new Date();
    	if (getMonthDifference(this.lastTransactionDate, todayDate) >= 2) {
    		this.accountStatus = 0;
    		return true;
    	} else {
    		return false;
    	}
    }
    	
	@Override
	public void withdraw(double withdrawAmt) {
		if (this.accountStatus == 1) {
            if (withdrawAmt <= this.balance) {
                this.balance -= withdrawAmt;
                System.out.println("Withdrawal successful. New balance is: " + this.balance);
            } else {
                System.out.println("Insufficient Balance. Try overdraft.");
            }
        } else {
            System.out.println("Cannot withdraw: Account is inactive.");
        }
	}

	@Override
	public double calculateInterest() {
		if (this.accountStatus == 1) {
            return balance * (rate_Of_Interest / 12); // Calculate interest for one month
        } else {
            System.out.println("Cannot calculate interest: Account is inactive.");
            return 0;
        }
	}
	
	public static int getMonthDifference(Date startDate, Date endDate) {
	    Calendar startCalendar = Calendar.getInstance();
	    Calendar endCalendar = Calendar.getInstance();

	    startCalendar.setTime(startDate);
	    endCalendar.setTime(endDate);

	    int startYear = startCalendar.get(Calendar.YEAR);
	    int startMonth = startCalendar.get(Calendar.MONTH);

	    int endYear = endCalendar.get(Calendar.YEAR);
	    int endMonth = endCalendar.get(Calendar.MONTH);

	    // Calculate the difference in months
	    return Math.abs((endYear - startYear) * 12 + (endMonth - startMonth));
	}
	
	public String toString() {
	    return super.toString(); // Leverage the base class's toString
	}

}
