package view;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import control.DatabaseController;
import control.StaffController;
import model.Cinema;
import model.Cineplex;
import model.MovieListing;
import model.MovieListing.MovieGenre;
import model.MovieListing.MovieRating;
import model.MovieListing.MovieStatus;
import model.MovieShowing;
import model.ShowTime;

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
	private static ArrayList<MovieShowing> movieShowingArrayList = MovieShowing.getMovieShows(); 
	private static List<MovieListing> movieList = new ArrayList<MovieListing>();
	
	DatabaseController databaseCon = DatabaseController.getInstance();
	
	private static StaffController staffCon = StaffController.getInstance();
	
	private StaffUI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffUI getInstance() {
		if(staffUI == null) {
			staffUI = new StaffUI();
		}
		return staffUI;
	}
	
	/**
	 * Display a menu with options
	 * @throws IOException 
	 */
	public void displayMenu() throws IOException {
		int choice;
		
		do {
			System.out.println("____________ STAFF MENU _____________\n"
							+ "                                     \n"
							+ " 1. Add New Movie		            \n"
							+ " 2. Edit Movie Details	            \n"
							+ " 3. Configure Settings	            \n"
							+ " 4. View Top 5 Movies            	\n"
							+ " 0. Logout                           \n"
							+ "_____________________________________\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					addNewMovie();
					break;
				case 2:
					editAMovie();
					break;
				case 3:
					StaffConfigSystemUI staffCS = StaffConfigSystemUI.getInstance();
					staffCS.displayMenu();
					break;
				case 4:
					StaffManageTop5UI staffMT5 = StaffManageTop5UI.getInstance();
					staffMT5.displayMenu();
					break;
				case 0:
					System.out.println("You have successfully logged out!");
					MoblimaApp.mainMenu();
					return;
				default:
					System.out.println("Invalid choice, please try again");
					break;
			}
			
		} while(choice >= 0 || choice <= 8);
	}
	
	/**
	 * addNewMovie() : creates new movie and its details
	 * @throws IOException 
	 */
	private static void addNewMovie() throws IOException {
		System.out.println("____________ ADD NEW MOVIE ____________\n");
		System.out.println("Enter movie name: ");
		String movieTitle = sc.next();
		System.out.println("Enter movie synopsis: ");
		String synopsis = sc.next();
		MovieListing.MovieStatus status = selectMovieStatus();
		MovieListing.MovieGenre genre = selectMovieGenre();
		MovieListing.MovieRating rating = selectMovieRating();
		System.out.println("Enter director name: ");
		String movieDirector = sc.nextLine();
		
		// Loop to get all cast names
		ArrayList<String> movieCast = loopCastNames("Enter movie cast ");
		
		System.out.println("Enter movie duration: ");
		int duration = sc.nextInt();
		
		//add showtime
		movieShowing = addMovieShowing(movie);

		movie = new MovieListing(movieTitle, 
								status, 
								movieDirector, 
								duration, 
								movieCast, 
								genre, 
								rating, 
								synopsis);
		//staffCon.saveMovieListing(movie);
	staffCon.saveMovieListing(movieTitle, status,movieDirector, duration, movieCast, genre, rating, synopsis);
	
		//add movie showing
		movieShowing = new MovieShowing(movieTitle, 
										movieShowing.getCinema(), 
										movieShowing.getCineplex(), 
										movieShowing.getShowTimes());
		staffCon.saveMovieShowing(movieShowing);
	}
	
	//


	/**
	 * @param mList : MovieListing object that is receiving show times
	 * @return MovieShowing object
	 */
	private static MovieShowing addMovieShowing(MovieListing mList) {
		//initialise MovieShowing
		MovieShowing movieShow = new MovieShowing();
		String cinema, showTime;
		Cineplex cineplex;
		
		do {
			System.out.println("____________ ADD NEW MOVIE SHOWING TIME ____________\n");
			
			//select cineplex name
			cineplex = selectCineplex();

			//select cinema name
			cinema = selectCinema(cineplex);
			
			//key in movie showtimes
			if(cineplex != null && cinema != null) {
				System.out.println("Enter movie showing date and time for " + cineplex.getCineplexName() + " @ " + cinema + " (Format: YYYY-MM-dd-hh-min): ");	
				showTime = sc.nextLine();
				
				if(!showTime.isEmpty()) {
					String[] dtLine = showTime.split("-");
					if(dtLine.length == 5) {
						ShowTime showingDT = new ShowTime(Integer.parseInt(dtLine[0]), 
														Integer.parseInt(dtLine[1]),
														Integer.parseInt(dtLine[2]),
														Integer.parseInt(dtLine[3]),
														Integer.parseInt(dtLine[4]));
						movieShow.addShowTime(showingDT);
					} else {
						System.out.println("You have entered wrong input. Please re-enter!");
						break;
					}
				}
			}
			//check if cineplex is null
		} while (cineplex==null);

		return movieShow;
	}

	/**
	 * editAMovie() : edits and updates the selected movie
	 */
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
			System.out.println("Select which component to edit: \n" + "| 1. Movie Title\n"
					+ "| 2. Movie Synopsis\n" + "| 3. Movie Director\n"
					+ "| 4. Movie Duration\n" + "| 5. Movie Cast List\n"
					+ "| 6. Movie Genre\n" + "| 7. Movie Rating\n"
					+ "| 8. Movie Status\n" + "| 9. Movie Showing Details\n"
					+ "| 0. Back to Staff Menu\n" + "__________________________________\n"
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
				movie = newMovieDetails;
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
			databaseCon.updateMovie(movieToUpdate, newMovieDetails);

//			
		} while (choice > 0 || choice <= 9);
