package view;

import java.util.Scanner;

import control.StaffController;

public class MoblimaApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int choice;
		
		System.out.println("________WELCOME TO MOBLIMA_________"
						+ "| 1. Login as Staff                 |"
						+ "| 2. Continue as Guest              |"
						+ "| 0. Exit Program                   |"
						+ "____________________________________");
	
		choice = sc.nextInt();
		
		switch (choice) {
			case 1:
				StaffController staffCon = StaffController.getInstance();
				StaffUI staffUi = StaffUI.getInstance();
				
				System.out.println("Enter username: ");
				String username = sc.nextLine();
				System.out.println("Enter password: ");
				String password = sc.nextLine();
				
				boolean isLoggedIn = staffCon.validateStaffLogin(username, password);
				
				if(isLoggedIn) {
					staffUi.displayMenu();
					break;
				} else {
					System.out.println("The username or password entered is invalid, please try again.");
					break;
				}
				
			case 2:
				// Display Guest Menu
			case 0:
				System.out.print("System terminating..");
				break;
		}
		
		sc.close();
	}

}
