package view;
import java.util.Scanner;

import control.StaffController;
import model.MovieListing;

public class StaffUI {

	private static StaffUI staffUI = null;
	private Scanner sc; 
	private MovieListing movie;
	
	private StaffUI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffUI getInstance() {
		if(staffUI == null) {
			staffUI = new StaffUI();
		}
		return staffUI;
	}
	
	public void displayMenu() {
		int choice;
		StaffController staffCon = StaffController.getInstance();
		
		do {
			System.out.println("_____________ STAFF MENU ______________\n"
								+ "|                                    |\n"
								+ "| 1. Create Movie Listing            |\n"
								+ "| 2. Update Movie Listing            |\n"
								+ "| 3. Remove Movie Listing            |\n"
								+ "| 4. Create Movie Showing            |\n"
								+ "| 5. Update Movie Showing            |\n"
								+ "| 6. Remove Movie Showing            |\n"
								+ "| 7. Configure Settings	            |\n"
								+ "| 8. Generate Sales Report           |\n"
								+ "| 0. Logout                          |\n");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:

					staffCon.createMovieListing(movie);
					break;
				case 2:
					staffCon.updateMovieListing();
					break;
				case 3:
					staffCon.removeMovieListing();
					break;
				case 4:
					staffCon.createMovieShowing();
					break;
				case 5:
					staffCon.updateMovieShowing();
					break;
				case 6:
					staffCon.removeMovieShowing();
					break;
				case 7:
					staffCon.configureSettings();
					break;
				case 8:
					staffCon.generateSalesReport();
					break;
				case 0:
					staffCon.staffLogout();
					break;
				default:
					System.out.println("Invalid choice, please try again");
					break;
			}
			
		} while(choice > 0);
	}
}
