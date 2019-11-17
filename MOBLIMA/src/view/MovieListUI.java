package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.DatabaseController;
import model.MovieListing;
import model.Rating;

public class MovieListUI {
	private static MovieListUI movieListUI = null;
	private Scanner sc;
	
	private MovieListUI() {
		sc = new Scanner(System.in);
	}

	public static MovieListUI getInstance() {
		if (movieListUI == null) {
			movieListUI = new MovieListUI();
		}
		return movieListUI;
	}
	
	// List all Movie and details
	public void listAllMovie() {
		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = DatabaseController.readMovieListing();

		ArrayList<Rating> ratedMovies = new ArrayList<Rating>();
		ratedMovies = DatabaseController.readReviewCSV();

		System.out.println("--------All Movies-----------");
		for (int i = 0; i < movieListings.size(); i++) {
			System.out.println((i + 1) + ". " + movieListings.get(i).getMovieTitle());
		}

		System.out.println("Choose a movie: ");
		int movieChoice = sc.nextInt();
		if (movieChoice - 1 >= movieListings.size() || movieChoice <= 0) {
			System.out.println("Please select a valid number");
			movieChoice = sc.nextInt();
		}
		System.out.println("You have selected: " + movieListings.get(movieChoice - 1).getMovieTitle());

		System.out.println("1. View movie details");
		System.out.println("2. Book the movie");

		int option;
		option = sc.nextInt();

		switch (option) {
		case 1:
			System.out.println("-----Movie: (" + movieListings.get(movieChoice - 1).getMovieTitle() + ")-----");
			System.out.println("Status: " + movieListings.get(movieChoice - 1).getMovieStatus());
			System.out.println("Director: " + movieListings.get(movieChoice - 1).getMovieDirector());
			System.out.println("Duration: " + movieListings.get(movieChoice - 1).getMovieDuration() + " mins");
			System.out.print("Cast: ");
			ArrayList<String> casts = new ArrayList<String>();
			casts = movieListings.get(movieChoice - 1).getMovieCastList();
			for (String cast : movieListings.get(movieChoice - 1).getMovieCastList()) {
				System.out.print(cast + "; ");
			}
			System.out.println("\nGenre: " + movieListings.get(movieChoice - 1).getMovieGenre());
			System.out.println("Rating: " + movieListings.get(movieChoice - 1).getMovieRating());
			System.out.println("Synopsis: " + movieListings.get(movieChoice - 1).getMovieSynopsis());
			for (int i = 0; i < ratedMovies.size(); i++) {
				if (ratedMovies.get(i).getMovieTitle().equals(movieListings.get(movieChoice - 1).getMovieTitle())) {
					System.out.println("Reviews: " + ratedMovies.get(i).getMovieReview());
					System.out.println("Overall Rating: " + ratedMovies.get(i).getMovieRating() + "/5");
					break;
				}
				if (i == ratedMovies.size() - 1) {
					System.out.println("Review: NA");
					System.out.println("Overall Ratings: NA");
				}
			}
			break;
		case 2:
			System.out.println("\nBooking Ticket for " + movieListings.get(movieChoice-1).getMovieTitle());
			BookingUI bookingUI = BookingUI.getInstance();
			bookingUI.bookMovie(movieListings.get(movieChoice-1));
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
		
	}
}
