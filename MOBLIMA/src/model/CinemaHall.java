package model;

public class CinemaHall extends Cinema {
	int row, col;
	int[][] seats;

	public CinemaHall(String cineplexID, String cineplexName, String cinemaID, String cinemaName, String cinemaLocation,
			int row, int col) {
//		super(cineplexID, cineplexName, cinemaID, cinemaName, cinemaLocation);
		this.row = row;
		this.col = col;
		this.seats = new int[row][col];
	}

	public void displaySeats() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(seats[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean isSeatAvailable(int row, int col) {
		if (seats[row][col] == 0)
			return true;
		else
			return false;
	}

}
