package model;

import java.util.ArrayList;

/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cineplex {
	private String cineplexID, cineplexName;
	private static ArrayList<Cineplex> cineplexList = new ArrayList<>();
	private static ArrayList<Cinema> cinemaList = new ArrayList<>();
	
	public Cineplex(String cineplexID, String cineplexName, ArrayList<Cinema> cinemaList) {
		super();
		this.cineplexID = cineplexID;
		this.cineplexName = cineplexName;
	}

	public String getCineplexID() {
		return cineplexID;
	}

	public String getCineplexName() {
		return cineplexName;
	}
	
	public void setCineplexID(String cineplexID) {
		this.cineplexID = cineplexID;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * @return A list of Cineplexes
	 */
	public static ArrayList<Cineplex> getCineplexList(){
		return cineplexList;
	}

	public static ArrayList<Cinema> getCinemaList(){
		return cinemaList;
	}
}
