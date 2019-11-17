package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import control.ReviewTopSales;

/**
 * UI for Reviewing movie watched
 * @author Casuarina D/O Abdul Karim
 *
 */
public class ReviewUI {
	 ReviewTopSales reviewCon = ReviewTopSales.getInstance();
	private static ReviewUI reviewUI = null;
	private Scanner sc;
		
		private ReviewUI() {
			sc = new Scanner(System.in);
		}

		public static ReviewUI getInstance() {
			if (reviewUI == null) {
				reviewUI = new ReviewUI();
			}
			return reviewUI;
		}
	
	/**
	 * UI display for reviewing movies
	 * @throws IOException
	 */
	public void reviewMovie() throws IOException{
		System.out.println("-----Reviewable Movies----");
		System.out.println("Which movies would you like to review? Select your choice");
		ArrayList<String> reviewablemovies = new ArrayList<String>();
		reviewablemovies = reviewCon.displayReviewableMovies();
		if(reviewablemovies.size() == 0) {
			System.out.println("You have not watched any movies");
		}
		for (int i = 0; i < reviewablemovies.size(); i++) {
			System.out.println((i + 1) + ". " + reviewablemovies.get(i));
		}
		int choice = sc.nextInt();
		if(choice > reviewablemovies.size() || choice < 1) {
			System.out.println("Invalid input. Please input a valid choice");
			choice = sc.nextInt();
		}
		//int results = reviewCon.reviewChosenMovie(choice, 1, "");
		int result = reviewCon.reviewCompleted();
		if(result == 1)
		{
			ArrayList<String> reviewResults = new ArrayList<String>();
			reviewResults = reviewCon.displayUserReview(choice);
			if(reviewResults.isEmpty()) {
				System.out.println("Rate this movie out of 5");
				sc.nextLine();
				int inputRate = sc.nextInt();
				if(inputRate > 5 || inputRate < 0) {
					System.out.println("Invalid input. Please input a number from 0-5");
				}
				System.out.println("Any comments on the movie?");
				sc.nextLine();
				String inputComment = sc.nextLine();
				reviewCon.reviewChosenMovie(choice, inputRate, inputComment);
				System.out.println("Thank you for your review!");
			}
			else {
				System.out.println("You have already reviewed this movie. These were your reviews");
				System.out.println(reviewResults);
			}
			return;
		}
		
	}
}
