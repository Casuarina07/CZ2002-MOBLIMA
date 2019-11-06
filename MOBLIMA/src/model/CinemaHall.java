package model;

public class CinemaHall extends Cinema {
	int row, col;
	Integer[][] seats;

	public CinemaHall(String cineplexID, String cineplexName, String cinemaID, String cinemaName, String cinemaLocation,
			int row, int col, Integer[][] seats) {
		super(cineplexID, cineplexName, cinemaID, cinemaName, cinemaLocation);
		this.row = row;
		this.col = col;
		this.seats = seats;
	}

	public void displaySeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats.length; j++) {
				System.out.println(seats[i][j]);
			}
		}
	}

	public boolean isSeatAvailable(int row, int col) {
		if (seats[row][col] == null)
			return true;
		else
			return false;
	}

}
