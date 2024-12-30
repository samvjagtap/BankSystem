package com.unicorn.bank_managment_case_study;

public class AccountFactory {
	 private static int savingCounter = 1;
	 private static int salaryCounter = 1;
	 private static int currentCounter = 1;
	 private static int loanCounter = 1;
	 
	 public static String generateAccountNumber(String accountType) {
		 String prefix;
	     int counter;

	     switch (accountType) {
	     	case "Saving": {
	     		prefix = "SAV";
	            counter = savingCounter++;
	            break;
	     	}
	        case "Salary": {
	        	prefix = "SAL";
	            counter = salaryCounter++;
	            break;
	        }
	        case "Current": {
	        	prefix = "CUR"; 
	        	counter = currentCounter++;
	        	break;
	        }
	        case "Loan": {
	        	prefix = "LOA";
	            counter = loanCounter++;
	            break;
	        }
	        default:
	        	throw new IllegalArgumentException("Invalid account type: " + accountType);
	     }
	     
	     return prefix + String.format("%03d", counter);
	 }
}
