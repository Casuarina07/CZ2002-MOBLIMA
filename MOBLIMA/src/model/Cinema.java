package model;

public class Cinema extends Cineplex {
	String cinemaID, cinemaName, cinemaLocation;

	public Cinema(String cineplexID, String cineplexName, String cinemaID, String cinemaName, String cinemaLocation) {
		super(cineplexID, cineplexName);
		this.cinemaID = cinemaID;
		this.cinemaName = cinemaName;
		this.cinemaLocation = cinemaLocation;
	}

	public String getCinemaID() {
		return cinemaID;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public String getCinemaLocation() {
		return cinemaLocation;
	}

}
