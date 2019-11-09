package view;

import java.util.Scanner;

import control.BookingTicketController;
import model.CinemaHall;
import model.MovieListing;
import model.Seat;
import view.MovieGoerUI.InvalidInputException;

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

	public void displayMenu() {
		int choice;
		
		do {
			System.out.println("_____________WELCOME!!_________________\n"
					+ "| 1. Search Movie                 |\n" 
					+ "| 2. List All Movie               |\n"
					+ "| 3. Book Ticket                  |\n"
					+ "| 4. List Top 5 Movies            |\n"
					+ "| 5. View Booking History         |\n"
					+ "| 0. Exit Program                 |\n"
					+ "____________________________________");
			
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:
				listTop5Movies();
				break;
			case 5:
				viewBookingHistory();
				break;
			case 0:

				break;
			default:
				System.out.println("Invalid choice, please try again");
				break;
			}

		} while (choice > 0);
	}

	// listTop5Movies
	private void listTop5Movies() {
		System.out.println("Top 5 Movies Sold: \n");
		System.out.println("Top 5 Movies Rated: \n");
	}

	private void viewBookingHistory() {
		System.out.println("Booking History \n");
	}

	public class InvalidInputException extends Exception {
		public InvalidInputException() {
			super("Invalid choice");
		}

		public InvalidInputException(String message) {
			super(message);
		}
	}
	
	
	
	
	private void bookTickets() {
		System.out.println("_____________BOOK TICDETS_________________\n");
		int movieChoice = getMovieChoice();
		
		int cinemaChoice = getCinemaChoice();
		System.out.println("Choose show date");
		int dateChoice = sc.nextInt();
		System.out.println("Choose show time");
		int timeChoice = sc.nextInt();
		CinemaHall cinemaHall = new CinemaHall(cineplexID, cineplexName, cinemaID, cinemaName, cinemaLocation, movieChoice,
				showDate, showTime);
		cinemaHall.displaySeats();

		System.out.println("Choose row letter");
		int rowChoice = Character.toUpperCase(sc.next().charAt(0)) - 65;
		System.out.println("Choose column number");
		int colChoice = sc.nextInt();

		Seat seat = cinemaHall.getSeats().get((rowChoice * CinemaHall.ROW) + colChoice);
		seat.occupySeat();

	}

	private int getMovieChoice() {
		// Display all movie listing
		// get movie choice
		int movieId;
		return movieId;
	}

	private int getCinemaChoice() {
		// Display all cinema
		int choice;
		try {
			System.out.println("Choose a cinema.");
			choice = sc.nextInt();
			if (choice < 0) {
				throw new InvalidInputException();
			}
		} catch (InvalidInputException e) {

			System.out.println("Choose a cinema.");
			choice = sc.nextInt();
		}
		return choice;
	}	
	
	private int getShowTimeChoice() {
		int choice;
		try {
			System.out.println("Choose a show time.");
			choice = sc.nextInt();
			if (choice < 0) {
				throw new InvalidInputException();
			}
		} catch (InvalidInputException e) {
			System.out.println("Choo se a show time.");
			choice = sc.nextInt();
		}
		return choice;
	}

}
