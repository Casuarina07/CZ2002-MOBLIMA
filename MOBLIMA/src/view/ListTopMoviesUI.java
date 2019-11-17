package view;

import java.io.IOException;
import java.util.Scanner;

import control.ReviewTopSales;

/**
 * List top movies is a view class to display the UI
 * @author Casuarina D/O Abdul Karim
 *
 */
public class ListTopMoviesUI {

	private static ListTopMoviesUI listTopMoviesUI = null;
	private Scanner sc;
		
		private ListTopMoviesUI() {
			sc = new Scanner(System.in);
		}

		public static ListTopMoviesUI getInstance() {
			if (listTopMoviesUI == null) {
				listTopMoviesUI = new ListTopMoviesUI();
			}
			return listTopMoviesUI;
		}
	
	static ReviewTopSales reviewCon = ReviewTopSales.getInstance();
		/**
		 * UI for listing top 5 movies by sales rating and sales
		 * @throws IOException
		 */
		public void listTop5Movies() throws IOException {
			System.out.println("---- Please select a choice to view Top 5 Movies by: ----:"); 
			System.out.println("1. Rating"); 
			System.out.println("2. Sales \n"); 
			
			 int choice = sc.nextInt();
			 
			 if(choice == 1) {
				 reviewCon.listTop5Rating();
			 }
			 else if(choice == 2) {
				 reviewCon.listTop5Sales();
			 }
			 else {
				  System.out.println("You have entered an invalid number");
	                System.out.println("Please re-enter your choice: ");
	                choice = sc.nextInt();
			 }
			 
		}
}
