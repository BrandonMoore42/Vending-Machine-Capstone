package com.techelevator;

public abstract class ItemInVendingMachine {
	
	private String name;
	private double price;
	private int numberOfItems;
	
	public ItemInVendingMachine (String strName, double dblPrice) {
		this.name = strName;
		this.price = dblPrice;
		numberOfItems = 5;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getNumberOfItems() {
		return numberOfItems;
	}
	
	public void dispenseOneItem() {
		if (numberOfItems>=1) {//Is is necessary?
		numberOfItems-= 1;
		}
	}
	
	public abstract String getMessage();
	
	
}
