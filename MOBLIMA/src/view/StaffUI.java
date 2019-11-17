package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.xml.crypto.Data;

import control.DatabaseController;
import control.StaffController;
import model.Cinema;
import model.Cineplex;
import model.MovieListing;
import model.MovieShowing;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class StaffUI {

	private static Scanner sc;
	private static StaffUI staffUI = null;

	private static MovieListing movie = new MovieListing();
	static MovieShowing movieShowing = new MovieShowing();

	private static ArrayList<MovieListing> movieArrayList = MovieListing.getMovieList();
	private static ArrayList<Cineplex> cineplexArrayList = Cineplex.getCineplexList();
	private static ArrayList<Cinema> cinemaArrayList = Cineplex.getCinemaList();
	private static List<MovieListing> movieList = new ArrayList<MovieListing>();

	private static StaffController staffCon = StaffController.getInstance();
	DatabaseController controller = DatabaseController.getInstance();

	private StaffUI() {
		sc = new Scanner(System.in);
	}

	public static StaffUI getInstance() {
		if (staffUI == null) {
			staffUI = new StaffUI();
		}
		return staffUI;
	}

	/**
	 * Display a menu with options
	 */
	public void displayMenu() {
		int choice;

		do {
			System.out.println("____________ STAFF MENU ____________\n" + "                                     \n"
					+ " 1. Add New Movie		            \n" + " 2. Edit Movie Details	            \n"
					+ " 3. Configure Settings	            \n" + " 4. Manage Sales		            	\n"
					+ " 0. Logout                           \n" + "_____________________________________\n"
					+ " Enter your choice here: ");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
//					addNewMovie();
				break;
			case 2:
				editAMovie();
				break;
			case 3:
//				StaffConfigSystemUI staffCS = StaffConfigSystemUI.getInstance();
//				staffCS.displayMenu();
				break;
			case 4:
				StaffManageTop5UI staffMT5 = StaffManageTop5UI.getInstance();
				staffMT5.displayMenu();
				break;
			case 0:
				System.out.println("You have successfully logged out!");
				return;
			default:
				System.out.println("Invalid choice, please try again");
				break;
			}

		} while (choice >= 0 || choice <= 8);
	}

	private void editAMovie() {

		int choice, movieDuration;
		String movieTitle, movieSynopsis, movieDirector, movieCast;
		MovieListing.MovieGenre genre;
		MovieListing.MovieRating rating;
		MovieListing.MovieStatus status;
		MovieListing movieToUpdate = selectMovie();
		MovieListing newMovieDetails = new MovieListing(movieToUpdate.getMovieTitle(), movieToUpdate.getMovieStatus(),
				movieToUpdate.getMovieDirector(), movieToUpdate.getMovieDuration(), movieToUpdate.getMovieCastList(),
				movieToUpdate.getMovieGenre(), movieToUpdate.getMovieRating(), movieToUpdate.getMovieSynopsis());

		MovieShowing movieShowingToUpdate = null;

		System.out.println("____________ EDIT MOVIE DETAILS ____________\n");
		System.out.println("____________ " + movieToUpdate.getMovieTitle() + " MOVIE ____________\n");
		do {
			System.out.println("Select which component to edit: \n" + "| 1. Movie Title	 		        |\n"
					+ "| 2. Movie Synopsis	            |\n" + "| 3. Movie Director		        |\n"
					+ "| 4. Movie Duration   	        |\n" + "| 5. Movie Cast List		        |\n"
					+ "| 6. Movie Genre 		        |\n" + "| 7. Movie Rating		        |\n"
					+ "| 8. Movie Status		        |\n" + "| 9. Movie Showing Details       |\n"
					+ "| 0. Back to Staff Menu          |\n" + "__________________________________\n"
					+ " Enter your choice here: ");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter new movie title: \n");
				movieTitle = sc.next();
				newMovieDetails.setMovieTitle(movieTitle);
				break;
			case 2:
				System.out.println("Enter new movie synopsis: \n");
				movieSynopsis = sc.next();
				newMovieDetails.setMovieSynopsis(movieSynopsis);
				break;
			case 3:
				System.out.println("Enter new movie director: \n");
				movieDirector = sc.next();
				newMovieDetails.setMovieDirector(movieDirector);
				break;
			case 4:
				System.out.println("Enter new movie duration: \n");
				movieDuration = sc.nextInt();
				newMovieDetails.setMovieDuration(movieDuration);
				break;
			case 5:
				loopCastNames("Enter new movie cast ");
				break;
			case 6:
				genre = selectMovieGenre();
				newMovieDetails.setMovieGenre(genre);
				break;
			case 7:
				rating = selectMovieRating();
				newMovieDetails.setMovieRating(rating);
				break;
			case 8:
				status = selectMovieStatus();
				// if the status = EndShowing
				if (status.ENDSHOWING == MovieListing.MovieStatus.ENDSHOWING) {
					// remove movie and its showtime
//					removeMovieAndShowing(movieToUpdate, movieShowingToUpdate);
				} else {
					newMovieDetails.setMovieStatus(status);
				}
				break;
			case 9:
				// modify movie showing details
//				addMovieShowing(movieToUpdate);
				break;
			case 0:
				return;
			}
			controller.updateMovie(movieToUpdate, newMovieDetails);

//			
		} while (choice > 0 || choice <= 9);
