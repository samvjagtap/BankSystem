package com.bank.account;

import java.sql.Date;

public class Loan extends Account {

    String accountHolderName;
    double amountloan;
    double intrest;

    public Loan() {
        super();
        // Default constructor
    }

    public Loan(long accountNumber, String accountHolderName, double balance, Date openingDate, Date closeingDate,
                String accountType, boolean isActive) {
        super(accountNumber, accountHolderName, balance, openingDate, closeingDate, accountType, isActive);
        // Parameterized constructor
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

    public double getIntrest() {
        return intrest;
    }

    public void setIntrest(double intrest) {
        this.intrest = intrest;
    }

    @Override
    public void calculateInterest() {
        // Calculate interest for loan
        double interestAmount = (amountloan * intrest) / 100;
        System.out.println("Interest amount: " + interestAmount);
    }

    @Override
    public void withdrow(double amount) {
        // Withdraw logic for loan repayment
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("Repayment successful. Remaining loan balance: " + getBalance());
        } else {
            System.out.println("Invalid repayment amount or insufficient balance.");
        }
    }

    @Override
    public void deposite(double amount) {
        // Deposit logic for loan disbursement
        if (amount > 0) {
            setBalance(getBalance() + amount);
            System.out.println("Loan disbursed successfully. Current balance: " + getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}
