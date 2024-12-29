package com.unicorn.bank_managment_case_study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanAccount extends Account {
	protected static final double min_Balance = 0;
	protected static final double LoanInterestRate = 8.0;
	protected static final double interestRate = 0.4;
	protected double loanAmount;
	protected double remainingLoanAmount;
	protected double loanEmi;
	protected int noOfEmi;

    public LoanAccount(String accountNumber, String holderName, double balance, String accountType, double loanAmount, double remainingLoanAmount, double loanEmi, int noOfEmi, Date createdOn) {
        super(accountNumber, holderName, balance, accountType, createdOn);
        this.setRemainingLoanAmount(remainingLoanAmount);
        this.loanAmount = loanAmount;
        this.loanEmi = loanEmi;
        this.noOfEmi = noOfEmi;
    }
    
    public static double getLoanInterestRate() {
    	return LoanInterestRate;
    }
    
    public double getRemainingLoanAmount() {
		return remainingLoanAmount;
	}

	public void setRemainingLoanAmount(double remainingLoanAmount) {
		this.remainingLoanAmount = remainingLoanAmount;
	}

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    public double getLoanEmi() {
        return loanEmi;
    }

    public void setLoanEmi(int loanEmi) {
        this.loanEmi = loanEmi;
    }
    
    public double calculateMonthlyLoanInterest() {
        // Check if the remaining loan amount is positive
        if (this.remainingLoanAmount > 0) {
            // Calculate monthly interest
            double monthlyInterest = Math.round( ((this.loanAmount * LoanAccount.getLoanInterestRate()) / (12 * 100)) * 100.0) / 100.0;
//            System.out.printf("Monthly interest on remaining loan amount %.2f is: %.2f%n", this.remainingLoanAmount, monthlyInterest);
            return monthlyInterest;
        } else {
            System.out.println("No interest calculated as the remaining loan amount is zero or negative.");
            return 0.0;
        }
    }
    
    
    public static void showLoanAccountDetails(Account[] accounts) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Format for displaying only the date
        
        // Header row for LoanAccount details
        System.out.printf("%-12s %-20s %-10s %-10s %-15s %-20s %-20s %-15s %-20s%n", 
                          "Account No", "Holder Name", "Balance", "Type", "Loan Amount", 
                          "Remaining Loan", "Loan EMI", "Interest Rate", "Created On");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

        
        // Display the details of the single LoanAccount
        for (Account account : accounts) {
			if (account instanceof LoanAccount) {
				LoanAccount loanAccount = (LoanAccount) account;
				System.out.printf("%-12s %-20s %-10.2f %-10s %-15.2f %-20.2f %-20.2f %-15.2f %-20s%n", 
						loanAccount.getAccountNumber(), loanAccount.getHolderName(), loanAccount.getBalance(), 
						loanAccount.getAccountType(), loanAccount.getLoanAmount(), 
						loanAccount.getRemainingLoanAmount(), loanAccount.getLoanEmi(), 
                        getInterestRate(), dateFormat.format(loanAccount.getCreatedOn()));
			}
		}
        
    }
    
    public boolean payEmi(double emiAmount, int payMethod) {
    	if (payMethod == 1) {
    		if ((this.getBalance() - emiAmount) >= min_Balance) {
    			this.setBalance(this.getBalance() - emiAmount);
    			this.setRemainingLoanAmount(this.getRemainingLoanAmount() - emiAmount);
        		return true;
    		} else {
    			System.out.println("Your Account has not sufficient Balance to pay EMI");
    			return false;
    		}
    	} else if (payMethod == 2) {
    		this.setRemainingLoanAmount(this.getRemainingLoanAmount() - emiAmount);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean verifyEmiAmmount(double emiAmount) {
    	if (emiAmount >= this.getLoanEmi() + (LoanInterestRate / 100) * this.getLoanAmount()) {
    		return true;
    	} else {
    		return false;
    	}
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
    	return this.balance * interestRate / 12;
    }

    @Override
    public boolean checkAccountStatus() {
        return true;
    }
    
    public String toString() {
        return super.toString(); // Leverage the base class's toString
    }

}
