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
	
	public static ArrayList<Cinema> readCinema() {
		initBufferReader("Cinema.csv");

		String line = "";
		ArrayList<Cinema> cinema = new ArrayList<Cinema>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				String cinemaCode = value[0];
				String cinemaName = value[1];
				cinema.add(new Cinema(cinemaCode, cinemaName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cinema;
	}

	public String[] readShowTime(String movieTitle, String cineplexName, String cinemaName) {
		initBufferReader("MovieShowing.csv");
		String line = "";
		String[] showTimes = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				String readMovieTitle = value[0];
				String readCineplexName = value[1];
				String readCinemaName = value[2];
				showTimes = value[3].split(";");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return showTimes;
	}
}
