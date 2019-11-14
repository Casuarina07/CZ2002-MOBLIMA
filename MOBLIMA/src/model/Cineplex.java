package model;

import java.util.ArrayList;

/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-10-24
 */
public class Cineplex {
	private String cineplexName;
	private static ArrayList<Cineplex> cineplexList = new ArrayList<>();
	private static ArrayList<Cinema> cinemaList = new ArrayList<>();
	
	public Cineplex(String cineplexName, String[] cinemaList) {
		super();
		this.cineplexName = cineplexName;
	}

	public String getCineplexName() {
		return cineplexName;
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
