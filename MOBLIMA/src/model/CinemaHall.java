package model;

import java.util.ArrayList;

public class CinemaHall extends Cinema {
	int row, col;
	int[][] seats;

//	public CinemaHall(String cineplexID, String cineplexName, String cinemaID, String cinemaName, String cinemaLocation,
//			int row, int col) {
//		super(cineplexID, cineplexName, cinemaID, cinemaName, cinemaLocation);
//		this.row = row;
//		this.col = col;
//		this.seats = new int[row][col];
//	}
//	

	public CinemaHall(String cineplexID, String cineplexName, ArrayList<Cinema> cinemaList, String cinemaID,
			String cinemaCode, String cinemaName, ArrayList<MovieListing> movieList, int row, int col, int[][] seats) {
		super(cineplexID, cineplexName, cinemaList, cinemaID, cinemaCode, cinemaName, movieList);
		this.row = row;
		this.col = col;
		this.seats = new int[row][col];
	}



	public void displaySeats() {
		System.out.println("---------------------- SCREEN ----------------------");
		for (int i = 0; i < row; i++) {
			System.out.print((char) (i + 65)+" ");
			for (int j = 0; j < col; j++) {
				if (seats[i][j] == 0) {
					System.out.print("[ ]");
				} else {
					System.out.print("[x]");
				}

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

	public void setSeatBooked(int row, int col) {
		seats[row][col] = 1;
	}

}
