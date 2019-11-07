package view;

import java.util.Scanner;

import control.StaffController;

public class MoblimaApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int choice;
<<<<<<< HEAD
		
=======

>>>>>>> 9dcab4c1716643b6bd2c767342f81bcf50d44d42
		System.out.println("________WELCOME TO MOBLIMA_________\n"
						+ "| 1. Login as Staff                 |\n"
						+ "| 2. Continue as Guest              |\n"
						+ "| 0. Exit Program                   |\n"
<<<<<<< HEAD
						+ "____________________________________\n");
		
=======
						+ "____________________________________");

>>>>>>> 9dcab4c1716643b6bd2c767342f81bcf50d44d42
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

			if (isLoggedIn) {
				staffUi.displayMenu();
				break;
			} else {
				System.out.println("The username or password entered is invalid, please try again.");
				break;
			}

		case 2:
			// Display Guest Menu
			MovieGoerUI movieGoerUI = MovieGoerUI.getInstance();
			movieGoerUI.displayMenu();
		case 0:
			System.out.print("System terminating..");
			break;
		}

		sc.close();
	}

}
