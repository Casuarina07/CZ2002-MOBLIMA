package model;
import java.util.ArrayList;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class MovieShowing {
	private MovieListing movieName;
	private CinemaHall cinemaHall;
	private Cineplex cineplex; 
	private ArrayList<ShowTime> showTimes;
	
	/**
	 * Empty Constructor
	 */
	public MovieShowing() {
		super();
	}
		
	/**
	 * Constructor
	 * @param movieName
	 * @param cinemaHall
	 * @param cineplex
	 * @param showTimes
	 */
	public MovieShowing(MovieListing movieName, CinemaHall cinemaHall, Cineplex cineplex, ArrayList<ShowTime> showTimes) {
		super();
		this.movieName = movieName;
		this.cinemaHall = cinemaHall;
		this.cineplex = cineplex;
		this.showTimes = new ArrayList<>();
	}
	
	public MovieListing getMovieName() {
		return movieName;
	}
	public void setMovieName(MovieListing movieName) {
		this.movieName = movieName;
	}
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	public Cineplex getCineplex() {
		return cineplex;
	}
	public void setCineplex(Cineplex cineplex) {
		this.cineplex = cineplex;
	}

	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}
	
	
	/**
	 * Add a list of show time for a movie
	 * @param showTime : shows the movie times
	 */
	public void addShowTime(ShowTime showTime) {
		for(ShowTime st : showTimes) {

		}
	}
}

