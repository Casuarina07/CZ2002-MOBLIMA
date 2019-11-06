package model;

public class Rating {
	private String movieReview;
	private double movieRating;

	public Rating(String movieReview, double movieRating) {
		super();
		this.movieReview = movieReview;
		this.movieRating = movieRating;
	}

	public String getMovieReview() {
		return movieReview;
	}

	public double getMovieRating() {
		return movieRating;
	}

	public double averateRating(double movieRating) {
		return 0d;
	}

}
