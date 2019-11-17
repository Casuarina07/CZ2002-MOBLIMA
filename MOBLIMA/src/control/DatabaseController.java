package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import model.Cinema;
import model.Cineplex;
import model.MovieListing;
import model.Transaction;

/**
 * @author David Loh Shun Hao
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-13
 */
public class DatabaseController {
	private static DatabaseController controller = null;
	private static BufferedReader bufferedReader = null;

	public static DatabaseController getInstance() {
		if (controller == null) {
			controller = new DatabaseController();
		}
		return controller;
	}

	/**
	 * Initialise a new BufferedReader
	 * 
	 * @param fileName the name of the file to be read
	 */
	private static void initBufferReader(String fileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("src/storage/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<MovieListing> readMovieListing() {
		String filePath = "src/storage/MovieListing.csv";

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();

		try {
			br = new BufferedReader(new FileReader(filePath));

			while ((line = br.readLine()) != null) {
				String[] value = line.split(cvsSplitBy);

				String movieTitle = value[0];
				// movieStatus
				MovieListing.MovieStatus movieStatus = MovieListing.MovieStatus.valueOf(value[1]);
				String movieDirector = value[2];
				int movieDuration = Integer.parseInt(value[3]);
				// casts arrayList
				ArrayList<String> casts = new ArrayList<String>();
				String[] castsList = value[4].split(";");
				for (String cast : castsList) {
					casts.add(cast);
				}
				MovieListing.MovieGenre movieGenre = MovieListing.MovieGenre.valueOf(value[5]);

				MovieListing.MovieRating movieRating = MovieListing.MovieRating.valueOf(value[6]);

				String movieSynopsis = value[7];

				MovieListing movie = new MovieListing(movieTitle, movieStatus, movieDirector, movieDuration, casts,
						movieGenre, movieRating, movieSynopsis);
				movies.add(movie);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return movies;
	}

	/**
	 * Returns a ArrayList of Cineplex object
	 * 
	 * @return An ArrayList of Cineplex object
	 * @see Cineplex
	 */
	public static ArrayList<Cineplex> readCineplex() {
		initBufferReader("Cineplex.csv");

		String line = "";
		ArrayList<Cineplex> cineplexs = new ArrayList<Cineplex>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				String cineplexName = value[0];
				String[] cinemas = value[1].split(";");
				cineplexs.add(new Cineplex(cineplexName, cinemas));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cineplexs;
	}

	/**
	 * Returns an ArrayList of Cinema objects that belongs to the cinplex selected
	 * by the user.
	 *
	 * @param cineplex a cinplex object choosen by the user
	 * @return An ArrayList of Cinema objects belonging to the cineplex
	 * @see Cinema
	 */
	public static ArrayList<Cinema> readCinema(Cineplex cineplex) {
		initBufferReader("Cinema.csv");

		String line = "";
		ArrayList<Cinema> cinemaArry = new ArrayList<Cinema>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				String cinemaCode = value[0];
				String cinemaName = value[1];
				String readCineplex = value[2];
				if (readCineplex.equals(cineplex.getCineplexName())) {
					cinemaArry.add(new Cinema(cinemaCode, cinemaName));

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cinemaArry;
	}

	/**
	 * Returns an List of ArrayList that contains the hall number, show times, seat
	 * ID of occupied seats.
	 *
	 * @param movieTitle   title of the selected movie
	 * @param cineplexName name of selected cineplex
	 * @param cinemaName   name of selected cinema
	 * @return A List of ArrayLists containing hall numbers, show times and seat ID
	 *         of occupied seats
	 */
	public ArrayList<String>[] readShowTime(String movieTitle, String cineplexName, String cinemaName) {
		initBufferReader("MovieShowing.csv");
		String line = "";
		ArrayList<String>[] list = new ArrayList[3];
		ArrayList<String> hallNumbers = new ArrayList<String>();
		ArrayList<String> showTimes = new ArrayList<String>();
		ArrayList<String> occupiedSeats = new ArrayList<String>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				String readMovieTitle = value[0];
				String readCineplexName = value[1];
				String readCinemaName = value[2];

				if (movieTitle.equals(readMovieTitle) && cineplexName.equals(readCineplexName)
						&& cinemaName.equals(cinemaName)) {
					hallNumbers.add(value[3]);
					showTimes.add(value[4]);
					occupiedSeats.add(value[5]);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		list[0] = hallNumbers;
		list[1] = showTimes;
		list[2] = occupiedSeats;

		return list;
	}

	/**
	 * Writes into MovieShowing.csv seat ID of selected seat
	 * 
	 * @param movieTitle   title of selected movie
	 * @param cineplexName name of selected cineplex
	 * @param cinemaName   name of selected cinema
	 * @param showTime     selected show time
	 * @param seatID       seat ID of selected seat
	 */
	public void writeOccupiedSeat(String movieTitle, String cineplexName, String cinemaName, String showTime,
			String seatID) {
		try {
			File inputFile = new File("src/storage/MovieShowing.csv");

			// Read existing file
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();
			// get CSV row column and replace with by using row and column
			for (int i = 0; i < csvBody.size(); i++) {
				String[] strArray = csvBody.get(i);
				if (strArray[0].equals(movieTitle) && strArray[1].equals(cineplexName) && strArray[2].equals(cinemaName)
						&& strArray[4].equals(showTime)) {
					csvBody.get(i)[5] = csvBody.get(i)[5] + ";" + seatID;
				}
			}
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			writer.writeAll(csvBody);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Writes into Transaction.csv the transaction
	 * 
	 * @param transaction Transaction object of the transaction
	 * 
	 * @see Transaction
	 */
	public void writeTransaction(Transaction transaction) {
		try {
			// create FileWriter object with file as parameter
			CSVWriter writer = new CSVWriter(new FileWriter(new File("src/storage/Transaction.csv"), true), ',',
					CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			String[] transactions = { transaction.getTransactionID(), transaction.getMovieTitle(),
					transaction.getCineplexName(), transaction.getCinemaName(), transaction.getMovieShowtime(),
					transaction.getSeatID(), String.valueOf(transaction.getTotalAmount()) };
			writer.writeNext(transactions);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}