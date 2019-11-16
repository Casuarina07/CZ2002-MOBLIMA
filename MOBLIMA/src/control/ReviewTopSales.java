//REVIEW CONTROLLER 
package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

import model.Cinema;
import model.Cineplex;
import model.MovieListing;
import model.Rating;
import model.Sales;
import model.Transaction;
import view.MovieGoerUI;

/**
 * @author Casuarina D/O Abdul Karim
 * @since 2019-11-13
 */
public class ReviewTopSales {
	private static ReviewTopSales controller = null;
	private static BufferedReader bufferedReader = null;
	DatabaseController databaseCon = DatabaseController.getInstance();
	
	public static ReviewTopSales getInstance() {
		if (controller == null) {
			controller = new ReviewTopSales();
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
	
	//list top 5 sales 
	/**
	 * list the top 5 movies based on sales
	 * @throws IOException
	 */
	public void listTop5Sales() throws IOException {
		ArrayList<Transaction> salesOrder = new ArrayList<Transaction>();
		salesOrder = databaseCon.readTransactionCSV();
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
	public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

	public void listTop5Rating() throws IOException {
		ArrayList<Rating> ratingOrder = new ArrayList<Rating>();
		ratingOrder = databaseCon.readReviewCSV();
		System.out.println("-----Top 5 Movies based on Rating:-----");
		Collections.sort(ratingOrder, Rating.MovieRating);
		int i = 1;
		for(Rating str: ratingOrder){
			System.out.println(i + ". " + str.getMovieTitle() + " - Rating: " + str.getMovieRating());
			i++;
		} 
	}

	public  ArrayList<String> displayReviewableMovies() {
		// TODO Auto-generated method stub
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		transaction = databaseCon.readTransactionCSV();
		//transactions array list contains all the movie titles that has been bought
		ArrayList<String> transactions = new ArrayList<String>();
		for (int i = 0; i < transaction.size(); i++) {
			if(!transactions.contains(transaction.get(i).getMovieTitle()))
			{
				transactions.add(transaction.get(i).getMovieTitle());
			}
		}
		return transactions;
	}
	
	public void reviewChosenMovie(int choice, int inputRate, String inputComment) throws IOException {
		//check whether the movie has already been reviewed
		ArrayList<Rating> reviewed = new ArrayList<Rating>();
		reviewed = databaseCon.readReviewCSV();
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		transaction = databaseCon.readTransactionCSV();
		//transactions array list contains all the movie titles that has been bought
		ArrayList<String> transactions = new ArrayList<String>();
		for (int i = 0; i < transaction.size(); i++) {
			if(!transactions.contains(transaction.get(i).getMovieTitle()))
			{
				transactions.add(transaction.get(i).getMovieTitle());
			}
		}
		
		for (int i = 0; i < reviewed.size(); i++) {
			if(reviewed.get(i).getMovieTitle().equals(transactions.get(choice-1))) {
				reviewCompleted();
			}
			else if(i==reviewed.size()-1) {
				String csv = "src/storage/Review.csv";
				ArrayList<Rating> review = new ArrayList<Rating>();
				review = databaseCon.readReviewCSV();
				String movieTitle = transactions.get(choice-1);
			   // CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
				CSVWriter writer = new CSVWriter(new FileWriter(csv, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			    String inputRateConvert = String.valueOf(inputRate);
			    String result = movieTitle + "," + inputRateConvert + "," + inputComment;
			    String [] record = result.split(",");
			    writer.writeNext(record);
			    writer.close();
				return;
			}
		}
		
	}

	public int reviewCompleted() {
	    int returnint = 1;
		return returnint;
	}

	public   ArrayList<String> displayUserReview(int choice) {
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		transaction = databaseCon.readTransactionCSV();
		//transactions array list contains all the movie titles that has been bought
		ArrayList<String> transactions = new ArrayList<String>();
		for (int i = 0; i < transaction.size(); i++) {
			if(!transactions.contains(transaction.get(i).getMovieTitle()))
			{
				transactions.add(transaction.get(i).getMovieTitle());
			}
		}
		ArrayList<Rating> reviewed = new ArrayList<Rating>();
		reviewed = databaseCon.readReviewCSV();
		ArrayList<String> reviewResult = new ArrayList<String>();
		for (int i = 0; i < reviewed.size(); i++) {
			if(reviewed.get(i).getMovieTitle().equals(transactions.get(choice-1))) {
				reviewResult.add("Movie Reviewed: " + reviewed.get(i).getMovieTitle() + "\nRating: " + reviewed.get(i).getMovieRating() + "\nComments: " + reviewed.get(i).getMovieReview());
				break;
			}
		}
		
		return reviewResult;
	}

}
