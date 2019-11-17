package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.DatabaseController;
import control.SearchAndListController;
import model.MovieListing;
import model.Rating;

public class SearchUI {
	private static SearchUI searchUI = null;
	private Scanner sc;
	
	private SearchUI() {
		sc = new Scanner(System.in);
	}

	public static SearchUI getInstance() {
		if (searchUI == null) {
			searchUI = new SearchUI();
		}
		return searchUI;
	}
	
	public void searchMovie()
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
		
		ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
		movieListings = DatabaseController.readMovieListing();
		
		ArrayList<Rating> ratedMovies = new ArrayList<Rating>();
		ratedMovies = DatabaseController.readReviewCSV();

		
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
			System.out.print("Cast: ");
			ArrayList<String> casts = new ArrayList<String>(); 
					casts = movieSearching.get(movieChoice-1).getMovieCastList();
			for(String cast : movieSearching.get(movieChoice-1).getMovieCastList()) {
				System.out.print(cast + "; ");
			}
			System.out.println("Genre: " + movieSearching.get(movieChoice-1).getMovieGenre());
			System.out.println("Rating: " + movieSearching.get(movieChoice-1).getMovieRating());
			System.out.println("Synopsis: " + movieSearching.get(movieChoice-1).getMovieSynopsis());
			for (int i = 0; i < ratedMovies.size(); i++) {
				if(ratedMovies.get(i).getMovieTitle().equals(movieSearching.get(movieChoice-1).getMovieTitle())) {
					System.out.println("Reviews: " + ratedMovies.get(i).getMovieReview()); 
					System.out.println("Overall Rating: " + ratedMovies.get(i).getMovieRating() + "/5");
					break;
				}
				if(i==ratedMovies.size()-1) {
					System.out.println("Review: NA");
					System.out.println("Overall Ratings: NA");
				}
			}
			
			break;
		case 2:
			
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
}
