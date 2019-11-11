package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class MovieListing {
	private static ArrayList<MovieListing> movieList = new ArrayList<>();

	public enum MovieStatus {COMINGSOON, PREVIEW, NOWSHOWING, ENDSHOWING}
	public enum MovieGenre {THRILLER, ROMANCE, COMEDY, CARTOON, ACTION}
	public enum MovieRating {G, PG, PG13, NC16, M18, R21} 
	
	private int movieID;
	private String movieTitle;
	private MovieStatus movieStatus; //refer to enum above
	private String movieDirector;
	private int movieDuration;
	private ArrayList<String> movieCastList; // there may be more than one cast, thus an array is needed
	private MovieGenre movieGenre; //refer to enum above
	private MovieRating movieRating; //refer to enum above
	private String movieSynopsis;
	
	private List<Rating> movieReview; // this is a list of all customer reviews for a particular movie
	private double overallRating; // this refers to the overall viewer ratings for example, stars used (1 - 5[best])
	
	public MovieListing(int movieID, String movieTitle, MovieStatus movieStatus, String movieDirector, int movieDuration,
			ArrayList<String> movieCastList, MovieGenre movieGenre, MovieRating movieRating, String movieSynopsis) {
		super();
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.movieDirector = movieDirector;
		this.movieDuration = movieDuration;
		this.movieCastList = movieCastList;
		this.movieGenre = movieGenre;
		this.movieRating = movieRating;
		this.movieSynopsis = movieSynopsis;
	}

	public MovieListing(int movieID, String movieTitle, MovieStatus movieStatus, String movieDirector, int movieDuration,
			ArrayList<String> movieCastList, MovieGenre movieGenre, List<Rating> movieReview, double overallRating,
			MovieRating movieRating, String movieSynopsis) {
		super();
		this.movieID = movieID;
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

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
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

	/**
	 * Add cast to Movie Cast List
	 * @param cast : holds a string value
	 */
	public void addMovieCast(String cast) {
		movieCastList.add(cast);
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
	
	/**
	 * 
	 * @return A list of Movies
	 */
	public static ArrayList<MovieListing> getMovieList() {
		return movieList;
	}	
	
	
	
}
