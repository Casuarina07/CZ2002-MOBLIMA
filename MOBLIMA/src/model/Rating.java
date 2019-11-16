package model;

import java.util.Comparator;

public class Rating{
	private String movieTitle;
	private String movieReview;
	private String movieRating;

	public Rating(String movieTitle, String movieRating, String movieReview) {
		super();
		this.movieTitle = movieTitle;
		this.movieReview = movieReview;
		this.movieRating = movieRating;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}

	public String getMovieReview() {
		return movieReview;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public double averateRating(double movieRating) {
		return 0d;
	}

	 public static Comparator<Rating> MovieRating = new Comparator<Rating>() {

			public int compare(Rating r1, Rating r2) {

			  // int rating = r1.getMovieRating();
			   //int rating2 = r2.getMovieRating();
				 int rating = Integer.parseInt(r1.getMovieRating());
				 int rating2 = Integer.parseInt(r2.getMovieRating());

			   /*For ascending order*/
			   return rating2-rating;

		   }};
}
