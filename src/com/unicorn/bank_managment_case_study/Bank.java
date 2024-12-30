package com.unicorn.bank_managment_case_study;
import java.text.DecimalFormat;
// composition = has a relation
// aggregation = that class can work without that thing
// Association = types = indirection, bidirection 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Bank {
	String bankName;
	Scanner scanner = new Scanner(System.in);
	static final double loanAmountLimit = 150000;
	static Transaction[] transactions = new Transaction[2];
	static Account[] accounts = new Account[11];
	Statistics stats = new Statistics(10, 5, 5, 0, 0, 0, 0, 0, 0, 271500, 271500);
	DecimalFormat df = new DecimalFormat("#.##");
	
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
        int bankChoice;

        // Store SavingAccount objects
        accounts[0] = new SavingAccount(AccountFactory.generateAccountNumber("Saving"), "John Doe", 15000.0, "Saving", new Date(2023 - 1900, 0, 1));
        accounts[1] = new SavingAccount(AccountFactory.generateAccountNumber("Saving"), "Jane Smith", 15000.0, "Saving", new Date(2023 - 1900, 0, 1));
        accounts[2] = new SavingAccount(AccountFactory.generateAccountNumber("Saving"), "Mark Johnson", 22000.0, "Saving", new Date(2023 - 1900, 1, 1));
        accounts[3] = new SavingAccount(AccountFactory.generateAccountNumber("Saving"), "Emily Davis", 17500.0, "Saving", new Date(2023 - 1900, 2, 1));
        accounts[4] = new SavingAccount(AccountFactory.generateAccountNumber("Saving"), "Michael Wilson", 12000.0, "Saving", new Date(2023 - 1900, 3, 1));

        // Adding 5 SalaryAccount objects
        accounts[5] = new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), "Alice Johnson", 20000.0, "Salary", 1, new Date(2023 - 1900, 5, 1));
        accounts[6] = new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), "Bob Brown", 35000.0, "Salary", 1, new Date(2023 - 1900, 6, 1));
        accounts[7] = new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), "Charlie Evans", 40000.0, "Salary", 1, new Date(2023 - 1900, 7, 1));
        accounts[8] = new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), "David Clark", 50000.0, "Salary", 1, new Date(2023 - 1900, 8, 1));
        accounts[9] = new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), "Sophia White", 45000.0, "Salary", 1, new Date(2024 - 1900, 11, 1));
        
        // Adding 5 SalaryAccount objects
        accounts[10] = new LoanAccount(AccountFactory.generateAccountNumber("Loan"), "Robert Pattison", 20000.0, "Loan", 100000.0, 100000.0, 1000, 10, new Date(2023 - 1900, 10, 1));

        // Add sample transactions
        transactions[0] = new Transaction(178567389, new Date(), 5000.0, "Saving", "SAV001", "John Doe", "Deposit");
        transactions[1] = new Transaction(259859968, new Date(), 2000.0, "Salary", "SAV002", "Jane Smith", "Deposit");
		
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
					this.overCounterActivities();
					break;
				}
								
				case 2: {
					this.accountLifecycleManagment();
					break;
				}
				
				
				case 3: {
					this.calCulateInterest();
					break;
				}
				
				case 4: {
					System.out.println("Bank Statistics:");
					int frozenAccCount = 0;
					for (Account account : accounts) {
						if (account.checkAccountStatus()) {
							frozenAccCount++;
						}
					}
					stats.setNumberOfFrozenAccounts(frozenAccCount);
					stats.printStatistics();
					
					System.out.println();
					
					System.out.println("Transaction Details");
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
	public void calCulateInterest() {
		String accountNumber;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		for (Account account : Bank.accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	            System.out.println("Total Interest: " + account.calculateInterest());
	            return;
	        }
	    }
	}
	// Calculate the interest: end
	
	// Over the counter activities Management: Start
	public void overCounterActivities() {
		int counterActivityChoice;
		while (true) {
			System.out.println("***** Over Counter Activites *****");
			System.out.println("1]. Withdraw Money");
			System.out.println("2]. Deposit Money");
			System.out.println("3]. Bill Payments");
			System.out.println("4]. Check Account Balance");
			System.out.println("5]. Pay EMI");
			System.out.println("6]. Loan Application");
			System.out.println("7]. Exit");
			System.out.print("Enter Your Choice: ");
			counterActivityChoice = scanner.nextInt();
			
			switch (counterActivityChoice) {
				case 1:{
					this.withdrawMoney();
					break;
				}
				
				case 2: {
					this.depositMoney();
					break;
				}
				
				case 3: {
					this.makeBillPayment();
					break;
				}
				
				case 4: {
					this.checkBalance();
					break;
				}
				
				case 5: {
					this.payEmi();
					break;
				}
				
				case 6: {
					this.loanApplication();
					break;
				}
				
				case 7: {
					System.out.println("Exiting....");
					return;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + counterActivityChoice);
			}
		}
	}
	
	public void loanApplication() {
		System.out.println("Existing Account: 1/0: ");
		int existingAccount = scanner.nextInt();
		
		if (existingAccount == 1) {
			System.out.println("Enter Account Number: ");
			String accountNumber = scanner.nextLine();
			
			for (Account account : Bank.accounts) {
				if (account != null && account.getAccountNumber().equals(accountNumber)) {
					if (account instanceof SalaryAccount) {
						SalaryAccount newAccount = (SalaryAccount) account;
						loanAccountCreation(newAccount, accountNumber);
					} else if (account instanceof SavingAccount) {
						SavingAccount newAccount = (SavingAccount) account;
						loanAccountCreation(newAccount, accountNumber);
					} else if (account instanceof CurrentAccount) {
						CurrentAccount newAccount = (CurrentAccount) account;
						loanAccountCreation(newAccount, accountNumber);
					}
				}
			}
		} else {
			Account newAccount = this.accountCreation();
			if (newAccount != null) {
				Account[] newAccounts = new Account[Bank.accounts.length + 1];
				System.arraycopy(Bank.accounts, 0, newAccounts, 0, Bank.accounts.length);
				Bank.accounts = newAccounts;
				
				Bank.accounts[Bank.accounts.length - 1] = newAccount;
	            System.out.println("Account added successfully!");
//	            this.showAccountDetails();
			} else {
	            System.out.println("Account creation failed.");
	        }
		}
	}
	
	public void loanAccountCreation(Account account, String accountNumber) {
		System.out.println("Enter loan Amount: ");
		double loanAmount = scanner.nextDouble();
		loanAmount = Math.round(loanAmount * 100.0) / 100.0;
		
		if (loanAmount >= stats.getBankClosingBalance() && loanAmount >= loanAmountLimit) {
			System.out.println("Bank is not able to provide that much amount");
			return;
		}
		
		System.out.println("Enter duration in months: ");
		int noOfEmi = scanner.nextInt();
		
		double monthlyRate = Math.round( (loanAmount * LoanAccount.getInterestRate()) / (12 * 100) * 100.0 ) / 100.0;
        double emi = Math.round( (loanAmount / noOfEmi) * 100.0) / 100.0;
            
        System.out.println("Your Emi for every month is: "+emi);
        System.out.println("Interest for first month is "+ monthlyRate +" for Amount: "+loanAmount);
        System.out.println("Total of first EMI is: "+(emi+monthlyRate));
		
		Account newAccount = new LoanAccount(accountNumber, account.getHolderName() , account.getBalance(), "Loan", loanAmount, loanAmount, emi, noOfEmi, new Date());
		
		Account[] newAccounts = new Account[Bank.accounts.length + 1];
		System.arraycopy(Bank.accounts, 0, newAccounts, 0, Bank.accounts.length);
		Bank.accounts = newAccounts;
		
		Bank.accounts[Bank.accounts.length - 1] = newAccount;
		this.deleteAccount(accountNumber);
		System.out.println("Account added successfully!");
	
		// Managing closing balance stats 
		stats.setBankClosingBalance(stats.getBankClosingBalance() - loanAmount);
	}
	
	public void payEmi() {
		String accountNumber;
		double emiAmount;
		int indexToPayEMI = -1;
		
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		for (int index = 0; index < Bank.accounts.length; index++) {
            if (Bank.accounts[index] != null && Bank.accounts[index].getAccountNumber().equals(accountNumber) && Bank.accounts[index].getAccountType().equals("Loan")) {
            	indexToPayEMI = index;
            	LoanAccount loanAcc = (LoanAccount) Bank.accounts[index];
            	
            	System.out.println("Enter EMI Amount: ");
            	emiAmount = scanner.nextDouble();
            	
            	while (!loanAcc.verifyEmiAmmount(emiAmount)) {
            		System.out.println("Emi Amount should greater than or equal to "+df.format((loanAcc.getLoanEmi() + loanAcc.calculateMonthlyLoanInterest())));
            		System.out.print("Enter Loan Amount: ");
                	emiAmount = scanner.nextDouble();
            	}
            	
            	System.out.println("choose Pay Method");
            	System.out.println("1]. Pay by Account Balance");
            	System.out.println("2]. Pay by Cash");
            	System.out.print("Enter your choice: ");
            	int payMethod = scanner.nextInt();
            	
            	if (loanAcc.payEmi(emiAmount, payMethod)) {
            		
            		System.out.println("EMI paid Successfuly...");
            		if (payMethod == 1) {
            			System.out.println("Want to Check Balance 1/0");
                		int balanceCheckChoice = scanner.nextInt();
                		if (balanceCheckChoice == 1) {
                			System.out.println("Your Account balance is: "+loanAcc.getBalance());
                		}
            		} 
            		System.out.println("Want to Check Remaining Loan 1/0");
            		int loanAmtCheckChoice = scanner.nextInt();
            		if (loanAmtCheckChoice == 1) {
            			System.out.println("Your Remaining Loan Amount is: "+loanAcc.getRemainingLoanAmount());
            		}
            		
            		// Account Pay EMI statistics Managed
            		stats.setNumberOfEmiPaid(stats.getNumberOfEmiPaid() + 1);
            		
            		// Managing Stats for closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() + (loanAcc.getLoanEmi() + loanAcc.calculateMonthlyLoanInterest()));
            		return;
            	} else {
            		System.out.println("EMI not paid!");
            		return;
            	}
            }
        }
		if (indexToPayEMI == -1) {
			System.out.println("Account not Found...");
		}
	}
	
	public void withdrawMoney() {
		String accountNumber;
		double withdrawalAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		System.out.print("Enter Amount to Withdraw: ");
		withdrawalAmount = scanner.nextDouble();
		
		if (withdrawalAmount <= 0) {
			System.out.println("Please Enter valid Amount");
			return;
		}
		
		for (Account account : Bank.accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		account.withdraw(withdrawalAmount);
	        		account.setLastTransactionOn(new Date());
	        		// Managing stats for Closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() - withdrawalAmount);
	        		// Maintaining Transaction History
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else if (account.getAccountType().equals("Saving")) {
	        		account.withdraw(withdrawalAmount);
	        		// Managing stats for Closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() - withdrawalAmount);
	        		// Maintaining Transaction History
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else if (account.getAccountType().equals("Current")) {
	        		account.withdraw(withdrawalAmount);
	        		// Managing stats for Closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() - withdrawalAmount);
	        		// Maintaining Transaction History
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else if (account.getAccountType().equals("Loan")) {
	        		account.withdraw(withdrawalAmount);
	        		// Managing stats for Closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() - withdrawalAmount);
	        		// Maintaining Transaction History
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), withdrawalAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Withdraw"), transactions, transactions.length);
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void checkBalance() {
		String accountNumber;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		for (Account account : accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else if (account.getAccountType().equals("Saving")) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else if (account.getAccountType().equals("Current")) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else if (account.getAccountType().equals("Loan")) {
	        		System.out.println("Balance of Account No: " + accountNumber + " is: " + account.getBalance()); 
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void makeBillPayment() {
		String accountNumber;
		double withdrawalAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		for (Account account : Bank.accounts) {
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
		    		
		    		// Account Bill Payment statistics Managed
		    		stats.setNumberOfBillsPaid(stats.getNumberOfBillsPaid() + 1);
		    		
		    		
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
		    		
		    		// Account Bill Payment statistics Managed
		    		stats.setNumberOfBillsPaid(stats.getNumberOfBillsPaid() + 1);
		    		
		    		
	        	} else if (account.getAccountType().equals("Current")) {
	        		
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
		    		
		    		// Account Bill Payment statistics Managed
		    		stats.setNumberOfBillsPaid(stats.getNumberOfBillsPaid() + 1);
		    		
		    		
	        	} else if (account.getAccountType().equals("Loan")) {
	        		
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
		    		
		    		// Account Bill Payment statistics Managed
		    		stats.setNumberOfBillsPaid(stats.getNumberOfBillsPaid() + 1);
		    		
		    		
	        	} else {
	        		System.out.println("Your Salary Account is Forzen");
	        	}
	            return;
	        }
	    }
	}
	
	public void depositMoney() {
		String accountNumber;
		double depositAmount;
		System.out.print("Enter Account Number: ");
		scanner.nextLine();
		accountNumber = scanner.nextLine();
		
		if (!this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
			System.out.println("Account is not Present");
			return;
		}
		
		System.out.print("Enter Amount to Deposit: ");
		depositAmount = scanner.nextDouble();
		
		if (depositAmount <= 0) {
			System.out.println("Please Enter valid Amount");
			return;
		}
		
		for (Account account : Bank.accounts) {
	        if (account != null && account.getAccountNumber().equals(accountNumber)) {
	        	
	        	if (account.getAccountType().equals("Salary") && !account.checkAccountStatus()) {
	        		account.deposit(depositAmount);
	        		// Managing Stats for closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() + depositAmount);
	        		// Maintaining Transaction History
		            Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), depositAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Deposite"), transactions, transactions.length);
		            System.out.println("Account Balance: "+account.getBalance());
	        	} else if (account.getAccountType().equals("Saving")) {
	        		account.deposit(depositAmount);
	        		// Managing Stats for closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() + depositAmount);
	        		// Maintaining Transaction History
	        		Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), depositAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Deposite"), transactions, transactions.length);
		            System.out.println("Account Balance: "+account.getBalance());
	        	} else if (account.getAccountType().equals("Current")) {
	        		account.deposit(depositAmount);
	        		// Managing Stats for closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() + depositAmount);
	        		// Maintaining Transaction History
		            Bank.transactions = Transaction.addTransaction(new Transaction(getCurrentDateTimeAsLong(), new Date(), depositAmount, account.getAccountType(), accountNumber, account.getHolderName(), "Deposite"), transactions, transactions.length);
		            System.out.println("Account Balance: "+account.getBalance());
	        	} else if (account.getAccountType().equals("Loan")) {
	        		account.deposit(depositAmount);
	        		// Managing Stats for closing Balance
	        		stats.setBankClosingBalance(stats.getBankClosingBalance() + depositAmount);
	        		// Maintaining Transaction History
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
	public void accountLifecycleManagment() {
		int accountLifecycleChoice;
		while (true) {
			System.out.println("***** Account Lifecycle *****");
			System.out.println("1]. Create a Account");
			System.out.println("2]. Show Account Details");
			System.out.println("3]. Close Account");
			System.out.println("4]. Update Account");
			System.out.println("5]. Show Loan Account Details");
			System.out.println("6]. Exit");
			System.out.print("Enter Your Choice: ");
			accountLifecycleChoice = scanner.nextInt();
			
			switch (accountLifecycleChoice) {
				case 1: {
					Account newAccount = this.accountCreation();
					if (newAccount != null) {
						Account[] newAccounts = new Account[Bank.accounts.length + 1];
						System.arraycopy(Bank.accounts, 0, newAccounts, 0, Bank.accounts.length);
						Bank.accounts = newAccounts;
						
						Bank.accounts[Bank.accounts.length - 1] = newAccount;
			            System.out.println("Account added successfully!");
			            
			            // Account Count statistics Managed
			            stats.setNumberOfAccounts(stats.getNumberOfAccounts() + 1);
					} else {
			            System.out.println("Account creation failed.");
			        }
					break;
				}
				case 2: {
					this.showAccountDetails();
					break;
				}
				case 3: {
					System.out.print("Enter Account No: ");
					scanner.nextLine();
					String accountNumber = scanner.nextLine();
					
					Bank.accounts = this.deleteAccount(accountNumber);
					
					// Account Count statistics Managed
		            stats.setNumberOfAccounts(stats.getNumberOfAccounts() - 1);
					break;
				}
				case 4: {
					System.out.print("Enter Account No: ");
					scanner.nextLine();
					String accountNumber = scanner.nextLine();
					
//					while(this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
//				    	System.out.println("This Account Already Taken");
//				    	System.out.print("Enter Account Number: ");
//					    accountNumber = scanner.nextLine();
//				    }
					
					this.updateAccountDetails(accountNumber);
					break;
				}
				case 5: {
					LoanAccount.showLoanAccountDetails(Bank.accounts);
					break;
				}
				case 6: {
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
	
	public Account accountCreation() {
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
		    
//		    System.out.print("Enter Account Number: ");
//		    scanner.nextLine();
//		    accountNumber = scanner.nextLine();
//		    while(this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
//		    	System.out.println("This Account Already Taken");
//		    	System.out.print("Enter Account Number: ");
//			    accountNumber = scanner.nextLine();
//		    }
		    
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
		    
		    // Account Count statistics Managed
            stats.setNumberOfSavingAccounts(stats.getNumberOfSavingAccounts() + 1);
		    
		    return new SavingAccount(AccountFactory.generateAccountNumber("Saving"), holderName, balance, "Saving", new Date());
		} else if (accountType == 2) {
			String accountNumber, holderName;
		    double balance;
		    Date createdOn = new Date();
		    
//		    System.out.print("Enter Account Number: ");
//		    scanner.nextLine();
//		    accountNumber = scanner.nextLine();
//		    
//		    while(this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
//		    	System.out.println("This Account Already Taken");
//		    	System.out.print("Enter Account Number: ");
//			    accountNumber = scanner.nextLine();
//		    }
		    
		    System.out.print("Enter Account Holder Name: ");
		    scanner.nextLine();
		    holderName = scanner.nextLine();
		    
		    System.out.print("Enter Opening Balance: ");
		    balance = scanner.nextDouble();
		    
		    // Account Count statistics Managed
            stats.setNumberOfSalaryAccounts(stats.getNumberOfSalaryAccounts() + 1);
		    
		    return new SalaryAccount(AccountFactory.generateAccountNumber("Salary"), holderName, balance, "Salary", 1, new Date());
		} else if (accountType == 3) {
			String accountNumber, holderName;
		    double balance;
		    Date createdOn = new Date();
		    
//		    System.out.print("Enter Account Number: ");
//		    scanner.nextLine();
//		    accountNumber = scanner.nextLine();
//		    
//		    while(this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
//		    	System.out.println("This Account Already Taken");
//		    	System.out.print("Enter Account Number: ");
//			    accountNumber = scanner.nextLine();
//		    }
		    
		    System.out.print("Enter Account Holder Name: ");
		    holderName = scanner.nextLine();
		    
		    System.out.print("Enter Opening Balance: ");
		    balance = scanner.nextDouble();
		    
		    // Account Count statistics Managed
		    stats.setNumberOfCurrentAccounts(stats.getNumberOfCurrentAccounts() + 1);
		    
			return new CurrentAccount(AccountFactory.generateAccountNumber("Current"), holderName, balance, "Current", createdOn);
		} else if (accountType == 4) {
			String accountNumber, holderName;
		    
//		    System.out.print("Enter Account Number: ");
//		    scanner.nextLine();
//		    accountNumber = scanner.nextLine();
//		    
//		    while(this.isAccountNumberPresent(accountNumber, Bank.accounts)) {
//		    	System.out.println("This Account Already Taken");
//		    	System.out.print("Enter Account Number: ");
//			    accountNumber = scanner.nextLine();
//		    }
		    
		    System.out.print("Enter Account Holder Name: ");
		    scanner.nextLine();
		    holderName = scanner.nextLine();
		    
		    System.out.print("Enter loan Amount: ");
			double loanAmount = scanner.nextDouble();
			
			System.out.print("Enter duration in months: ");
			int noOfEmi = scanner.nextInt();
			
			double monthlyRate = Math.round( ((LoanAccount.getLoanInterestRate() / 100) * loanAmount) * 100.0) / 100.0;
	        double emi = Math.round( (loanAmount / noOfEmi) * 100.0 ) / 100.0;
	        
	        System.out.println("Your Emi for every month is: "+emi);
	        System.out.println("Interest for first month is "+monthlyRate+" for Amount: "+loanAmount);
	        System.out.println("Total of first EMI is: "+(emi+monthlyRate));
	        
	        // Account Count statistics Managed
	        stats.setNumberOfLoanAccounts(stats.getNumberOfLoanAccounts() + 1);
	        
	        System.out.println("loan lelelelelelele => "+loanAmount);
	        
			return new LoanAccount(AccountFactory.generateAccountNumber("Loan"), holderName, 0.0, "Loan", loanAmount, loanAmount, emi, noOfEmi, new Date());
		} else {
			System.out.println("Invalid Choice");
			return null;
		}
	}
	
	public void showAccountDetails() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Format for displaying only the date

	    System.out.printf("%-12s %-20s %-10s %-10s %-20s %-20s%n", "Account No", "Holder Name", "Balance", "Type", "Created On", "Last Transaction");
	    System.out.println("---------------------------------------------------------------------------------------------------------------");

	    for (Account account : Bank.accounts) {
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
	        } else if (account instanceof CurrentAccount) {
	            CurrentAccount current = (CurrentAccount) account;
	            System.out.printf("%-12s %-20s %-10.2f %-10s %-20s %-20s%n", 
	                              current.getAccountNumber(), current.getHolderName(), current.getBalance(), 
	                              current.getAccountType(), dateFormat.format(current.getCreatedOn()), 
	                              dateFormat.format(current.getLastTransactionDate()));
	        } else if (account instanceof LoanAccount) {
	        	LoanAccount loan = (LoanAccount) account;
	            System.out.printf("%-12s %-20s %-10.2f %-10s %-20s %-20s%n", 
	            		loan.getAccountNumber(), loan.getHolderName(), loan.getBalance(), 
	            		loan.getAccountType(), dateFormat.format(loan.getCreatedOn()), 
	                              dateFormat.format(loan.getLastTransactionDate()));
	        }
	    }
	}
	
	public Account[] deleteAccount(String accountNumber) {
		double currentBalanceWithInterest = 0;
		int indexToDelete = -1;
		for (int index = 0; index < Bank.accounts.length; index++) {
            if (Bank.accounts[index] != null && Bank.accounts[index].getAccountNumber().equals(accountNumber)) {
            	currentBalanceWithInterest = Bank.accounts[index].getBalance() + Bank.accounts[index].calculateInterest();
            	indexToDelete = index;
                break;
            }
        }
		if (indexToDelete == -1) {
            System.out.println("Account with number " + accountNumber + " not found.");
            return Bank.accounts; // Return the original array if account is not found
        } else {
        	for (int index = indexToDelete; index < Bank.accounts.length - 1; index++) {
        		Bank.accounts[index] = Bank.accounts[index + 1];
        	}
        }
		Bank.accounts[accounts.length - 1] = null;
		
		System.out.println("Customer Balance with Interest: " + currentBalanceWithInterest);
		System.out.println("Account with number " + accountNumber + " Closed successfully.");
	    return Bank.accounts;
	}
	
	public void updateAccountDetails(String accountNumber) {
		Account accountToEdit = null;
		// Find the account
		for (Account account : Bank.accounts) {
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
	        } else if (accountType.equals("Loan")) {
	        	System.out.println("Comming Soon...");
	        } else {
	        	accountToEdit.setAccountType(accountType);
	        }
        }
        

        System.out.println("\nAccount details updated successfully.");
	}
	// Account Life cycle Management: End
}
