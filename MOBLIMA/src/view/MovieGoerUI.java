package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import control.BookingController;
import control.BookingController.SeatOccupiedException;
import control.DatabaseController;
import control.MovieGoerController;
import control.SearchAndListController;
import model.Cinema;
import model.CinemaHall;
import model.Cineplex;
import model.MovieListing;
import model.Ticket;

/**
 * @author Casuarina D/O Abdul Karim
 * @author Loh Hui Qi
 * @author David Loh Shun Hao
 * @since 2019-11-06
 */
public class MovieGoerUI {

	private static MovieGoerUI movieGoerUI = null;
	private Scanner sc;
	private MovieListing movie;

	private MovieGoerUI() {
		sc = new Scanner(System.in);
	}

	public static MovieGoerUI getInstance() {
		if (movieGoerUI == null) {
			movieGoerUI = new MovieGoerUI();
		}
		return movieGoerUI;
	}

	public void displayMenu() throws IOException {
		int choice;

		do {
			System.out.println("_____________WELCOME!!_________________\n" 
					+ "| 1. Search Movie                 |\n"
					+ "| 2. List All Movies              |\n" 
					+ "| 3. List Top Movies              |\n" 
					+ "| 4. View Booking History         |\n" 
					+ "| 5. Review Movies Watched        |\n"
					+ "| 0. Exit Program                 |\n" 
					+ "____________________________________");
			
			choice = sc.nextInt();
			while(choice<0 || choice>5) {
				System.out.println("Invalid choice. Please select again");
				choice = sc.nextInt();
			}
			MovieGoerController moviegoerCon = MovieGoerController.getInstance();
			moviegoerCon.displayUI(choice);

		} while (choice > 0);
	}

	public class InvalidSeatException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidSeatException() {
		}

		@Override
		public String getMessage() {
			return "An invalid seat is entered. Please enter try again!";
		}
	}

}
