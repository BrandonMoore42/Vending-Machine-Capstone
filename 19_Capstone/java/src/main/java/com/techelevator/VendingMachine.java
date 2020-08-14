package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VendingMachine {
	
	private Map<String, ItemInVendingMachine> vendingMachineMap = new LinkedHashMap<String, ItemInVendingMachine>();
	//^^^This is the master map.  Don't worry that the Menu has a method creating a new map; it pulls from here.
	
	public VendingMachine(){ 
		File inputFile = new File("VendingMachine.txt");
		try {
			Scanner fileScanner = new Scanner(inputFile);
		 
			while (fileScanner.hasNextLine() ) {
				String line = fileScanner.nextLine();
				String[] itemSplitUp = line.split("\\|");
				String location = itemSplitUp[0];
				String name = itemSplitUp[1];
				double price = Double.parseDouble(itemSplitUp[2]);
				String type = itemSplitUp[3];
				
				ItemInVendingMachine currentItem = null;
				
				if (type.equals("Chip")) {
					currentItem = new Chip(name, price);
				} else if (type.equals("Candy")) {
					currentItem = new Candy(name, price);
				} else if (type.equals("Drink")) {
					currentItem = new Drink(name, price);
				} else if (type.equals("Gum")) {
					currentItem = new Gum(name, price);
				}
				
				vendingMachineMap.put(location, currentItem);
			}
			fileScanner.close();
			
		}catch (FileNotFoundException e) {
				// TODO: handle exception
			}
			
//		} catch (Exception e) {
//			System.out.println("Your file was not found...");
//		}
	}

//	public String displayInventory() {
//		String displayOfItems = "";
//		
//		 Set<String> itemKeySet = vendingMachineMap.keySet();
//		 
//		 for (String key : itemKeySet) {
//			 if (vendingMachineMap.get(key).getNumberOfItems()  == 0) {
//				 displayOfItems += key + " " + 
//						 vendingMachineMap.get(key).getName() + " " + 
//						 vendingMachineMap.get(key).getPrice() + " " + 
//						 "***SOLD OUT***" + "\n";
//			 } else {
//			 displayOfItems += key + " " + 
//					 vendingMachineMap.get(key).getName() + " " + 
//					 vendingMachineMap.get(key).getPrice() + " " + "\n";
//			 }
//			 }
//		
//		return displayOfItems;
//	}


	
	public Map<String, ItemInVendingMachine> getVendingMachineMap() {
		return vendingMachineMap;
	}
	
	public double getItemPrice(String itemLocation) {
		ItemInVendingMachine  item = vendingMachineMap.get(itemLocation);
		double price = item.getPrice();
		return price;
	}
	
	public int getNumberOfItems (String itemLocation) {
		ItemInVendingMachine  item = vendingMachineMap.get(itemLocation);
		int numberOfItems = item.getNumberOfItems();
		return numberOfItems;
	}
	
	public String getMessage(String itemLocation) {
		ItemInVendingMachine  item = vendingMachineMap.get(itemLocation);
		String message = item.getMessage();
		return message;
	}
	
	public void dispenseOneItem(String itemLocation) {
		ItemInVendingMachine  item = vendingMachineMap.get(itemLocation);
		item.dispenseOneItem();
	}
	

}
