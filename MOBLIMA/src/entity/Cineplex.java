package entity;

public class Cineplex {
	private String cinemaID, cinemaName, cinemaLocation;

	public Cineplex(String cinemaID, String cinemaName, String cinemaLocation) {
		super();
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
