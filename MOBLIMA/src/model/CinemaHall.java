package model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CinemaHall {
	public static int ROW = 10;
	public static int COL = 16;

	private static ArrayList<Seat> seats = new ArrayList<Seat>();
	private static int[][] seatsLayout = new int[ROW][COL];
	private Date showDate, showTime;

	public CinemaHall(Date showDate, Date showTime) {
		super();
		initSeats();
		this.seats = new ArrayList<Seat>();
		this.seatsLayout = new int[ROW][COL];
		this.showDate = showDate;
		this.showTime = showTime;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public static void displaySeats() {
		// Populate the seatsLayout Array with occupied seats.
		int row, col;
		for (int i = 0; i < seats.size(); i++) {
			if (seats.get(i).isOccupied()) {
				col = (seats.get(i).getSeatId()) % ROW;
				row = (seats.get(i).getSeatId()) / ROW;
				seatsLayout[row][col] = 1;
			}
		}

		// Print the seatsLayout
		System.out.println("--------------------- SCREEN ---------------------");
		for (int i = 0; i < ROW; i++) {
			System.out.print((char) (i + 65) + " ");
			for (int j = 0; j < COL; j++) {
				if (seatsLayout[i][j] == 0) {
					System.out.print("[ ]");
				} else {
					System.out.print("[x]");
				}

			}
			System.out.println();
		}
		System.out.println();
	}

	public static void initSeats() {
		seats = new ArrayList<Seat>();
		for (int i = 0; i < (ROW * COL); i++) {
			seats.add(new Seat(i, false));
		}
	}

	public static void readOccupiedSeatsData(int cineplexID, int cinemaID, int movieID, int showTimeID) {
		try {
			FileReader fileReader = new FileReader("./src/storage/CinemaHall.csv");
			CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
			String[] nextRecord;
			// Read from CinemaHall.csv line by line and search for the line with the
			// matching cineplexID, cinemaID, movieID and showTimeID
			while ((nextRecord = csvReader.readNext()) != null) {
				if (Integer.parseInt(nextRecord[1]) == cinemaID && Integer.parseInt(nextRecord[2]) == cinemaID
						&& Integer.parseInt(nextRecord[3]) == movieID
						&& Integer.parseInt(nextRecord[4]) == showTimeID) {

					// List to store seatID that are occupied
					String[] occupiedSeatIds = nextRecord[5].split(",");

					for (String id : occupiedSeatIds) {
						seats.get(Integer.parseInt(id)).setOccupied(true);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