//		staffCon.updateMovieListing(movie);
	}

	private MovieListing selectMovie() {

		ArrayList<MovieListing> movieListings = controller.readMovieListing();

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
		return (movieListings.get(movieChoice - 1));
	}

	/**
	 * loopCastNames() : loop to store as a list of movie casts
	 * 
	 * @param msg : Gets the specific message to display on the question
	 * @return
	 */
	public static ArrayList<String> loopCastNames(String msg) {
		String castName = null;
		int i = 1;
		do {
			System.out.println(msg + i + " Name (Press Enter to stop adding cast name): ");
			castName = sc.nextLine();
			if (!castName.isEmpty()) {
				movie.addMovieCast(castName);
				i++;
			}
		} while (!castName.isEmpty());
		return movie.getMovieCastList();

	}

	/**
	 * selectMovieGenre() : select the movie genre that was stored in enum
	 * 
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieGenre selectMovieGenre() {
		System.out.println("Select movie genre: \n");

		MovieListing.MovieGenre[] genre = MovieListing.MovieGenre.values();
		for (int i = 0; i < genre.length; i++) {
			System.out.println(String.format("%d. %s", i + 1, genre[i].name()));
		}
		int index = readChoice(1, genre.length + 1) - 1;
		return genre[index];
	}

	/**
	 * readChoice() : Validates that it is an integer value
	 * 
	 * @param min
	 * @param max
	 * @return c : returns an integer value
	 */
	protected static int readChoice(int min, int max) {

		int c = 0;

		do {
			try {
				c = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ime) {
				System.out.println("Please input an integer value");
			}
		} while (!(c >= min && c <= max));
		return c;
	}

	/**
	 * selectMovieRating() : select the movie rating that was stored in enum
	 * 
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieRating selectMovieRating() {
		System.out.println("Select movie rating: \n");

		MovieListing.MovieRating[] rating = MovieListing.MovieRating.values();
		for (int i = 0; i < rating.length; i++) {
			System.out.println(String.format("%d. %s", i + 1, rating[i].name()));
		}

		int index = readChoice(1, rating.length + 1) - 1;
		return rating[index];
	}

	/**
	 * selectMovieStatus() : select the movie status that was stored in enum
	 * 
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieStatus selectMovieStatus() {
		System.out.println("Select movie status: \n");

		MovieListing.MovieStatus[] status = MovieListing.MovieStatus.values();
		for (int i = 0; i < status.length; i++) {
			System.out.println(String.format("%d. %s", i + 1, status[i].name()));
		}

		int index = readChoice(1, status.length + 1) - 1;
		return status[index];
	}
}
