package com.unicorn.bank_managment_case_study;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		int masterChoice;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you want to start Bank Application 1/0");
		masterChoice = scanner.nextInt();
		
		if (masterChoice == 1) {
			Bank bank = new Bank("XYZ");
			bank.bankManagmentSystem();
		} else {
			System.out.println("Thank You...");
		}
	}

}
