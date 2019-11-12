package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Cinema;
import model.Cineplex;
import model.MovieListing;

public class DatabaseController {
	private static DatabaseController controller = null;
	private BufferedReader bufferedReader = null;

	public static DatabaseController getInstance() {
		if (controller == null) {
			controller = new DatabaseController();
		}
		return controller;
	}

	private void initBufferReader(String fileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("src/storage/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Cineplex> readCineplex() {
		initBufferReader("Cineplex.csv");

		String line = "";
		ArrayList<Cineplex> cineplexs = new ArrayList<Cineplex>();

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] value = line.split(",");
				int cineplexId = Integer.parseInt(value[0]);
				String cineplexName = value[1];
				String[] cinemas = value[2].split(";");
				cineplexs.add(new Cineplex(cineplexId, cineplexName, cinemas));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cineplexs;
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
