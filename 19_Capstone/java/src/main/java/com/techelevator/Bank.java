package com.techelevator;

public class Bank {

	private double userBalance;
	private double machineBalance;
	
	
	public Bank() {
	
	}
	
	public double getUserBalance() {
		return userBalance;
	}
	
	public double getMachineBalance() {
		return machineBalance;
	}
	
	public void feedMoney(double moneyFed) {
		userBalance += moneyFed;
	}
	
	public void spendMoney(double moneySpent) {
		userBalance -= moneySpent;
		machineBalance += moneySpent;
	}
	
	public double getChange() {
		double change = userBalance;
		userBalance -= userBalance;
		return change;
	}

}
