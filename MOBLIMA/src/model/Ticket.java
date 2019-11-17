package model;

import control.PriceController;

/**
 * This class implements the Ticket entity which a ticket bought by the user
 * with attributes movie, cineplex, cinema, showTime and cinemaHall
 * 
 * @author David Loh Shun Hao
 * @since 2019-10-24
 */
public class Ticket {

	private MovieListing movie;
	private Cineplex cineplex;
	private Cinema cinema;
	private String showTime;
	private CinemaHall cinemaHall;
	private String seatID;
	private double price = 0d;
	private int choice;

	/**
	 * @param movie      Movie selected by the user.
	 * @param cineplex   Cineplex selected by the user.
	 * @param cinema     Cinema sekected by the user.
	 * @param showTime   Show time selected by the user.
	 * @param cinemaHall CinemaHall assigned based ont the show time.
	 * @param seatID
	 * @param choice
	 */
	public Ticket(MovieListing movie, Cineplex cineplex, Cinema cinema, String showTime, CinemaHall cinemaHall,
			String seatID, int choice) {
		super();
		this.movie = movie;
		this.cinema = cinema;
		this.cineplex = cineplex;
		this.showTime = showTime;
		this.cinemaHall = cinemaHall;
		this.seatID = seatID;
		this.choice = choice;
		this.price = PriceController.calculateTicketPrice(10d, choice);
	}

	/**
	 * Gets the movie the user booked.
	 * 
	 * @return A MovieListing object which represents the booked movie.
	 * @see MovieListing
	 */
	public MovieListing getMovie() {
		return movie;
	}

	/**
	 * Gets the price of the ticket.
	 * 
	 * @return The price of the ticket of type double.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the cineplex of the booked movie.
	 * 
	 * @return A Cineplex object chosen by the user.
	 * @see Cineplex
	 */
	public Cineplex getCineplex() {
		return cineplex;
	}

	/**
	 * Gets the cinema of the booked movie.
	 * 
	 * @return A Cinema object choosen by the user.
	 * @see Cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Gets the show time of the booked movie.
	 * 
	 * @return A string representing the show time if the movie.
	 */
	public String getShowTime() {
		return showTime;
	}

	/**
	 * Gets the cinema hall of the booked movie.
	 * 
	 * @return A CinemaHall object of the booked movie.
	 * @see CinemaHall
	 */
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}

	/**
	 * Gets the seat ID of the chosen seat.
	 * 
	 * @return A string representing the seat ID.
	 */
	public String getSeatID() {

		char row = (char) (Integer.parseInt(seatID.substring(0, 1)) + 65);

		return "" + row + "" + seatID.substring(1);
	}

}
