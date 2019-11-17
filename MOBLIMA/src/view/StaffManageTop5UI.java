package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import control.DatabaseController;
import control.SearchAndListController;
import control.StaffController;
import model.MovieListing;
import model.Rating;
import model.Transaction;

/**
 * @author Gwyn Bong Xiao Min
 * @author Loh Hui Qi
 * @author Casuarina D/O Abdul Karim
 * @since 2019-11-08
 */
public class StaffManageTop5UI {
	private static StaffManageTop5UI staffMT5 = null;
	private static Scanner sc; 
	private static StaffController staffCon = StaffController.getInstance();
	
	private StaffManageTop5UI() {
		sc = new Scanner(System.in);
	}
	
	public static StaffManageTop5UI getInstance() {
		if(staffMT5 == null) {
			staffMT5 = new StaffManageTop5UI();
		}
		return staffMT5;
	}

	public void displayMenu() throws IOException {
		int choice;
		
		do {
			System.out.println("_____ MANAGE TOP 5 MOVIES MENU ________\n"
							+ "|                                       |\n"
							+ "| 1. List Top 5 Movies by Sales     	   |\n"
							+ "| 2. List Top 5 Movies by Rating    	   |\n"
							+ "| 0. Back to Staff Menu                 |\n"
							+ "|                                       |\n"
							+ " Enter your choice here: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					listTop5Sales();
					break;
				case 2:
					listTop5byRating();
					break;
				case 0:
					return;
					
			}
		} while (choice >= 0 || choice <= 2);
	}

	private void listTop5byRating() {
		ArrayList<Rating> ratingOrder = new ArrayList<Rating>();
		ratingOrder = DatabaseController.readReviewCSV();
		System.out.println("-----Top 5 Movies based on Rating:-----");
		Collections.sort(ratingOrder, Rating.MovieRating);
		int i = 1;
		for(Rating str: ratingOrder){
			System.out.println(i + ". " + str.getMovieTitle() + " - Rating: " + str.getMovieRating());
			i++;
		} 
	}

	public void listTop5Sales() throws IOException {
		ArrayList<Transaction> salesOrder = new ArrayList<Transaction>();
		salesOrder = DatabaseController.readTransactionCSV();
		System.out.println("---Top 5 Movies based on Sales");
		ArrayList<String> top5sales = new ArrayList<>();
		for(int i=0; i<salesOrder.size(); i++) {
			if(top5sales.contains(salesOrder.get(i).getMovieTitle())) {
				top5sales.add(salesOrder.get(i).getMovieTitle()); 
				continue;
			}
			else {
				top5sales.add(salesOrder.get(i).getMovieTitle()); 
				//System.out.println(salesOrder.get(i).getMovieTitle());
			}
		}
		
		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();
		movies = SearchAndListController.readMovieCSV();
		
		//Hashmap 
		HashMap<String, Integer> map 
        = new HashMap<>(); 

		//list for the occurence and the movietitle 
		ArrayList<String> topmovies = new ArrayList<String>();
		for(int i = 0; i<movies.size(); i++) {
			int occurrences = Collections.frequency(top5sales, movies.get(i).getMovieTitle());
			topmovies.add(movies.get(i).getMovieTitle() + occurrences);
			
			map.put(movies.get(i).getMovieTitle(), occurrences); 
  
		}
		final Map<String, Integer> sortedByCount = sortByValue(map);
		//System.out.println(sortedByCount);
		String[] sortedMovies = sortedByCount.keySet().toArray(new String[sortedByCount.size()]);
		for (int i = 0; i < 5; i++) {
			System.out.println((i+1) + ". " + sortedMovies[i]);
		}
	}
	//sorted by count for sales
	/**
	 * Sorting map based on the count 
	 * @param wordCounts
	 * @return
	 */
	public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
