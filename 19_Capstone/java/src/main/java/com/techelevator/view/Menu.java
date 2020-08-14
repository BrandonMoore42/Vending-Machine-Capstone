package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.time.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.techelevator.Bank;
import com.techelevator.ItemInVendingMachine;
import com.techelevator.VendingMachine;



public class Menu {

	private static DecimalFormat df = new DecimalFormat("0.00");
	private PrintWriter out;
	private Scanner in;
	private Bank bank = new Bank(); //This is the master bank; don't make any other banks in other classes.
	private VendingMachine vendingMachine = new VendingMachine(); //^^^Same goes for this vendingMachine
	Map<String, ItemInVendingMachine> vendingMachineMap = vendingMachine.getVendingMachineMap();
	
	LocalDateTime myDateObj = LocalDateTime.now();
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
	String formattedDate = myDateObj.format(myFormatObj);  //THIS IS THE TIME STRING(Sounds like a Dr.Who Episode)
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	public double feedMoney() {
		
String userInput = null;
while (userInput == null) {
				try {
		userInput = in.nextLine();
		
		double moneyFed = Double.parseDouble(userInput);
			
		
		bank.feedMoney(moneyFed);
		
		writeToAudit(formattedDate+" FEED MONEY: $"+ df.format(moneyFed)+" $"+ df.format(bank.getUserBalance()) +"\n");
				} catch (Exception e) {
					out.println("not a valid entry, please enter a number");
					userInput = null;
					out.flush();
				}
}
		
		
		return bank.getUserBalance();
	}
	
	public String displayInventory() {
		
		String displayOfItems = "";
		
		 Set<String> itemKeySet = vendingMachineMap.keySet();
		 
		 for (String key : itemKeySet) {
			 if (vendingMachineMap.get(key).getNumberOfItems()  == 0) {
				 displayOfItems += key + " " + 
						 vendingMachineMap.get(key).getName() + " $" + 
						 df.format(vendingMachineMap.get(key).getPrice()) + " " + 
						 "***SOLD OUT***" + "\n";
			 } else {
			 displayOfItems += key + " " + 
					 vendingMachineMap.get(key).getName() + " $" + 
					 df.format(vendingMachineMap.get(key).getPrice()) + " " + "\n";
			 }
			 }
		
		return displayOfItems;
	}

	
	public String purchaseProduct() {
		String productToPurchaseLocation = in.nextLine(); //needs sad path check
		
		if (bank.getUserBalance() < vendingMachine.getItemPrice(productToPurchaseLocation) ) {
			return "Insufficient funds; please choose to feed money first\n";
		} else if (bank.getUserBalance() >= vendingMachine.getItemPrice(productToPurchaseLocation)) {
				if (vendingMachine.getNumberOfItems(productToPurchaseLocation) > 0) {
					double priceOfItemToPurchase = vendingMachineMap.get(productToPurchaseLocation).getPrice();
					bank.spendMoney(priceOfItemToPurchase);
					vendingMachine.dispenseOneItem(productToPurchaseLocation);
					
					writeToAudit(formattedDate+ " " +vendingMachineMap.get(productToPurchaseLocation).getName() +
					" "+productToPurchaseLocation+		
					" $"+df.format(vendingMachine.getItemPrice(productToPurchaseLocation))+" $"+ 
					df.format(bank.getUserBalance()) +"\n");
					
					return vendingMachine.getMessage(productToPurchaseLocation);
					} else {
					return "Sorry, that item is sold out.\n";
					
				}

			
		}
		
		
		return "Fix this part";
	}
	
	public String changeGivenMessage() {
		double changeInitial = bank.getChange();
		int change = (int) (Math.ceil(changeInitial*100));
		int quarters =(change/25);
		int dimes = ((change - quarters*25)/10);
		int nickels = ((change - quarters*25 - dimes*10)/5);
	
		String givenMessage = ("Your change is " + quarters + " quarters, " +
								dimes + " dimes, and " + nickels + " nickels.");
		
		writeToAudit(formattedDate+" GIVE CHANGE: $"+df.format(changeInitial)+" $"+ df.format(bank.getUserBalance()) +"\n");
		
				return givenMessage;
		
	}
	
	public void writeToAudit(String logInput) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("log.txt",true);
		
			fos.write(logInput.getBytes());
		
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
