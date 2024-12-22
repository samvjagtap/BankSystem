package com.unicorn.bank_managment_case_study;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bank {
	String bankName;
	Scanner scanner = new Scanner(System.in);
	static Transaction[] transactions = new Transaction[2];
	
	public Bank(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public static long getCurrentDateTimeAsLong() {
	    Date now = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    String dateTimeString = formatter.format(now);
	    return Long.parseLong(dateTimeString);
	}

	public void bankManagmentSystem() {
		// Array of Account references
        Account[] accounts = new Account[10];
        int bankChoice;

        // Store SavingAccount objects
        accounts[0] = new SavingAccount("SAV001", "John Doe", 15000.0, "Saving", new Date(2023 - 1900, 0, 1));
        accounts[1] = new SavingAccount("SAV002", "Jane Smith", 15000.0, "Saving", new Date(2023 - 1900, 0, 1));
        accounts[2] = new SavingAccount("SAV003", "Mark Johnson", 22000.0, "Saving", new Date(2023 - 1900, 1, 1));
        accounts[3] = new SavingAccount("SAV004", "Emily Davis", 17500.0, "Saving", new Date(2023 - 1900, 2, 1));
        accounts[4] = new SavingAccount("SAV005", "Michael Wilson", 12000.0, "Saving", new Date(2023 - 1900, 3, 1));

        // Adding 5 SalaryAccount objects
        accounts[5] = new SalaryAccount("SAL001", "Alice Johnson", 20000.0, "Salary", 1, new Date(2023 - 1900, 5, 1));
        accounts[6] = new SalaryAccount("SAL002", "Bob Brown", 35000.0, "Salary", 1, new Date(2023 - 1900, 6, 1));
        accounts[7] = new SalaryAccount("SAL003", "Charlie Evans", 40000.0, "Salary", 1, new Date(2023 - 1900, 7, 1));
        accounts[8] = new SalaryAccount("SAL004", "David Clark", 50000.0, "Salary", 1, new Date(2023 - 1900, 8, 1));
        accounts[9] = new SalaryAccount("SAL005", "Sophia White", 45000.0, "Salary", 1, new Date(2024 - 1900, 11, 1));
        
        // Add sample transactions
        transactions[0] = new Transaction(178567389, new Date(), 5000.0, "Saving", "5000", "John Doe", "Deposit");
        transactions[1] = new Transaction(259859968, new Date(), 2000.0, "Salary", "4500", "Jane Smith", "Deposit");

		
		while(true) {
			System.out.println("***** Menu *****");
			System.out.println("1]. Over Counter Activites");
			System.out.println("2]. Account Lifecycle");
			System.out.println("3]. Interest Calculation");
			System.out.println("4]. Daily Report");
			System.out.println("5]. Exit");
			System.out.print("Enter Your Choice: ");
			bankChoice = scanner.nextInt();
			
			switch (bankChoice) {
				case 1: {
					this.overCounterActivities(accounts, transactions);
					break;
				}
								
				case 2: {
					this.accountLifecycleManagment(accounts);
					break;
				}
				
				
				case 3: {
					this.calCulateInterest(accounts);
					break;
				}
				
				case 4: {
					Transaction.showDailyTransaction(transactions);
					break;
				}
				
				case 5: {
					System.out.println("Exiting....");
					return;
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + bankChoice);
			}
			
		}
	}
	
	
	// Calculate the interest: start
	public void calCulateInterest(Account[] accounts) {
		String accountNumber;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	            System.out.println("Total Interest: " + account.calculateInterest());
	            return;
	        }
	    }
	}
	// Calculate the interest: end
	
	// Over the counter activities Management: Start
	public void overCounterActivities(Account[] account, Transaction[] transactions) {
		int counterActivityChoice;
		while (true) {
			System.out.println("***** Over Counter Activites *****");
			System.out.println("1]. Withdraw Money");
			System.out.println("2]. Deposit Money");
			System.out.println("3]. Bill Payments");
			System.out.println("4]. Check Account Balance");
			System.out.println("5]. Exit");
			System.out.print("Enter Your Choice: ");
			counterActivityChoice = scanner.nextInt();
			
			switch (counterActivityChoice) {
				case 1:{
					this.withdrawMoney(account, transactions);
					break;
				}
				
				case 2: {
					this.depositMoney(account, transactions);
					break;
				}
				
				case 3: {
					this.makeBillPayment(account, transactions);
					break;
				}
				
				case 4: {
					this.checkBalance(account);
					break;
				}
				
				case 5: {
					System.out.println("Exiting....");
					return;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + counterActivityChoice);
			}
		}
	}
	
	public void withdrawMoney(Account[] accounts, Transaction[] transactions) {
		String accountNumber;
		double withdrawalAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		System.out.print("Enter Amount to Withdraw: ");
		withdrawalAmount = scanner.nextDouble();
		
		if (withdrawalAmount <= 0) {
			System.out.println("Please Enter valid Amount");
			return;
		}
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		account.withdraw(withdrawalAmount);
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else if (account.getAccountType().equals("Saving")) {
	        		account.withdraw(withdrawalAmount);
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void checkBalance(Account[] accounts) {
		String accountNumber;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else if (account.getAccountType().equals("Saving")) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void makeBillPayment(Account[] accounts, Transaction[] transactions) {
		String accountNumber;
		double withdrawalAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		
	        		System.out.println("Enter Bill Name: ");
		        	String billName = scanner.nextLine();
		        	System.out.print("Enter Amount to Pay: ");
		    		withdrawalAmount = scanner.nextDouble();
		    		
		    		if (withdrawalAmount <= 0) {
		    			System.out.println("Please Enter valid Amount");
		    			return;
		    		}
		    		
		    		account.withdraw(withdrawalAmount);
		    		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Bill Payment"), transactions, transactions.length);
		    		System.out.println("Bill Payment Done Successful...");
		    		
	        	} else if (account.getAccountType().equals("Saving")) {
	        		
	        		System.out.println("Enter Bill Name: ");
		        	String billName = scanner.nextLine();
		        	System.out.print("Enter Amount to Pay: ");
		    		withdrawalAmount = scanner.nextDouble();
		    		
		    		if (withdrawalAmount <= 0) {
		    			System.out.println("Please Enter valid Amount");
		    			return;
		    		}
		    		
		    		account.withdraw(withdrawalAmount);
		    		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Bill Payment"), transactions, transactions.length);
		    		System.out.println("Bill Payment Done Successful...");
		    		
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void depositMoney(Account[] accounts, Transaction[] transactions) {
		String accountNumber;
		double depositAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		System.out.print("Enter Amount to Deposit: ");
		depositAmount = scanner.nextDouble();
		
		if (depositAmount <= 0) {
			System.out.println("Please Enter valid Amount");
			return;
		}
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		account.deposit(depositAmount);
		            Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), depositAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Deposite"), transactions, transactions.length);
		            System.out.println("Account Balance: "+account.getBalance());
	        	} else if (account.getAccountType().equals("Salary")) {
	        		account.deposit(depositAmount);
		            Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), depositAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Deposite"), transactions, transactions.length);
		            System.out.println("Account Balance: "+account.getBalance());
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	// Over the counter activities Management: End
	
	
	// Account Life cycle Management: Start
	public void accountLifecycleManagment(Account[] account) {
		int accountLifecycleChoice;
		while (true) {
			System.out.println("***** Account Lifecycle *****");
			System.out.println("1]. Create a Account");
			System.out.println("2]. Show Account Details");
			System.out.println("3]. Close Account");
			System.out.println("4]. Update Account");
			System.out.println("5]. Exit");
			System.out.print("Enter Your Choice: ");
			accountLifecycleChoice = scanner.nextInt();
			
			switch (accountLifecycleChoice) {
				case 1: {
					Account newAccount = this.accountCreation(account);
					if (newAccount != null) {
						Account[] newAccounts = new Account[account.length + 1];
						System.arraycopy(account, 0, newAccounts, 0, account.length);
						account = newAccounts;
						
						account[account.length - 1] = newAccount;
			            System.out.println("Account added successfully!");
					} else {
			            System.out.println("Account creation failed.");
			        }
					break;
				}
				case 2: {
					this.showAccountDetails(account);
					break;
				}
				case 3: {
					System.out.print("Enter Account No: ");
					scanner.nextLine();
					String accountNumber = scanner.nextLine();
					
					account = this.deleteAccount(accountNumber, account);
					break;
				}
				case 4: {
					System.out.print("Enter Account No: ");
					scanner.nextLine();
					String accountNumber = scanner.nextLine();
					this.updateAccountDetails(accountNumber, account);
					break;
				}
				case 5: {
					System.out.println("Exiting...");
					return;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + accountLifecycleChoice);
			}
		}
	}
	
	public boolean isAccountNumberPresent(String accountNumber, Account[] accounts) {
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	            return true; // Account number is found
	        }
	    }
	    return false; // Account number is not found
	}
	
	public Account accountCreation(Account[] account) {
		System.out.println("Bank Account Type");
		System.out.println("1]. Saving Account");
		System.out.println("2]. Salary Account");
		System.out.println("3]. Current Account");
		System.out.println("4]. Loan Account");
		System.out.print("Which Type of Account you want to open: ");
		int accountType = scanner.nextInt();
		
		if (accountType == 1) {
			String accountNumber, holderName;
		    double balance;
		    Date createdOn = new Date();
		    
		    System.out.print("Enter Account Number: ");
		    scanner.nextLine();
		    accountNumber = scanner.nextLine();
		    while(this.isAccountNumberPresent(accountNumber, account)) {
		    	System.out.println("This Account Already Taken");
		    	System.out.print("Enter Account Number: ");
			    accountNumber = scanner.nextLine();
		    }
		    
		    System.out.print("Enter Account Holder Name: ");
		    holderName = scanner.nextLine();
		    
		    System.out.println("Minimun 10000 balance required for account creation");
		    System.out.print("Enter Opening Balance: ");
		    balance = scanner.nextDouble();
		    
		    while(balance <= 10000) {
		    	System.out.println("Balance is less than 10000");
		    	System.out.println("Minimun 10000 balance required for account creation");
		    	System.out.print("Enter Opening Balance: ");
			    balance = scanner.nextDouble();
		    }
		    
		    return new SavingAccount(accountNumber, holderName, balance, "Saving", new Date());
		} else if (accountType == 2) {
			String accountNumber, holderName;
		    double balance;
		    Date createdOn = new Date();
		    
		    System.out.print("Enter Account Number: ");
		    scanner.nextLine();
		    accountNumber = scanner.nextLine();
		    
		    System.out.print("Enter Account Holder Name: ");
		    scanner.nextLine();
		    holderName = scanner.nextLine();
		    
		    System.out.print("Enter Opening Balance: ");
		    balance = scanner.nextDouble();
		    
		    return new SalaryAccount(accountNumber, holderName, balance, "Salary", 1, new Date());
		} else if (accountType == 3) {
			System.out.println("Coming soon");
			return null;
		} else if (accountType == 4) {
			System.out.println("Coming soon");
			return null;
		} else {
			System.out.println("Invalid Choice");
			return null;
		}
	}
	
	public void showAccountDetails(Account[] accounts) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Format for displaying only the date

	    System.out.printf("%-12s %-20s %-10s %-10s %-20s %-20s%n", "Account No", "Holder Name", "Balance", "Type", "Created On", "Last Transaction");
	    System.out.println("---------------------------------------------------------------------------------------------------------------");

	    for (Account account : accounts) {
	        if (account instanceof SalaryAccount) {
	            SalaryAccount salary = (SalaryAccount) account;
	            System.out.printf("%-12s %-20s %-10.2f %-10s %-20s %-20s%n", 
	                              salary.getAccountNumber(), salary.getHolderName(), salary.getBalance(), 
	                              salary.getAccountType(), dateFormat.format(salary.getCreatedOn()), 
	                              dateFormat.format(salary.getLastTransactionDate()));
	        } else if (account instanceof SavingAccount) {
	            System.out.printf("%-12s %-20s %-10.2f %-10s %-20s %-20s%n", 
	                              account.getAccountNumber(), account.getHolderName(), account.getBalance(), 
	                              account.getAccountType(), dateFormat.format(account.getCreatedOn()), dateFormat.format(account.getLastTransactionDate()));
	        }
	    }
	}
	
	public Account[] deleteAccount(String accountNumber, Account[] accounts) {
		double currentBalanceWithInterest = 0;
		int indexToDelete = -1;
		for (int index = 0; index < accounts.length; index++) {
            if (accounts[index] != null && accounts[index].getAccountNumber().equals(accountNumber)) {
            	currentBalanceWithInterest = accounts[index].getBalance() + accounts[index].calculateInterest();
            	indexToDelete = index;
                break;
            }
        }
		if (indexToDelete == -1) {
            System.out.println("Account with number " + accountNumber + " not found.");
            return accounts; // Return the original array if account is not found
        } else {
        	for (int index = indexToDelete; index < accounts.length - 1; index++) {
            	accounts[index] = accounts[index + 1];
        	}
        }
		accounts[accounts.length - 1] = null;
		
		System.out.println("Customer Balance with Interest: " + currentBalanceWithInterest);
		System.out.println("Account with number " + accountNumber + " Closed successfully.");
	    return accounts;
	}
	
	public void updateAccountDetails(String accountNumber, Account[] accounts) {
		Account accountToEdit = null;
		// Find the account
		for (Account account : accounts) {
			if (account != null && account.getAccountNumber().equals(accountNumber)) {
				accountToEdit = account;
	            break;
	        }
	    }

		if (accountToEdit == null) {
			System.out.println("Account with number " + accountNumber + " not found.");
			return;
		}
		
		System.out.println("\nEnter new details (press Enter to skip):");
        System.out.print("Holder Name [" + accountToEdit.getHolderName() + "]: ");
        String holderName = scanner.nextLine();
        if (!holderName.isEmpty()) {
            accountToEdit.setHolderName(holderName);
        }

        System.out.print("Account Type [" + accountToEdit.getAccountType() + "]: ");
        String accountType = scanner.nextLine();
        if (!accountType.isEmpty()) {
	        if (accountType.equals("Salary")) {
	        	System.out.println("You need to deposit " + (10000 - accountToEdit.getBalance()));
	        	System.out.print("Enter Amount to deposit: ");
	        	double amount = scanner.nextDouble();
	        	if (accountToEdit.getBalance() + amount >= 10000) {
	        		accountToEdit.deposit(amount);
	        		accountToEdit.setAccountType(accountType);
	        	} else {
	        		System.out.println("Enter correct amount");
	        	}
	        }
        }
        

        System.out.println("\nAccount details updated successfully.");
	}
	// Account Life cycle Management: End
}
