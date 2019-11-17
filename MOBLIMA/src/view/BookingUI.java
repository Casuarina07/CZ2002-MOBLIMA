package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.BookingController;
import control.DatabaseController;
import control.BookingController.SeatOccupiedException;
import model.Cinema;
import model.CinemaHall;
import model.Cineplex;
import model.MovieListing;
import model.Ticket;

/**
 * This class implements the BookingUI view which displays the view when users
 * book a movie.
 * 
 * @author David Loh Shun Hao
 * @since 2019-11-06
 */
public class BookingUI {

	private static BookingUI bookingUI = null;
	private Scanner sc;

	private BookingUI() {
		sc = new Scanner(System.in);
	}

	/**
	 * Initialise the BookingUI object.
	 * 
	 * @return The BookingUI object.
	 */
	public static BookingUI getInstance() {
		if (bookingUI == null) {
			bookingUI = new BookingUI();
		}
		return bookingUI;
	}

	/**
	 * Booking movie method to allow user to choose their options 
	 * @param movie
	 */
	public void bookMovie(MovieListing movie) {
		DatabaseController databaseController = DatabaseController.getInstance();
		ArrayList<Cineplex> cineplexs = databaseController.readCineplex();

		// Display all cineplexs
		for (int i = 0; i < cineplexs.size(); i++) {
			System.out.println((i + 1) + ". " + cineplexs.get(i).getCineplexName());
		}

		// Get user's cineplex choice
		System.out.println("Choose a cineplex.");
		int cineplexChoice = sc.nextInt();
		Cineplex cineplex = cineplexs.get(cineplexChoice - 1);

		// Display all cinemas
		System.out.println();
		ArrayList<Cinema> cinemas = databaseController.readCinema(cineplex);
		for (int i = 0; i < cinemas.size(); i++) {
			System.out.println((i + 1) + ". " + cinemas.get(i).getCinemaName());
		}

		// Get user's cinema choice
		System.out.println("Choose a cinema.");
		int cinemaChoice = sc.nextInt();
		Cinema cinema = cinemas.get(cinemaChoice - 1);

		// Display all show time
		System.out.println();
		ArrayList<String>[] list = databaseController.readShowTime(movie.getMovieTitle(), cineplex.getCineplexName(),
				cinema.getCinemaName());
		ArrayList<String> showTimes = list[1];

		// Print the show times
		for (int i = 0; i < showTimes.size(); i++) {
			System.out.println(i + 1 + ". " + getFormattedShowtime(showTimes.get(i)));
		}

		// Get user's show time choice
		System.out.println("Choose a show time.");
		int showTimeChoice = sc.nextInt();
		String showTime = showTimes.get(showTimeChoice - 1);

		// Display seats layout
		System.out.println();
		String[] occupiedSeats = list[2].get(showTimeChoice - 1).split(";");
		ArrayList<String> hallNumbers = list[0];
		CinemaHall hall = new CinemaHall(occupiedSeats, hallNumbers.get(0));
		hall.displaySeats();

		// Get user's seat choice
		BookingController bookingController = BookingController.getInstance();
		String seatID = getSeatChoice();
		while (!bookingController.isValidSeat(seatID)) {
			System.out.println("Seat entered invalid. Please try again.");
			seatID = getSeatChoice();
		}

		int choice = getRateChoice();
		while (choice < 1 || choice > 3) {
			choice = getRateChoice();
		}

		// Split into row and col
		String row = seatID.substring(0, 2);
		String col = seatID.substring(1);
		seatID = "" + (Character.toUpperCase(row.charAt(0)) - 65) + "" + col;

		// Book ticket
		Ticket ticket = null;
		try {
			ticket = bookingController.bookTicket(movie, cineplex, cinema, showTime, hall, seatID, choice);
		} catch (SeatOccupiedException e) {
			System.out.println(e.getMessage());
		}

		// Print ticket details
		if (ticket != null) {
			printTicket(ticket);
		}

	}

	private int getRateChoice() {
		System.out.println("Is your seat choosen for a senior or children?");
		System.out.println("1. Senior");
		System.out.println("2. Children");
		System.out.println("3. None of the above");
		return sc.nextInt();
	}

	private String getSeatChoice() {
		System.out.println("Choose a seat: ");
		return sc.next();
	}

	// Format the showtime to YYYY-mm-dd HH:mm
	private String getFormattedShowtime(String showTime) {
		String year = showTime.substring(0, 4);
		String month = showTime.substring(4, 6);
		String day = showTime.substring(6, 8);
		String hour = showTime.substring(8, 10);
		String min = showTime.substring(10, 12);
		return year + "-" + month + "-" + day + " " + hour + ":" + min;
	}

	// Prints the booked ticket details
	private void printTicket(Ticket ticket) {
		System.out.println("Transaction Successful! Thank you. ");
		System.out.println("_____________Ticket_________________");
		System.out.println("Movie: " + ticket.getMovie().getMovieTitle());
		System.out.println("Price: $" + ticket.getPrice());
		System.out.println("Cineplex: " + ticket.getCineplex().getCineplexName());
		System.out.println("Cinema: " + ticket.getCinema().getCinemaName());
		System.out.println("Show time: " + getFormattedShowtime(ticket.getShowTime()));
		System.out.println("Hall: " + ticket.getCinemaHall().getHallNumber());
		System.out.println("Seat: " + ticket.getSeatID());
		System.out.println("____________________________________");

	}

}
