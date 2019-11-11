package view;
import java.util.ArrayList;
import java.util.Scanner;

import control.SeachAndListMovieCSV;
import model.MovieListing;

public class MovieGoerUI {

	private static MovieGoerUI movieGoerUI = null;
	private Scanner sc; 
	private MovieListing movie;
	
	private MovieGoerUI() {
		sc = new Scanner(System.in);
	}
	
	public static MovieGoerUI getInstance() {
		if(movieGoerUI == null) {
			movieGoerUI = new MovieGoerUI();
		}
		return movieGoerUI;
	}
	
	public void displayMenu() {
		int choice;
		
		do {
			System.out.println("_____________WELCOME!!_________________\n"
					+ "| 1. Search Movie                 |\n" 
					+ "| 2. List All Movie               |\n"
					+ "| 3. Book Ticket                  |\n"
					+ "| 4. List Top 5 Movies            |\n"
					+ "| 5. View Booking History         |\n"
					+ "| 0. Exit Program                 |\n"
					+ "____________________________________");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					System.out.println("Please enter movie title: ");
					Scanner scTitle = new Scanner(System.in);
					String titleInput = scTitle.nextLine();
					
					ArrayList<MovieListing> movieSearching = new ArrayList<MovieListing>();	
					movieSearching = SeachAndListMovieCSV.searchMovieCSV(titleInput);
					
					for (int i = 0; i < movieSearching.size(); i++) 
					{
						System.out.println(i + ". " + movieSearching.get(i).getMovieTitle());
					}
					break;
				case 2:
					ArrayList<MovieListing> movieListings = new ArrayList<MovieListing>();
					movieListings = SeachAndListMovieCSV.readMovieCSV();
					for (int i = 0; i < movieListings.size(); i++) 
					{
						System.out.println(i + ". " + movieListings.get(i).getMovieTitle());
					}
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
			
		} while(choice > 0);
	}
	//listTop5Movies
	private void listTop5Movies() {
		System.out.println("Top 5 Movies Sold: \n"); 
		System.out.println("Top 5 Movies Rated: \n"); 
	}

	private void viewBookingHistory() {
		System.out.println("Booking History \n"); 
	}

}
