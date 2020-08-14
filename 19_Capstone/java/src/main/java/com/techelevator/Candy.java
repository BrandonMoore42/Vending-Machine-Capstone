package com.techelevator;

public class Candy extends ItemInVendingMachine{

	public Candy(String name, double price) {
		super(name, price);
	}
	
	public String getMessage() {
		return "Munch Munch, Yum!";
	}
	
	

}
