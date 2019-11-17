package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import control.DatabaseController;
import model.Transaction;

/**
 * To display UI for booking history
 * @author Casuarina D/O Abdul Karim
 * 
 */
public class BookingHistoryUI {
	private static BookingHistoryUI bookingHistoryUI = null;
	private Scanner sc;
	
	private BookingHistoryUI() {
		sc = new Scanner(System.in);
	}

	public static BookingHistoryUI getInstance() {
		if (bookingHistoryUI == null) {
			bookingHistoryUI = new BookingHistoryUI();
		}
		return bookingHistoryUI;
	}

	
	/**
	 * To loop through the transaction array list for booking history
	 * @throws IOException
	 */
	public static void viewBookingHistory() throws IOException {
		System.out.println("------Booking History-----"); 
		ArrayList<Transaction> bookingHistory = new ArrayList<Transaction>();
		bookingHistory = DatabaseController.readTransactionCSV();
		for (int i = 0; i < bookingHistory.size(); i++) {
			System.out.println( "\nTransaction ID: " + bookingHistory.get(i).getTransactionID());
			System.out.println( "Movie Title: " + bookingHistory.get(i).getMovieTitle());
			System.out.println( "Cineplex Name: " + bookingHistory.get(i).getCineplexName());
			System.out.println( "Cinema Name: " + bookingHistory.get(i).getCinemaName());
			System.out.println( "Movie Showtime: " + bookingHistory.get(i).getMovieShowtime());
			System.out.println( "Seat ID: " + bookingHistory.get(i).getSeatID());
		}
		
	}
}
