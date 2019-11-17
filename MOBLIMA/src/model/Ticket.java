package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import control.PriceController;
import control.PriceController.RATES;


/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Ticket {
	private String movieName, movieType, cinemaType, seatID;
	private static double standardPrice = 6, finalPrice, age = 0;
	static boolean isSenior, isChild, isAdult, isStudent;
	private boolean isPublicHoliday, isWeekday;
	private static ArrayList<String> holidayList = new ArrayList<>();
	private MovieListing movie;
	private Cineplex cineplex;
	private Cinema cinema;
	private String showTime;
	private CinemaHall cinemaHall;
	private double price = 0d;
	private int choice;

	public Ticket(String movieName, String movieType, String cinemaType, String seatID, double standardPrice) {
		super();
		this.movieName = movieName;
		this.movieType = movieType;
		this.cinemaType = cinemaType;
		this.seatID = seatID;
		Ticket.standardPrice = standardPrice;
		this.isPublicHoliday = isPublicHoliday();
		this.isWeekday = isWeekday();
	}
	
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

	public String getMovieName() {
		return movieName;
	}

	public MovieListing getMovie() {
		return movie;
	}

	public String getMovieType() {
		return movieType;
	}

	public String getCinemaType() {
		return cinemaType;
	}

	public static double getStandardPrice() {
		return standardPrice;
	}
	
	public static void setStandardPrice(double standardPrice) {
		Ticket.standardPrice = standardPrice;
	}

	public double getFinalPrice() {
		finalPrice = standardPrice;

		// Additional $2 for tickets sold during the public holidays
		if (isPublicHoliday)
			return finalPrice += 2d;

		// Additional $1 for tickets sold during the weekends
		else if (!isWeekday)
			return finalPrice += 1d;

		// Standard price for all other days
		else
			return finalPrice;
	}

	public boolean isWeekday() {
		// Retrieve system's date
		LocalDate date = LocalDate.now();

		// Returns false for weekend, true for weekday
		if (date.getDayOfWeek().getValue() + 1 == Calendar.SATURDAY
				|| date.getDayOfWeek().getValue() + 1 == Calendar.SUNDAY)
			return false;
		else
			return true;
	}
	

	
	/**
	 * @return boolean if it is a public holiday
	 */
	public boolean isPublicHoliday() {
		return isPublicHoliday;
	}
	
	/**
	 * Add public holiday
	 * @param holidayDate
	 */
	public static void addPublicHoliday(String holidayDate) {
		holidayList.add(holidayDate);	
	}
	
	public static boolean isSenior() {
		return isSenior;
	}
	
	public static boolean isChild() {
		return isChild;
	}
	
	public static boolean isAdult() {
		return isAdult;
	}
	
	public static boolean isStudent() {
		return isStudent;
	}
	
	public static double Senior() {
		isChild = false;
		isSenior = true;
		isAdult = false;
		isStudent = false;
		return calculatebySP(RATES.SENIOR.getRate(), age);
	}
	
	public static double Children() {
		isChild = true;
		isSenior = false;
		isAdult = false;
		isStudent = false;
		return calculatebySP(RATES.CHILDREN.getRate(), age);
	}
	
	public static double Adult() {
		isChild = false;
		isSenior = false;
		isAdult = true;
		isStudent = false;
		return calculatebySP(PriceController.RATES.ADULT.getRate(), age);
	}
	
	public static double Student() {
		isChild = false;
		isSenior = false;
		isAdult = false;
		isStudent = true;
		return calculatebySP(RATES.STUDENT.getRate(), age);
	}

	public static void convert(boolean isSenior, boolean isChild, boolean isWeekend) {
		if(isSenior) {
			age = -6;
		} else if(isStudent) {
			age = -2;
		} else if(isChild) {
			age = -4;
		} else {
			age = 0;
		}
	}
	public static double calculatebySP(double rates, double age) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		finalPrice =(standardPrice * rates) + age;
		return Double.valueOf(df2.format(finalPrice));
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
