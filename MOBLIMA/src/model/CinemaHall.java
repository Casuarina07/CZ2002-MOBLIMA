package model;

import java.util.ArrayList;

/**
 * This class implements the CinemaHall entity which represents the lyaout of
 * the show time selected a with attributes cinemaCode, cinemaName, movieList.
 * 
 * @author David Loh Shun Hao
 * @since 2019-10-24
 */
public class CinemaHall {
	public static int ROW = 10, COL = 16;
	private String[] occupiedSeats;
	private int row, col;
	private Seat[][] seatLayout;
	private ArrayList<Seat> seats;
	private String hallNumber;

	/**
	 * Creates a cinema hall with seat ID of occupied seats and hall number.
	 * 
	 * @param occupiedSeats List of all seat ID that are occupied.
	 * @param hallNumber    String representing the hall number
	 */
	public CinemaHall(String[] occupiedSeats, String hallNumber) {
		super();
		this.occupiedSeats = occupiedSeats;
		this.hallNumber = hallNumber;
		initSeats();
	}

	/**
	 * Gets an ArrayList of Seat objects.
	 * 
	 * @return An ArrayList of Seat objects.
	 * @see Seat
	 */
	public ArrayList<Seat> getSeats() {
		return seats;
	}

	/**
	 * Gets the hall number.
	 * 
	 * @return String representing the hall number.
	 */
	public String getHallNumber() {
		return hallNumber;
	}

	/**
	 * Displays the seat layout for use to select a seat.
	 * 
	 */
	public void displaySeats() {
		System.out.println("--------------------- SCREEN ---------------------");
		for (int i = 0; i < ROW; i++) {
			System.out.print((char) (i + 65) + " ");
			for (int j = 0; j < COL; j++) {
				if (seatLayout[i][j].isOccupied()) {
					System.out.print("[X]");
				} else {
					System.out.print("[ ]");
				}

			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Initialise an ArrayList of Seat objects and a 2D List of Seat type.
	 */
	public void initSeats() {
		String seatID;

		// Initialise an ArrayList to store the seats
		seats = new ArrayList<Seat>();
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				seatID = "" + i + "" + j;
				seats.add(new Seat(seatID));
			}
		}

		// Loops through the occupiedSeats List and set Seat that are occupied.
		for (Seat seat : seats) {
			for (int i = 0; i < occupiedSeats.length; i++) {
				if (seat.getSeatID().equals(occupiedSeats[i])) {
					seat.setOccupied(true);
				}
			}
		}

		// Initialise the 2D List with all the seat in the List.
		seatLayout = new Seat[ROW][COL];
		for (int i = 0; i < seats.size(); i++) {
			col = i % COL;
			row = i / COL;
			seatLayout[row][col] = seats.get(i);
		}
	}

}
