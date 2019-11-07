package model;

public class Cineplex {
	private String cineplexID, cineplexName;

	public Cineplex(String cineplexID, String cineplexName) {
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

}
