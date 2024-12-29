package com.unicorn.bank_managment_case_study;

public class Statistics {
	// Attributes to store bank statistics
    private int numberOfAccounts;
    private int numberOfSavingAccounts;
    private int numberOfSalaryAccounts;
    private int numberOfCurrentAccounts;
    private int numberOfLoanAccounts;
    private int numberOfFrozenAccounts;
    private int numberOfClosedAccounts;
    private int numberOfEmiPaid;
    private int numberOfBillsPaid;
    private double bankOpeningBalance;
    private double bankClosingBalance;

    public int getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public void setNumberOfAccounts(int numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;
	}

	public int getNumberOfSavingAccounts() {
		return numberOfSavingAccounts;
	}

	public void setNumberOfSavingAccounts(int numberOfSavingAccounts) {
		this.numberOfSavingAccounts = numberOfSavingAccounts;
	}

	public int getNumberOfSalaryAccounts() {
		return numberOfSalaryAccounts;
	}

	public void setNumberOfSalaryAccounts(int numberOfSalaryAccounts) {
		this.numberOfSalaryAccounts = numberOfSalaryAccounts;
	}

	public int getNumberOfCurrentAccounts() {
		return numberOfCurrentAccounts;
	}

	public void setNumberOfCurrentAccounts(int numberOfCurrentAccounts) {
		this.numberOfCurrentAccounts = numberOfCurrentAccounts;
	}

	public int getNumberOfLoanAccounts() {
		return numberOfLoanAccounts;
	}

	public void setNumberOfLoanAccounts(int numberOfLoanAccounts) {
		this.numberOfLoanAccounts = numberOfLoanAccounts;
	}

	public int getNumberOfFrozenAccounts() {
		return numberOfFrozenAccounts;
	}

	public void setNumberOfFrozenAccounts(int numberOfFrozenAccounts) {
		this.numberOfFrozenAccounts = numberOfFrozenAccounts;
	}

	public int getNumberOfClosedAccounts() {
		return numberOfClosedAccounts;
	}

	public void setNumberOfClosedAccounts(int numberOfClosedAccounts) {
		this.numberOfClosedAccounts = numberOfClosedAccounts;
	}

	public int getNumberOfEmiPaid() {
		return numberOfEmiPaid;
	}

	public void setNumberOfEmiPaid(int numberOfEmiPaid) {
		this.numberOfEmiPaid = numberOfEmiPaid;
	}

	public int getNumberOfBillsPaid() {
		return numberOfBillsPaid;
	}

	public void setNumberOfBillsPaid(int numberOfBillsPaid) {
		this.numberOfBillsPaid = numberOfBillsPaid;
	}

	public double getBankOpeningBalance() {
		return bankOpeningBalance;
	}

	public void setBankOpeningBalance(double bankOpeningBalance) {
		this.bankOpeningBalance = bankOpeningBalance;
	}

	public double getBankClosingBalance() {
		return bankClosingBalance;
	}

	public void setBankClosingBalance(double bankClosingBalance) {
		this.bankClosingBalance = bankClosingBalance;
	}

	// Constructor
    public Statistics(int numberOfAccounts, int numberOfSavingAccounts, int numberOfSalaryAccounts,
                      int numberOfCurrentAccounts, int numberOfLoanAccounts, int numberOfFrozenAccounts,
                      int numberOfClosedAccounts, int numberOfEmiPaid, int numberOfBillsPaid,
                      double bankOpeningBalance, double bankClosingBalance) {
        this.numberOfAccounts = numberOfAccounts;
        this.numberOfSavingAccounts = numberOfSavingAccounts;
        this.numberOfSalaryAccounts = numberOfSalaryAccounts;
        this.numberOfCurrentAccounts = numberOfCurrentAccounts;
        this.numberOfLoanAccounts = numberOfLoanAccounts;
        this.numberOfFrozenAccounts = numberOfFrozenAccounts;
        this.numberOfClosedAccounts = numberOfClosedAccounts;
        this.numberOfEmiPaid = numberOfEmiPaid;
        this.numberOfBillsPaid = numberOfBillsPaid;
        this.bankOpeningBalance = bankOpeningBalance;
        this.bankClosingBalance = bankClosingBalance;
    }

    // Method to print bank statistics    
    public void printStatistics() {
        // Print table header
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s%n",
                "Accounts", "Savings", "Salary", "Current", "Loan", "Frozen", "Closed", "EMIs Paid", "Bills Paid", "Opening Balance", "Closing Balance");
        System.out.println("=".repeat(220));

        // Print each row
        System.out.printf("%-20d %-20d %-20d %-20d %-20d %-20d %-20d %-20d %-20d %-20.2f %-20.2f%n",
        		this.numberOfAccounts, this.numberOfSavingAccounts, this.numberOfSalaryAccounts,
        		this.numberOfCurrentAccounts, this.numberOfLoanAccounts, this.numberOfFrozenAccounts,
        		this.numberOfClosedAccounts, this.numberOfEmiPaid, this.numberOfBillsPaid,
        		this.bankOpeningBalance, this.bankClosingBalance);
    }
}
