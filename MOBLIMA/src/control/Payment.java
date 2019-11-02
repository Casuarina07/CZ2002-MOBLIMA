package control;

import java.sql.Date;

public class Payment {
	private String transactionID, custName, movieTitle, cinemaName, searID;
	private double totalAmount;
	private Date showTime, showDate;

	public Payment(String transactionID, String custName, String movieTitle, String cinemaName, String searID,
			double totalAmount, Date showTime, Date showDate) {
		super();
		this.transactionID = transactionID;
		this.custName = custName;
		this.movieTitle = movieTitle;
		this.cinemaName = cinemaName;
		this.searID = searID;
		this.totalAmount = totalAmount;
		this.showTime = showTime;
		this.showDate = showDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getCustName() {
		return custName;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public String getSearID() {
		return searID;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public Date getShowTime() {
		return showTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public boolean confirmTransaction() {
		return true;
	}

}
