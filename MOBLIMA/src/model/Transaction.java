package model;

import java.sql.Date;

public class Transaction {
	private String transactionID, custName, movieTitle, cinemaName, seatID, cineplexName, movieShowtime;
	private double totalAmount;

	public Transaction(String transactionID, String movieTitle, String cineplexName, String cinemaName, String movieShowtime,
		 String seatID, double totalAmount) {
		super();
		this.transactionID = transactionID;
		this.movieTitle = movieTitle;
		this.cineplexName = cineplexName;
		this.cinemaName = cinemaName;
		this.movieShowtime = movieShowtime;
		this.seatID = seatID;
		this.totalAmount = totalAmount;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getCineplexName() {
		return cineplexName;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	
	public String getMovieShowtime() {
		return movieShowtime;
	}

	public String getSeatID() {
		return seatID;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public boolean confirmTransaction() {
		return true;
	}

}
