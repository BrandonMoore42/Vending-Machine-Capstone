//1. Did not add sad path tests

package com.techelevator;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION };
    private int menuCycler;
	private Menu menu;

	

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		
	
	}

	public void run() throws FileNotFoundException {

		
		
menuCycler = 1;
		
		while (true) {
//			String choice = "Purchase";
//			if (menuCycler == 1) 
			if (menuCycler == 1) {
				menuCycler();
			} else if (menuCycler == 0) {
				String choice2 = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
				
				if (choice2.equals(SUB_MENU_OPTION_FEED_MONEY)) {

					
					System.out.println("How much money would you like to deposit? \n");
					
					double userBalance = menu.feedMoney();
					
					System.out.print("Your current balance is $");
					System.out.printf("%.2f", userBalance);
					System.out.print(".\n");
					
				
					menuCycler = 1;
					
					
				} else if (choice2.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
					
					System.out.println("Enter the AlphaNumeric code for item you would like to purchase\n");
					
					String returnContent = menu.purchaseProduct();
					
					System.out.println(returnContent);
					
//					int canYouPurchase = menu.canYouPurchase();
//					
//					if (someVariable == 1) {
//						
//					}
					menuCycler = 0;
					
				}else if (choice2.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
					
					String finishTransactionMessage = menu.changeGivenMessage();
					System.out.println(finishTransactionMessage); //need to round
					
					menuCycler = 1;
					
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
	public void menuCycler() throws FileNotFoundException {
	String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
	

	if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

		System.out.println(menu.displayInventory());
		
		
		
		
	} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
		// do purchase
		String choice2 = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
		
		if (choice2.equals(SUB_MENU_OPTION_FEED_MONEY)) {

			
			System.out.println("How much money would you like to deposit? \n");
			
			double userBalance = menu.feedMoney();
			
			System.out.print("Your current balance is $");
			System.out.printf("%.2f", userBalance);
			System.out.print(".\n");
			
		
			menuCycler = 1;
			
			
		} else if (choice2.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
			
			System.out.println("Enter the AlphaNumeric code for item you would like to purchase\n");
			
			String returnContent = menu.purchaseProduct();
			
			System.out.println(returnContent);
			
//			int canYouPurchase = menu.canYouPurchase();
//			
//			if (someVariable == 1) {
//				
//			}
			menuCycler = 0;
			
		}else if (choice2.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
			
			String finishTransactionMessage = menu.changeGivenMessage();
			System.out.println(finishTransactionMessage); //need to round
			
			menuCycler = 1;
			
		}
	}
	}
}

