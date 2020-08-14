package com.techelevator;

public class Drink extends ItemInVendingMachine{
	
	public Drink(String name, double price) {
		super(name, price);
	}
	
	public String getMessage() {
		return "Glug Glug, Yum!";
	}
	
	

}
