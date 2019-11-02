package control;

public class Sales {

	private String movieTitle;
	private double totalAmount;

	public Sales(String movieTitle, double totalAmount) {
		super();
		this.movieTitle = movieTitle;
		this.totalAmount = totalAmount;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double calculateTotalSales() {
		return 0d;
	}

	public void top5Movies() {

	}

}
