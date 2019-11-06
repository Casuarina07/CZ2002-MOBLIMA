package model;
import java.text.SimpleDateFormat;

/*
 * This class represents a movie show times at specific cinema hall
 *  
 * */
public class MovieShowing {
	private MovieListing movieName;
	private String cinemaHall;
	private Cineplex cineplex; 
	private SimpleDateFormat showTime;
		
	public MovieShowing(MovieListing movieName, String cinemaHall, Cineplex cineplex, SimpleDateFormat showTime) {
		super();
		this.movieName = movieName;
		this.cinemaHall = cinemaHall;
		this.cineplex = cineplex;
		this.showTime = showTime;
	}
	
	/**
	 * string of information about a movie showing in a cinema
	 * 
	 * */
	public String toString() {
		return null;

	}
	
	public MovieListing getMovieName() {
		return movieName;
	}
	public void setMovieName(MovieListing movieName) {
		this.movieName = movieName;
	}
	public String getCinemaHall() {
		return cinemaHall;
	}
	public void setCinemaHall(String cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	public Cineplex getCineplex() {
		return cineplex;
	}
	public void setCineplex(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	public SimpleDateFormat getShowTime() {
		return showTime;
	}
	public void setShowTime(SimpleDateFormat showTime) {
		this.showTime = showTime;
	}
}

