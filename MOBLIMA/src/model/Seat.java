package model;

/**
 * This class implements the Ticket entity which a ticket bought by the user
 * with attributes movie, cineplex, cinema, showTime and cinemaHall
 * 
 * @author David Loh Shun Hao
 * @since 2019-10-24
 */
public class Seat {
	private String seatID;
	private boolean isOccupied;

	/**
	 * Creates a new Seat with its seat ID.
	 * 
	 * @param seatID A string representing the row and column of the seat.
	 */
	public Seat(String seatID) {
		super();
		this.seatID = seatID;
		this.isOccupied = false;
	}

	/**
	 * Gets the string representing the row and column of the seat.
	 * 
	 * @return A string representing the row and column of the seat.
	 */
	public String getSeatID() {
		return seatID;
	}

	/**
	 * Gets the vacancy status of the seat.
	 * 
	 * @return If the seat is occupied.
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Set the vacancy status of the seat.
	 * 
	 * @param isOccupied If the seat is occupied.
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}