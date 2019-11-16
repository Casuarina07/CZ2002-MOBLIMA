package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import model.Cinema;
import model.MovieListing;
import model.MovieListing.MovieStatus;
import model.MovieShowing;
import model.ShowTime;
import model.Staff;


/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-01
 */
public class StaffController {
	private Scanner sc;
	private static StaffController staffCon = null;
	
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
	
}
