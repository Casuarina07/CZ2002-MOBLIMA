//MOVIEGOER Controller
package control;

import java.io.IOException;
import java.util.Scanner;

import model.MovieListing;
import view.BookingHistoryUI;
import view.ListTopMoviesUI;
import view.MovieListUI;
import view.ReviewUI;
import view.SearchUI;

/**
 * Controller for redirecting to different views
 * @author Casuarina07
 *
 */
public class MovieGoerController {
	private static MovieGoerController movieGoerCon = null;
	private Scanner sc;
	private MovieListing movie;
	
	private MovieGoerController() {
		sc = new Scanner(System.in);
	}

	public static MovieGoerController getInstance() {
		if(movieGoerCon == null) {
			movieGoerCon = new MovieGoerController();
		}
		return movieGoerCon;
	}

	/**
	 * Redirecting to different views
	 * @param choice
	 * @throws IOException
	 */
	public void displayUI(int choice) throws IOException {
		// TODO Auto-generated method stub
		switch (choice) {
		case 1:
			SearchUI searchUI = SearchUI.getInstance();
			searchUI.searchMovie();
			break;
		case 2:
			MovieListUI movieListUI = MovieListUI.getInstance();
			movieListUI.listAllMovie();
			break;
		case 3:
			ListTopMoviesUI listTopMoviesUI = ListTopMoviesUI.getInstance();
			listTopMoviesUI.listTop5Movies();
			break;
		case 4:
			BookingHistoryUI bookingHistoryUI = BookingHistoryUI.getInstance();
			BookingHistoryUI.viewBookingHistory();
			break;
		case 5: 
			ReviewUI reviewUI = ReviewUI.getInstance();
			reviewUI.reviewMovie();
		case 0:
			break;
		default:
			System.out.println("Invalid choice, please try again");
			break;
		}
	}
}
