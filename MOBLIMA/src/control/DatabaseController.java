package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Cinema;
import model.Cineplex;
import model.MovieListing;

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

	private static void initBufferReader(String fileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("src/storage/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

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

	public ArrayList<String>[] readShowTime(String movieTitle, String cineplexName, String cinemaName) {
		initBufferReader("MovieShowing.csv");
		String line = "";
		ArrayList<String>[] list = new ArrayList[2];
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
					showTimes.add(value[4]);
					occupiedSeats.add(value[5]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		list[0] = showTimes;
		list[1] = occupiedSeats;
		return list;
	}
}
