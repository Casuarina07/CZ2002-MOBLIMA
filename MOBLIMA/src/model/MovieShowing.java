package model;
import java.util.ArrayList;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class MovieShowing {
	private String movieName;
	private String cinema;
	private CinemaHall cinemaHall;
	private String cineplex; 
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	private static ArrayList<MovieShowing> showList = new ArrayList<>();
	
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
	
	
	public MovieShowing(String movieName, CinemaHall cinemaHall, String cineplex, ArrayList<ShowTime> showTimes) {
		super();
		this.movieName = movieName;
		this.cinemaHall = cinemaHall;
		this.cineplex = cineplex;
		this.showTimes = new ArrayList<>();
	}
	
	public MovieShowing(String movieName, String cinema, String cineplex, ArrayList<ShowTime> showTimes) {
		super();
		this.movieName = movieName;
		this.cinema = cinema;
		this.cineplex = cineplex;
		this.showTimes = showTimes;
	}

	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	public String getCineplex() {
		return cineplex;
	}
	public void setCineplex(String cineplex) {
		this.cineplex = cineplex;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}
	
	public static ArrayList<MovieShowing> getMovieShows() {
		return showList;
	}
	
	/**
	 * Add a showtime to the list for a movie
	 * @param showTime : shows the movie times
	 */
	public void addShowTime(ShowTime showTime) {
		for(ShowTime st : showTimes) {
			if(st.isDuplicateTime(showTime)) {
				System.out.println("Duplicated Time found!");
				return;
			}
		}
		showTimes.add(showTime);
	}
	
	public void removeShowTime(ShowTime showTime) {
		showTimes.remove(showTime);
		if(showTimes.size() == 0) {

		}
	}
}

