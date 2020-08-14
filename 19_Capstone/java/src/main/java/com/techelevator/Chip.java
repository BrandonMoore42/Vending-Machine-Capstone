package com.techelevator;

public class Chip extends ItemInVendingMachine{
	
	public Chip(String name, double price) {
		super(name, price);
	}
	
	public String getMessage() {
		return "Crunch Crunch, Yum!";
	}
	
	

}
