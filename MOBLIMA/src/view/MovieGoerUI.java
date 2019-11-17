package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.BookingController;
import control.BookingController.SeatOccupiedException;
import control.DatabaseController;
import control.SearchAndListController;
import model.Cinema;
import model.CinemaHall;
import model.Cineplex;
import model.MovieListing;
import model.Ticket;

/**
 * @author Casuarina D/O Abdul Karim
 * @author Loh Hui Qi
 * @author David Loh Shun Hao
 * @since 2019-11-06
 */
public class MovieGoerUI {

	private static MovieGoerUI movieGoerUI = null;
	private Scanner sc;
	private MovieListing movie;

	private MovieGoerUI() {
		sc = new Scanner(System.in);
	}

	public static MovieGoerUI getInstance() {
		if (movieGoerUI == null) {
			movieGoerUI = new MovieGoerUI();
		}
		return movieGoerUI;
	}

	public void displayMenu() {
		int choice;

		do {
			System.out.println("_____________WELCOME!!_________________\n");
			System.out.println("| 1. Search Movie                 |");
			System.out.println("| 2. List All Movie               |");
			System.out.println("| 3. List Top 5 Movies            |");
			System.out.println("| 4. View Booking History         |");
			System.out.println("| 0. Exit Program                 |");
			System.out.println("____________________________________");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				searchMovie();
				break;
			case 2:
				listAllMovie();
				break;
			case 3:
				listTop5Movies();
				break;
			case 4:
				viewBookingHistory();
				break;

			case 0:

				break;
			default:
				System.out.println("Invalid choice, please try again");
				break;

			}

		} while (choice > 0);
	}

	// Search Movie
	private void searchMovie() {
		System.out.println("Enter movie title: ");
		String titleInput = sc.nextLine();

		ArrayList<MovieListing> movieSearching = new ArrayList<MovieListing>();
		movieSearching = SearchAndListController.searchMovieCSV(titleInput);

		for (int i = 0; i < movieSearching.size(); i++) {
			System.out.println((i + 1) + ". " + movieSearching.get(i).getMovieTitle());
		}
		if (movieSearching.size() == 0) {
			return;
		}

		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = SearchAndListController.readMovieCSV();

		System.out.println("Choose a movie: ");
		int movieChoice = sc.nextInt();
		System.out.println("You have selected: " + movieSearching.get(movieChoice - 1).getMovieTitle());

		System.out.println("1. View movie details");
		System.out.println("2. Book the movie");

		int option;
		option = sc.nextInt();

		switch (option) {
		case 1:
			printMovieDetails(movieSearching.get(movieChoice - 1));
			break;
		case 2:
			// Book movie from bookingUI
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}

	// List all Movie and details
	private void listAllMovie() {
		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = SearchAndListController.readMovieCSV();
		System.out.println("--------All Movies-----------");
		for (int i = 0; i < movieListings.size(); i++) {
			System.out.println((i + 1) + ". " + movieListings.get(i).getMovieTitle());
		}

		System.out.println("Choose a movie: ");
		int movieChoice = sc.nextInt();
		if (movieChoice - 1 >= movieListings.size() || movieChoice <= 0) {
			System.out.println("Please select a valid number");
			return;
		}
		System.out.println("You have selected: " + movieListings.get(movieChoice - 1).getMovieTitle());
		System.out.println("1. View movie details");
		System.out.println("2. Book the movie");

		int option;
		option = sc.nextInt();

		switch (option) {
		case 1:
			printMovieDetails(movieListings.get(movieChoice - 1));
			break;
		case 2:
			// book movie from bookingUI
			break;
		default:
			System.out.println("Invalid input");
			break;
		}

	}

	private void printMovieDetails(MovieListing movie) {
		System.out.println("-----Movie: (" + movie.getMovieTitle() + ")-----");
		System.out.println("Status: " + movie.getMovieStatus());
		System.out.println("Director: " + movie.getMovieDirector());
		System.out.println("Duration: " + movie.getMovieDuration());
		System.out.println("Cast: " + movie.getMovieCastList());
		System.out.println("Genre: " + movie.getMovieGenre());
		System.out.println("Rating: " + movie.getMovieRating());
		System.out.println("Synopsis: " + movie.getMovieSynopsis());
		System.out.println("Reviews: " + movie.getMovieReview()); // need to get from review
		System.out.println("Overall Rating: " + movie.getOverallRating()); // need to get from review
	}

	// Get users movie selection
	private MovieListing getMovieChoice() {
		ArrayList<MovieListing> movieListing = new ArrayList<MovieListing>();
		System.out.println("Choose a movie.");
		int movieChoice = sc.nextInt();

		MovieListing movie = movieListing.get(movieChoice);
		return movie;
	}

	// listTop5Movies
	private void listTop5Movies() {
		System.out.println("Top 5 Movies Sold: \n");
		System.out.println("Top 5 Movies Rated: \n");
	}

	private void viewBookingHistory() {
		System.out.println("Booking History \n");
	}

	public class InvalidSeatException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidSeatException() {
		}

		@Override
		public String getMessage() {
			return "An invalid seat is entered. Please enter try again!";
		}
	}

}
