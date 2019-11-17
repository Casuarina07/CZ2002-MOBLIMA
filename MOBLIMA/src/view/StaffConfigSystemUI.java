package view;

import java.util.Scanner;

import control.StaffController;
import model.Ticket;


/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-08
 */
public class StaffConfigSystemUI {
	
	private static StaffConfigSystemUI staffCS = null;
	private static Scanner sc; 
	private static StaffController staffCon = StaffController.getInstance();
	
	private StaffConfigSystemUI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffConfigSystemUI getInstance() {
		if(staffCS == null) {
			staffCS = new StaffConfigSystemUI();
		}
		return staffCS;
	}
	
	public void displayMenu() {
		int choice;
		
		do {
			System.out.println("________ CONFIGURE SYSTEM MENU _________\n"
							+ "                                    		\n"
							+ " 1. Manage Ticket Price            		\n"
							+ " 2. Manage Holidays            			\n"
							+ " 0. Back to Staff Menu              		\n"
							+ "_________________________________________\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					manageTicketPrice();
					break;
				case 2:
					manageHolidays();
					break;
				case 0:
					return;
					
			}
		} while (choice >= 0 || choice <= 2);
	}

	/**
	 * manageTicketPrice() : Manages the ticket price by amending only the standard price
	 */
	private static void manageTicketPrice() {
		//display original price chart
		displayTicketPrices();
		
		int choice;
		
		do {
			System.out.println("____________ MANAGE TICKET PRICE __________\n" 
							+ "												\n"
							+ " 1. Edit Standard Ticket Price     	      	\n"
							+ " 0. Back to System Menu        		      	\n"
							+ "____________________________________________	\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					System.out.println("Current standard price: $" + Ticket.getStandardPrice());
					System.out.println("Enter new standard price: ");
					double newSP = sc.nextDouble();
					Ticket.setStandardPrice(newSP);
					System.out.println("\nStandard price updated.");
					//display updated price chart
					displayTicketPrices();
					break;
				case 0:
					return;
			}		
		} while (choice >= 0 || choice <= 2);
	}
	
	private static void manageHolidays() {
		String holidayDate;
		do {
			System.out.println("Enter the holiday (Format: dd-MM-YYYY.");
			holidayDate = sc.nextLine(); 
			if(!holidayDate.isEmpty() && 
				holidayDate.toCharArray().length == 8) {
				String[] dtLine = holidayDate.split("-");
				//add holiday dates!
				Ticket.addPublicHoliday(holidayDate);
				System.out.println("Added date as holiday.");
			} else if(holidayDate.matches("0")) {
				return;				
			} else {
				System.out.println("You have entered wrong input. Please re-enter again!");
				continue;
			}
		} while (!holidayDate.isEmpty());
	}
	
	/**
	 * displayTicketPrices() : Displays ticket price chart
	 */
	private static void displayTicketPrices() {
		System.out.println("____________ TICKET PRICE CHART ___________\n");
		System.out.println("Adult\t\t\t\t" + Ticket.Adult());
		System.out.println("Student\t\t\t\t" + Ticket.Student());
		System.out.println("Senior Citizen\t\t\t" + Ticket.Senior());
		System.out.println("Children\t\t\t" + Ticket.Children());
		System.out.println("\n");
	}
}
