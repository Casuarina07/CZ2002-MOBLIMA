package data;

import java.sql.Date;

public class BookingTicket {
	private String seatsVacancy, movieTitle, cinemaName;
	private Date showTime, showDate;

	public BookingTicket(String seatsVacancy, String movieTitle, String cinemaName, Date showTime, Date showDate) {
		super();
		this.seatsVacancy = seatsVacancy;
		this.movieTitle = movieTitle;
		this.cinemaName = cinemaName;
		this.showTime = showTime;
		this.showDate = showDate;
	}

	public String getSeatsVacancy() {
		return seatsVacancy;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public Date getShowTime() {
		return showTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public boolean updateSeatsVacancy(String seatsVacancy) {
		return true;
	}

}
