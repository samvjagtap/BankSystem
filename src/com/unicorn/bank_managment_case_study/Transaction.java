package com.unicorn.bank_managment_case_study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	long transactionID;
	Date transactionDate;
	Double transactionAmount;
	String accountType, accountNumber, holderName, transactionType;
	
	public Transaction(long transactionID, Date transactionDate, Double transactionAmount, String accountType, String accountNumber, String holderName, String transactionType) {
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.transactionType = transactionType;
	}
	
	public static Transaction[] addTransaction(Transaction transaction, Transaction[] oldTransactions, int length) {
		if (transaction != null) {
			Transaction[] newTransaction = new Transaction[length + 1];
			System.arraycopy(oldTransactions, 0, newTransaction, 0, length);
			oldTransactions = newTransaction;

			oldTransactions[length] = transaction;
			System.out.println("Transaction added successfully!");
			return oldTransactions;
		} else {
	        System.out.println("Transaction creation failed.");
	        return null;
		}
	}
	
	public static void showDailyTransaction(Transaction[] transactions) {
		
		
	    // Format for date
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	    // Print table header
	    System.out.printf("%-15s %-20s %-20s %-15s %-15s %-15s %-15s %-15s%n", 
	                      "Transaction ID", "Date", "Amount", "Type", "Account No", "Holder Name", "Account Type", "Transaction Type");
	    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

	    // Print each transaction in table format
	    for (Transaction transaction : transactions) {
	        if (transaction != null) { // Check for non-null elements in the array
	            System.out.printf("%-15d %-20s %-20.2f %-15s %-15s %-15s %-15s %-15s%n", 
	                              transaction.transactionID, 
	                              dateFormat.format(transaction.transactionDate),
	                              transaction.transactionAmount,
	                              transaction.accountType,
	                              transaction.accountNumber,
	                              transaction.holderName,
	                              transaction.accountType,
	                              transaction.transactionType); // Print transactionType
	        }
	    }
	}
	
	
}
