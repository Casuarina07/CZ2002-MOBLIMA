package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class MovieListing {

	public enum MovieStatus {COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING}
	
	public enum MovieGenre {THRILLER, ROMANCE, COMEDY, CARTOON}
	
	public enum MovieRating {G, PG, PG13, NC16, M18, R21} 
	
	private String movieTitle;
	private MovieStatus movieStatus; //refer to enum above
	private String movieDirector;
	private int movieDuration;
	private ArrayList<String> movieCastList; // there may be more than one cast, thus an array is needed
	private MovieGenre movieGenre; //refer to enum above
	private List<Rating> movieReview; // this is a list of all customer reviews for a particular movie
	private double overallRating; // this refers to the overall viewer ratings for example, stars used
	private MovieRating movieRating; //refer to enum above
	private String movieSynopsis;
	
	public MovieListing(String movieTitle, MovieStatus movieStatus, String movieDirector, int movieDuration,
			ArrayList<String> movieCastList, MovieGenre movieGenre, MovieRating movieRating, String movieSynopsis) {
		super();
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.movieDirector = movieDirector;
		this.movieDuration = movieDuration;
		this.movieCastList = movieCastList;
		this.movieGenre = movieGenre;
		this.movieRating = movieRating;
		this.movieSynopsis = movieSynopsis;
	}
	
	

	public MovieListing(String movieTitle, MovieStatus movieStatus, String movieDirector, int movieDuration,
			ArrayList<String> movieCastList, MovieGenre movieGenre, List<Rating> movieReview, double overallRating,
			MovieRating movieRating, String movieSynopsis) {
		super();
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.movieDirector = movieDirector;
		this.movieDuration = movieDuration;
		this.movieCastList = movieCastList;
		this.movieGenre = movieGenre;
		this.movieReview = movieReview;
		this.overallRating = overallRating;
		this.movieRating = movieRating;
		this.movieSynopsis = movieSynopsis;
	}

	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public MovieStatus getMovieStatus() {
		return movieStatus;
	}
	public void setMovieStatus(MovieStatus movieStatus) {
		this.movieStatus = movieStatus;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public int getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}

	public ArrayList<String> getMovieCastList() {
		return movieCastList;
	}

	public void setMovieCastList(ArrayList<String> movieCastList) {
		this.movieCastList = movieCastList;
	}

	public MovieGenre getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(MovieGenre movieGenre) {
		this.movieGenre = movieGenre;
	}

	public List<Rating> getMovieReview() {
		return movieReview;
	}
	public void setMovieReview(List<Rating> movieReview) {
		this.movieReview = movieReview;
	}
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public MovieRating getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}
	public String getMovieSynopsis() {
		return movieSynopsis;
	}
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}	
	
	
	
}
