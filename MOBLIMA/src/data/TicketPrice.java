package data;

public class TicketPrice {
	private String ticketPrice, cinemaType, movieType;
	private boolean isPublicHoloday, isWeekday;
	private double standardPrice;

	public TicketPrice(String ticketPrice, String cinemaType, String movieType, boolean isPublicHoloday,
			boolean isWeekday, double standardPrice) {
		super();
		this.ticketPrice = ticketPrice;
		this.cinemaType = cinemaType;
		this.movieType = movieType;
		this.isPublicHoloday = isPublicHoloday;
		this.isWeekday = isWeekday;
		this.standardPrice = standardPrice;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(String cinemaType) {
		this.cinemaType = cinemaType;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public boolean isPublicHoloday() {
		return isPublicHoloday;
	}

	public void setPublicHoloday(boolean isPublicHoloday) {
		this.isPublicHoloday = isPublicHoloday;
	}

	public boolean isWeekday() {
		return isWeekday;
	}

	public void setWeekday(boolean isWeekday) {
		this.isWeekday = isWeekday;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

}
