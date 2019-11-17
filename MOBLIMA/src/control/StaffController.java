package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import model.MovieListing;
import model.MovieShowing;
import model.ShowTime;
import model.Staff;
import model.MovieListing.MovieGenre;
import model.MovieListing.MovieRating;
import model.MovieListing.MovieStatus;


/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class StaffController {
	private Scanner sc;
	private static StaffController staffCon = null;
	private static CSVReader csvReader = null;
	private static CSVWriter csvWriter = null;
	
	private MovieListing movie;
	private static ArrayList<MovieListing> movieArrayList = MovieListing.getMovieList();
	private static ArrayList<Staff> staffArrayList = Staff.getStaffList();
	
	private static final String COMMA_DELIMITER = ",";
	
	private StaffController() {
		sc = new Scanner(System.in);
	}

	public static StaffController getInstance() {
		if(staffCon == null) {
			staffCon = new StaffController();
		}
		return staffCon;
	}	
	
	private static void readToCSV(String fileName) {
		try {
			csvReader = new CSVReader(new FileReader("src/storage/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeToCSV(String fileName) {
		try {
			csvWriter = new CSVWriter(new FileWriter("src/storage/" + fileName, true),
													CSVWriter.DEFAULT_SEPARATOR, 
													CSVWriter.NO_ESCAPE_CHARACTER,
													CSVWriter.NO_QUOTE_CHARACTER,
													CSVWriter.DEFAULT_LINE_END);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * loadStaffList method = This reads the Staff CSV file to retrieve all Staff records.
	 * @return boolean when there are Staff records in the CSV file
	 */
	public static boolean loadStaffList() {
		try {
			String line;
			
			//read csv file
			BufferedReader br = new BufferedReader(new FileReader("src/storage/Staff.csv"));
			
			//skip header
			br.readLine();
			
			//reading from second line
			while((line = br.readLine()) != null) {
				String[] staffDetails = line.split(COMMA_DELIMITER);
				if(staffDetails.length > 0 ) {
					// save staff details in Staff Object
					Staff s = new Staff(staffDetails[0], staffDetails[1], staffDetails[2]);
					staffArrayList.add(s);
					return true;
				} else {
					throw new IOException("Retrieval of staff data is wrong!");
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Validate staff login credentials by a list of staff records
	 * @param username : user input username for identification
	 * @param password : user input password for verification
	 * @return Staff object
	 */
	public Staff validateStaffLogin(String username, String password){
		for(Staff staff : staffArrayList) {
			if(staff.getStaffName().equals(username) && 
					staff.getStaffPwd().equals(password)) {
				return staff;
			}
		}
		
		return null;
	}
	
	/**
	 * saveMovieListing() : to store all movies and its details
	 * @param movieListing : 
	 */
	public void saveMovieListing(MovieListing movieListing) {
		//add to database
		try {
			writeToCSV("MovieListing.csv");

			ArrayList<String> casts = movieListing.getMovieCastList();
			
			String castStr = "";
			//loop casts
			for(int i=0; i<casts.size(); i++) {
				//form string of cast names
				castStr += ";";
				castStr += casts.get(i);
			}
			
			String result = movieListing.getMovieTitle() + "," + 
							movieListing.getMovieStatus() + ","+ 
							movieListing.getMovieDirector() + "," + 
							movieListing.getMovieDuration() + "," +
							castStr + "," +
							movieListing.getMovieGenre() + "," +
							movieListing.getMovieRating() + "," + 
							movieListing.getMovieSynopsis();
			
			String [] record = result.split(",");
			
			csvWriter.writeNext(record);
			
			//display message
			System.out.println("Added " + movieListing.getMovieTitle() 
								+ " movie was added to the movie listing.");
			csvWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMovieAndShowing(MovieListing movieListing, int index) {
		try{
			File inputFile = new File ("src/storage/MovieListing.csv");
		
			//read existing file
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> records = reader.readAll();
			
			for(int i=0; i<records.size(); i++) {
				String[] array = records.get(i);
				
				
			}
			reader.close();
			
			//write to csv file
			writeToCSV("MovieListing.csv");
			
			csvWriter.writeAll(records);
			
			//display message
			System.out.println(movieListing.getMovieTitle() + " movie has been updated.");
			
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	public void removeMovieAndShowing(MovieListing movieListing, MovieShowing movieShowing) {
		try {
			File showingFile = new File ("src/storage/MovieShowing.csv");
			
			//read existing file
			CSVReader reader = new CSVReader(new FileReader(showingFile));
			List<String[]> records = reader.readAll();
			
			for(int i=0; i<records.size(); i++) {
				String[] array = records.get(i);
				
				if(array[0] == movieShowing.getMovieName()) {
					records.remove(i);
				}
			}
			reader.close();
			
			//write to csv file
			writeToCSV("MovieShowing.csv");
			
			csvWriter.writeAll(records);
			
			//display message
			System.out.println( movieListing.getMovieTitle() + " movie has ended.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CsvException e) {
				e.printStackTrace();
			}
	}

	/**
	 * saveMovieShowing() : stores movie showing details
	 * @param movieShowing
	 */
	public void saveMovieShowing(MovieShowing movieShowing) {
		//add into MovieShowTimes csv
		try {
		
			ArrayList<ShowTime> showtimes = movieShowing.getShowTimes();
			String sTimeStr = "";
			
			//loop casts
			for(int i=0; i<showtimes.size(); i++) {
				//form string of cast names
				sTimeStr += ";";
				sTimeStr += showtimes.get(i).toString();
			}
			
			String result = movieShowing.getMovieName() + "," + 
							movieShowing.getCineplex() + ","+ 
							movieShowing.getCinema() + "," + 
							sTimeStr;
			
			
			String [] record = result.split(",");
			csvWriter.writeNext(record);
			
			//display message
			System.out.println(movieShowing.getMovieName() 
								+ " movie is added into MovieShowing!");
			csvWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveMovieListing(String movieTitle, MovieStatus status, String movieDirector, int duration,
			ArrayList<String> movieCast, MovieGenre genre, MovieRating rating, String synopsis) throws IOException {
		// TODO Auto-generated method stub
		String csv = "src/storage/MovieListing.csv";
	   // CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		//String castString = null;
		StringBuilder movieCasts = new StringBuilder();
		for (int i = 0; i < movieCast.size(); i++) {
			movieCasts.append(movieCast.get(i)+ ";");
		}
		//System.out.println("Casts List: " + castString);
	    String result = movieTitle + "," + status + "," + movieDirector + "," + duration + "," + movieCasts.toString() + "," + genre + "," + rating + "," + synopsis;
	    String [] record = result.split(",");
	    writer.writeNext(record);
	    writer.close();
		return;
	}
	
	public void saveHoliday() {
		
	}
}
