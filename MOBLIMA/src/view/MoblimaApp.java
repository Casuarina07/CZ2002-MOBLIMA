package view;

import java.io.IOException;
import java.util.Scanner;

import control.StaffController;
import model.Staff;


/**
 * @author Gwyn Bong Xiao Min
 * @author Casuarina D/O Abdul Karim
 * @since 2019-11-01
 */
public class MoblimaApp {

	public static void main(String[] args) throws IOException {

		StaffController staffCon = StaffController.getInstance();
		
		//load in staff info into staff.csv
		boolean loadInStaffData = staffCon.loadStaffList();
		
		Scanner sc = new Scanner(System.in);

		int choice;
		
		do {
			System.out.println("________WELCOME TO MOBLIMA_________\n"
							+ "                                   \n"
							+ " 1. Login as Staff                 \n"
							+ " 2. Continue as Guest              \n"
							+ " 0. Exit Program                   \n"
							+ "____________________________________\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();

			if(choice < 1) {
				System.out.println("Wrong choice. Please re-enter");
				continue;
			} else {
				switch (choice) {
					case 1: // Login as Staff
						System.out.println("Enter username: ");
						String username = sc.next();
						System.out.println("Enter password: ");
						String password = sc.next();
			
						while(loadInStaffData) {
							//verify staff login and get which staff has logged in
							Staff getLoggedInStaff = staffCon.validateStaffLogin(username, password);
			
							if (getLoggedInStaff != null) {
								System.out.println("Hello, " + getLoggedInStaff.getStaffName());
								StaffUI staffUi = StaffUI.getInstance();
								staffUi.displayMenu();
								break;
							} else {
								System.out.println("Invalid login credentials. Please re-enter.");
								continue;
							}
						}
						break;
					
					case 2: // Continue as Guest
						MovieGoerUI movieGoerUI = MovieGoerUI.getInstance();
						movieGoerUI.displayMenu();
						break;
					case 0: // Exit Program
						System.out.print("System terminating..");
						break;
				}
			}
		} while(choice > 2);
	}	
}
