package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.DatabaseController;
import control.SearchAndListController;
import control.StaffController;
import model.Cinema;
import model.Cineplex;
import model.MovieListing;
import model.MovieShowing;
import model.ShowTime;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class StaffUI {
	
	private static Scanner sc;
	private static StaffUI staffUI = null;
	 
	private static MovieListing movie = new MovieListing();
	static MovieShowing movieShowing = new MovieShowing();
	
	private static ArrayList<MovieListing> movieArrayList = MovieListing.getMovieList();
	private static ArrayList<Cineplex> cineplexArrayList = Cineplex.getCineplexList();
	private static ArrayList<Cinema> cinemaArrayList = Cineplex.getCinemaList();
	private static List<MovieListing> movieList = new ArrayList<MovieListing>();
	
	private static StaffController staffCon = StaffController.getInstance();
	
	private StaffUI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffUI getInstance() {
		if(staffUI == null) {
			staffUI = new StaffUI();
		}
		return staffUI;
	}
	
	/**
	 * Display a menu with options
	 */
	public void displayMenu() {
		int choice;
		
		do {
			System.out.println("____________ STAFF MENU ____________\n"
							+ "                                     \n"
							+ " 1. Add New Movie		            \n"
							+ " 2. Edit Movie Details	            \n"
							+ " 3. Configure Settings	            \n"
							+ " 4. Manage Sales		            	\n"
							+ " 0. Logout                           \n"
							+ "_____________________________________\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
//					addNewMovie();
					break;
				case 2:
//					editAMovie();
					break;
				case 3:
					StaffConfigSystemUI staffCS = StaffConfigSystemUI.getInstance();
					staffCS.displayMenu();
					break;
				case 4:
					StaffManageTop5UI staffMT5 = StaffManageTop5UI.getInstance();
					staffMT5.displayMenu();
					break;
				case 0:
					System.out.println("You have successfully logged out!");
					return;
				default:
					System.out.println("Invalid choice, please try again");
					break;
			}
			
		} while(choice >= 0 || choice <= 8);
	}

}
