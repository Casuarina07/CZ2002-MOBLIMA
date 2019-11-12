package model;

import java.util.ArrayList;

/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cinema extends Cineplex {
	String cinemaID, cinemaCode, cinemaName;
	private ArrayList<MovieListing> movieList;


	public Cinema(String cineplexID, String cineplexName, ArrayList<Cinema> cinemaList, String cinemaID,
			String cinemaCode, String cinemaName, ArrayList<MovieListing> movieList) {
		super(cineplexID, cineplexName, cinemaList);
		this.cinemaID = cinemaID;
		this.cinemaCode = cinemaCode;
		this.cinemaName = cinemaName;
		this.movieList = movieList;
	}

	public String getCinemaID() {
		return cinemaID;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaID(String cinemaID) {
		this.cinemaID = cinemaID;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
}
