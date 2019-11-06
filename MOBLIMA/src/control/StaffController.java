package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import model.MovieListing;
import model.Rating;
import model.MovieListing.MovieGenre;
import model.MovieListing.MovieRating;
import model.MovieListing.MovieStatus;
import view.StaffUI;

public class StaffController implements Serializable{
	private static StaffController staffCon = null;
	private Scanner sc;
	private MovieListing movie;
	
	private StaffController() {
		sc = new Scanner(System.in);
	}

	public static StaffController getInstance() {
		if(staffCon == null) {
			staffCon = new StaffController();
		}
		return staffCon;
	}
	
	public void loadMovieListing() {
		
	}
	
	public void createMovieListing(MovieListing movieListing) {
		

	}

	public void updateMovieListing() {
		
	}

	public void removeMovieListing() {
	}

	public void createMovieShowing() {
		
	}
	public void updateMovieShowing() {
			
	}
	public void removeMovieShowing() {
		
	}
	
	public boolean validateStaffLogin(String username, final String pwd) {
		
	}
	
	public void staffLogout() {
		
	}
	public void listTop5MovieSales() {

	}
	
	public void generateSalesReport() {
		
	}

	public void configureSettings() {

	}
	
}
