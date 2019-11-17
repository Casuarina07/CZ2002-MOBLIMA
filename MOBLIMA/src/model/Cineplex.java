package model;

import java.util.ArrayList;

/**
 * This class implements the Cineplex entity which represents a cineplex with
 * attributes cineplexName and cinemaList.
 * 
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cineplex {
	private String cineplexName;
	private static ArrayList<Cineplex> cineplexList = new ArrayList<>();
	private static ArrayList<Cinema> cinemaList = new ArrayList<>();

	/**
	 * Creates a new Cineplex object with the cinplex name and a List of cinemas
	 * owned by the cineplex.
	 * 
	 * @param cineplexName
	 * @param cinemaList
	 */
	public Cineplex(String cineplexName, String[] cinemaList) {
		super();
		this.cineplexName = cineplexName;
	}

	/**
	 * Gets the cineplex's name
	 * 
	 * @return A String representing the cineplex's name
	 */
	public String getCineplexName() {
		return cineplexName;
	}

	/**
	 * Sets the cinplex's name
	 * 
	 * @param cineplexName name of the cineplex
	 */
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * Gets a List of cineplexes
	 * 
	 * @return A list of Cineplexes
	 */
	public static ArrayList<Cineplex> getCineplexList() {
		return cineplexList;
	}

	/**
	 * Gets an ArrayList of cinemas
	 * 
	 * @return An ArrayList of Cinema objects.
	 * @see Cinema
	 */
	public static ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}
}