//		staffCon.updateMovieListing(movie);
	}
	/**
	 * selectMovieStatus() : select the movie status that was stored in enum
	 * @return the index of the select enum
	 */
	private MovieListing selectMovie() {

		ArrayList<MovieListing> movieListings = databaseCon.readMovieListing();

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
	 * selectMovieStatus() : select the movie status that was stored in enum
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieStatus selectMovieStatus() {
		System.out.println("Select movie status: \n");
		
		MovieListing.MovieStatus[] status = MovieListing.MovieStatus.values();
		for(int i = 0; i < status.length; i++) {
			System.out.println(String.format("%d. %s", i+1, status[i].name()));
		}
		
		int index = readChoice(1, status.length+1) - 1;
		return status[index];
	}
	
	/**
	 * selectMovieGenre() : select the movie genre that was stored in enum
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieGenre selectMovieGenre(){
		System.out.println("Select movie genre: \n");
		
		MovieListing.MovieGenre[] genre = MovieListing.MovieGenre.values();
		for(int i = 0; i < genre.length; i++) {
			System.out.println(String.format("%d. %s", i+1, genre[i].name()));
		}
		int index = readChoice(1, genre.length+1) - 1;
		return genre[index];
	}
	
	/**
	 * selectMovieRating() : select the movie rating that was stored in enum
	 * @return the index of the select enum
	 */
	private static MovieListing.MovieRating selectMovieRating(){
		System.out.println("Select movie rating: \n");
		
		MovieListing.MovieRating[] rating = MovieListing.MovieRating.values();
		for(int i = 0; i < rating.length; i++) {
			System.out.println(String.format("%d. %s", i+1, rating[i].name()));
		}
		
		int index = readChoice(1, rating.length+1) - 1;
		return rating[index];
	}

	/**
	 * readChoice() : Validates that it is an integer value
	 * @param min
	 * @param max
	 * @return c : returns an integer value
	 */
	protected static int readChoice(int min, int max) {
		
		int c = 0;
		
		do {
			try {
				c = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ime){
				System.out.println("Please input an integer value");
			}
		} while (!(c >= min && c <= max));
		return c;
	}
	
	/**
	 * @return the index of the selected movie
	 */
	
	
	
	/**
	 * selectCineplex() : To select Cineplex
	 * @return the index of the selected Cineplex
	 */
	public static Cineplex selectCineplex() {
		int choice = 1;
		
		cineplexArrayList = DatabaseController.readCineplex();
		for(Cineplex cineplex : cineplexArrayList) {
			System.out.println(choice + ". " + cineplex.getCineplexName());
			choice++;
		}
		System.out.println("Select a cineplex: ");
		int index = readChoice(1, cineplexArrayList.size()+1) - 1;
		return cineplexArrayList.get(index);
		
	}
	
	/**
	 * selectCinema() : To select Cinema from a particular Cineplex
	 * @return the string of selected cinema name
	 */
	public static String selectCinema(Cineplex cineplex) {
		int choice = 1;
		cinemaArrayList = DatabaseController.readCinema(cineplex);
		for(Cinema cinema : cinemaArrayList) {
			System.out.println(choice + ". " + cinema.getCinemaName());
			choice++;
		}
		
		System.out.println("Select a cinema: ");
		int index = readChoice(1, cinemaArrayList.size()+1) - 1;
		return cinemaArrayList.get(index).getCinemaName();
	}
	
	/**
	 * loopCastNames() : loop to store as a list of movie casts
	 * @param msg : Gets the specific message to display on the question
	 * @return 
	 */
	public static ArrayList<String> loopCastNames(String msg) {
		String castName;
		int i = 1; 
		sc.nextLine();
		do {
			System.out.println(msg + i + " Name (Press Enter to stop adding cast name): ");
			castName = sc.nextLine();
			if(!castName.isEmpty()) {
				movie.addMovieCast(castName);
				i++;
			}
		} while (!castName.isEmpty());
		return movie.getMovieCastList();
		
	}
	
	/**
	 * Remove the movie by changing the status to MovieListing.ENDSHOWING
	 */
	private static void removeMovieAndShowing(MovieListing ml, MovieShowing ms) {
		
		staffCon.removeMovieAndShowing(ml, ms);
	}
}
