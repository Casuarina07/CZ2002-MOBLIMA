package model;

import java.util.Comparator;

/**
 * Rating entity class
 * @author Casuarina D/O Abdul Karim
 *
 */
public class Rating{
	private String movieTitle;
	private String movieReview;
	private String movieRating;

	/**
	 * Full constructor
	 * @param movieTitle
	 * @param movieRating
	 * @param movieReview
	 */
	public Rating(String movieTitle, String movieRating, String movieReview) {
		super();
		this.movieTitle = movieTitle;
		this.movieReview = movieReview;
		this.movieRating = movieRating;
	}
	
	/**
	 * return movieTitle
	 * @return movieTitle
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * return movieReview
	 * @return movieReview
	 */
	public String getMovieReview() {
		return movieReview;
	}

	/**
	 * return movieRating
	 * @return movieRating
	 */
	public String getMovieRating() {
		return movieRating;
	}

	/**
	 * @param movieRating
	 * @return
	 */
	public double averateRating(double movieRating) {
		return 0d;
	}

	 /**
	 * Comparing the rating values in the array list
	 */
	public static Comparator<Rating> MovieRating = new Comparator<Rating>() {

			/**
			 *Compare rating values and list in ascending order
			 */
			public int compare(Rating r1, Rating r2) {

			  // int rating = r1.getMovieRating();
			   //int rating2 = r2.getMovieRating();
				 int rating = Integer.parseInt(r1.getMovieRating());
				 int rating2 = Integer.parseInt(r2.getMovieRating());

			   /*For ascending order*/
			   return rating2-rating;

		   }};
}
