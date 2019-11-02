package control;

import boundary.StaffUI;

/*
 * This class represents staff functions
 * */
public class StaffController {
	private static StaffController staffCon = null;
	
	public static StaffController getInstance() {
		if(staffCon == null) {
			staffCon = new StaffController();
		}
		return staffCon;
	}
	
	public void createMovieListing() {

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
	
	public void staffLogin() {
		
	}
	
	public void staffLogout() {
		
	}
	public void listTop5MovieSales() {

	}

	public void configureSettings() {

	}
}
