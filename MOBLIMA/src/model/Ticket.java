package model;

import java.time.LocalDate;
import java.util.Calendar;

public class Ticket {
	private String movieName, movieType, cinemaType, seatID;
	private double standardPrice, finalPrice;
	private boolean isPublicHoliday, isWeekday;

	public Ticket(String movieName, String movieType, String cinemaType, String seatID, double standardPrice) {
		super();
		this.movieName = movieName;
		this.movieType = movieType;
		this.cinemaType = cinemaType;
		this.seatID = seatID;
		this.standardPrice = standardPrice;
		this.isPublicHoliday = isPublicHoliday();
		this.isWeekday = isWeekday();
	}

	public String getMovieName() {
		return movieName;
	}

	public String getMovieType() {
		return movieType;
	}

	public String getCinemaType() {
		return cinemaType;
	}

	public String getSeatID() {
		return seatID;
	}

	public double getStandardPrice() {
		return standardPrice;
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

	public boolean isPublicHoliday() {
		return isPublicHoliday;
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

}
