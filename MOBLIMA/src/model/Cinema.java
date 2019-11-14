package model;

import java.util.ArrayList;

/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cinema {
	String cinemaCode, cinemaName;
	private ArrayList<MovieListing> movieList;

	public Cinema(String cinemaCode, String cinemaName, ArrayList<MovieListing> movieList) {
		super();
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
		this.movieList = movieList;
	}

	public Cinema(String cinemaCode, String cinemaName) {
		super();
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public ArrayList<MovieListing> getMovieList() {
		return movieList;
	}

	public Cinema(ArrayList<MovieListing> movieList) {
		super();
		this.movieList = movieList;
	}

}
