package control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Cinema;
import model.CinemaHall;
import model.Cineplex;
import model.MovieListing;
import model.Seat;
import model.Ticket;
import model.Transaction;

/**
 * This class implements the BookingController controller that controls the
 * booking of a movie.
 * 
 * 
 * @author David Loh Shun Hao
 * 
 * @since 2019-11-13
 */
public class BookingController {

	private static BookingController bookingController;

	public static double standardPrice = 10d;

	/**
	 * Initialise a new BookingController object.
	 * 
	 * @return BookingController object.
	 */
	public static BookingController getInstance() {
		if (bookingController == null) {
			bookingController = new BookingController();
		}
		return bookingController;
	}

	/**
	 * Books a movie.
	 * 
	 * @param movie    Movie chosen by the user.
	 * @param cineplex Cineplex chosen by the user.
	 * @param cinema   Cinema chosen by the user.
	 * @param showTime Show time chosen by the user.
	 * @param hall     Cinema Hall in which the movie will be showing at.
	 * @param seatID   Seat selected by the user.
	 * @param choice   Integer number indicating if the user is booking for a
	 *                 children, senior or none of them.
	 * 
	 * @return A Ticket object representing the booked ticket.
	 * @see MovieListing
	 * @see Cineplex
	 * @see Cinema
	 * @see CinemaHall
	 * @see Ticket
	 * 
	 * @throws SeatOccupiedException
	 */
	public Ticket bookTicket(MovieListing movie, Cineplex cineplex, Cinema cinema, String showTime, CinemaHall hall,
			String seatID, int choice) throws SeatOccupiedException {
		DatabaseController controller = DatabaseController.getInstance();
		Ticket ticket = null;

		// Loop through all the seats in the hall
		for (Seat seat : hall.getSeats()) {
			// Compare seatID to find the seat user selected
			if (seat.getSeatID().equalsIgnoreCase(seatID)) {

				occupySeat(seat);
				String movieTitle = movie.getMovieTitle();
				String cineplexName = cineplex.getCineplexName();
				String cinemaCode = cinema.getCinemaCode();
				String cinemaName = cinema.getCinemaName();
				controller.writeOccupiedSeat(movieTitle, cineplexName, cinemaName, showTime, seatID);
				ticket = new Ticket(movie, cineplex, cinema, showTime, hall, seatID, choice);

				String transactionID = generateTransactionID(cinemaCode);
				Transaction transaction = new Transaction(transactionID, movieTitle, cineplexName, cinemaName, showTime,
						seatID, ticket.getPrice());

				controller.writeTransaction(transaction);
			}
		}
		return ticket;
	}

	/**
	 * Validates if the seat entered by the user is a valid input.
	 * 
	 * @param seatID Seat chosen by the user.
	 * @return If the Seat ID entered is valid.
	 */
	public boolean isValidSeat(String seatID) {
		if (seatID.length() == 2 || seatID.length() == 3) {

			String row = seatID.substring(0, 1);
			String col = seatID.substring(1);

			// If row entered is between A - Z
			if (row.matches("[a-jA-J]"))
				if (Integer.parseInt(col) >= 0 && Integer.parseInt(col) <= CinemaHall.COL)
					return true;
				else
					return false;
			else
				return false;
		} else {
			return false;
		}
	}

	private String generateTransactionID(String cinemaCode) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		LocalDateTime now = LocalDateTime.now();
		return cinemaCode + dtf.format(now);
	}

	private void occupySeat(Seat seat) throws SeatOccupiedException {
		if (seat.isOccupied())
			throw new SeatOccupiedException();
		else
			seat.setOccupied(true);
	}

	/**
	 * This class implements the SeatOccupiedExeception exception that handles the
	 * exception when a selected seat is occupied.
	 * 
	 * 
	 * @author David Loh Shun Hao
	 * 
	 * @since 2019-11-13
	 */
	public class SeatOccupiedException extends Exception {
		private static final long serialVersionUID = 1L;

		public SeatOccupiedException() {
		}

		@Override
		public String getMessage() {
			return "The seat is occupied! Please choose another seat!";
		}
	}

	/**
	 * This class implements the SeatOccupiedExeception exception that handles the
	 * exception when all the seats are occupied.
	 * 
	 * 
	 * @author David Loh Shun Hao
	 * 
	 * @since 2019-11-13
	 */
	public class SeatFullException extends Exception {
		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage() {
			return "The seats are full!";
		}
	}

}