package model;

import java.util.ArrayList;

/**
 * This class implements the Cinema entity which represents a cinema with
 * attributes cinemaCode, cinemaName, movieList.
 * 
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cinema {
	String cinemaCode, cinemaName;
	private ArrayList<MovieListing> movieList;

	/**
	 * Creates a cinema with the cinema code, cinema name and an ArrayList of
	 * MovieListing objects
	 * 
	 * @param cinemaCode
	 * @param cinemaName
	 * @param movieList
	 */
	public Cinema(String cinemaCode, String cinemaName, ArrayList<MovieListing> movieList) {
		super();
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
		this.movieList = movieList;
	}

	/**
	 * Creates a Cinema with the cinema code and cinema name.
	 * 
	 * @param cinemaCode cinema code in letters.
	 * @param cinemaName name of cinema.
	 */
	public Cinema(String cinemaCode, String cinemaName) {
		super();
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
	}

	/**
	 * Creates a Cinema the movie showing.
	 * 
	 * @param movieList An ArrayList of MovieListing object.
	 * @see MovieListing
	 */
	public Cinema(ArrayList<MovieListing> movieList) {
		super();
		this.movieList = movieList;
	}

	/**
	 * Gets the cinema code.
	 * 
	 * @return A string representing the cinema code.
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}

	/**
	 * gets the cinema's name
	 * 
	 * @return String of cinema name
	 */
	public String getCinemaName() {
		return cinemaName;
	}

	/**
	 * Retrieve all the movie that are showing in the cinema.
	 * 
	 * @return An ArrayList of MovieListing object.
	 */
	public ArrayList<MovieListing> getMovieList() {
		return movieList;
	}

}
