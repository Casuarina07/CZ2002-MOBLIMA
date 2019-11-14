package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.SearchAndListController;
import model.MovieListing;

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
			System.out.println("_____________WELCOME!!_________________\n" + "| 1. Search Movie                 |\n"
					+ "| 2. List All Movie               |\n" + "| 3. Book Ticket                  |\n"
					+ "| 4. List Top 5 Movies            |\n" + "| 5. View Booking History         |\n"
					+ "| 0. Exit Program                 |\n" + "____________________________________");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				searchMovie();
				break;
			case 2:
				listAllMovie();
				break;
			case 3:

				break;
			case 4:
				listTop5Movies();
				break;
			case 5:
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
	private void searchMovie()
	{
		System.out.println("Enter movie title: ");
		Scanner scTitle = new Scanner(System.in);
		String titleInput = scTitle.nextLine();

		ArrayList<MovieListing> movieSearching = new ArrayList<MovieListing>();
		movieSearching = SearchAndListController.searchMovieCSV(titleInput);

		for (int i = 0; i < movieSearching.size(); i++) {
			System.out.println((i + 1) + ". " + movieSearching.get(i).getMovieTitle());
		}	
		if(movieSearching.size() == 0)
		{
			return;
		}
		
		//
		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = SearchAndListController.readMovieCSV();
		
		System.out.println("Choose a movie: ");
		int movieChoice = sc.nextInt();
		System.out.println("You have selected: " + movieSearching.get(movieChoice-1).getMovieTitle());
		
		System.out.println("1. View movie details");
		System.out.println("2. Book the movie");

		int option;
		option = sc.nextInt();
	
		switch (option) {
		case 1:
			System.out.println("-----Movie: (" + movieSearching.get(movieChoice-1).getMovieTitle()+")-----");
			System.out.println("Status: " + movieSearching.get(movieChoice-1).getMovieStatus());
			System.out.println("Director: " + movieSearching.get(movieChoice-1).getMovieDirector());
			System.out.println("Duration: " + movieSearching.get(movieChoice-1).getMovieDuration());
			System.out.println("Cast: " + movieSearching.get(movieChoice-1).getMovieCastList());
			System.out.println("Genre: " + movieSearching.get(movieChoice-1).getMovieGenre());
			System.out.println("Rating: " + movieSearching.get(movieChoice-1).getMovieRating());
			System.out.println("Synopsis: " + movieSearching.get(movieChoice-1).getMovieSynopsis());
			System.out.println("Reviews: " + movieSearching.get(movieChoice-1).getMovieReview()); //need to get from review
			System.out.println("Overall Rating: " + movieSearching.get(movieChoice-1).getOverallRating()); //need to get from review 
			break;
		case 2:
			
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	
	
	// List all Movie and details
	private void listAllMovie()
	{
		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = SearchAndListController.readMovieCSV();
		System.out.println("--------All Movies-----------");
		for (int i = 0; i < movieListings.size(); i++) {
			System.out.println((i + 1) + ". " + movieListings.get(i).getMovieTitle());
		}
		
		
		System.out.println("Choose a movie: ");
		int movieChoice = sc.nextInt();
		if(movieChoice -1 >= movieListings.size() || movieChoice <= 0)
		{
			System.out.println("Please select a valid number");
			return;
		}
		System.out.println("You have selected: " + movieListings.get(movieChoice-1).getMovieTitle());
		
		System.out.println("1. View movie details");
		System.out.println("2. Book the movie");

		int option;
		option = sc.nextInt();
	
		switch (option) {
		case 1:
			System.out.println("-----Movie: (" + movieListings.get(movieChoice-1).getMovieTitle()+")-----");
			System.out.println("Status: " + movieListings.get(movieChoice-1).getMovieStatus());
			System.out.println("Director: " + movieListings.get(movieChoice-1).getMovieDirector());
			System.out.println("Duration: " + movieListings.get(movieChoice-1).getMovieDuration());
			System.out.println("Cast: " + movieListings.get(movieChoice-1).getMovieCastList());
			System.out.println("Genre: " + movieListings.get(movieChoice-1).getMovieGenre());
			System.out.println("Rating: " + movieListings.get(movieChoice-1).getMovieRating());
			System.out.println("Synopsis: " + movieListings.get(movieChoice-1).getMovieSynopsis());
			System.out.println("Reviews: " + movieListings.get(movieChoice-1).getMovieReview()); //need to get from review
			System.out.println("Overall Rating: " + movieListings.get(movieChoice-1).getOverallRating()); //need to get from review 
			break;
		case 2:
			
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
		
	}
	

	
	
	// listTop5Movies
	private void listTop5Movies() {
		System.out.println("Top 5 Movies Sold: \n");
		System.out.println("Top 5 Movies Rated: \n");
	}

	private void viewBookingHistory() {
		System.out.println("Booking History \n");
	}

}
