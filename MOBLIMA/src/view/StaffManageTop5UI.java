package view;

import java.util.Scanner;

import control.StaffController;

/**
 * @author Gwyn Bong Xiao Min
 * @author Loh Hui Qi
 * @author Casuarina D/O Abdul Karim
 * @since 2019-11-08
 */
public class StaffManageTop5UI {
	private static StaffManageTop5UI staffMT5 = null;
	private static Scanner sc; 
	private static StaffController staffCon = StaffController.getInstance();
	
	private StaffManageTop5UI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffManageTop5UI getInstance() {
		if(staffMT5 == null) {
			staffMT5 = new StaffManageTop5UI();
		}
		return staffMT5;
	}

	public void displayMenu() {
		int choice;
		
		do {
			System.out.println("_____ MANAGE TOP 5 MOVIES MENU ________\n"
							+ "|                                       |\n"
							+ "| 1. List Top 5 Movies by Sales     	   |\n"
							+ "| 2. List Top 5 Movies by Rating    	   |\n"
							+ "| 0. Back to Staff Menu                 |\n"
							+ "|                                       |\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					listTop5bySales();
					break;
				case 2:
					listTop5byRating();
					break;
				case 0:
					return;
					
			}
		} while (choice >= 0 || choice <= 2);
	}

	private void listTop5byRating() {
		
	}

	private void listTop5bySales() {
		
	}

}
