package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.MovieListing;

/**
 * 
 * @author Loh Hui Qi
 *
 */


public class SeachAndListMovieCSV {

	//readMovieListingCSV
	public static ArrayList<MovieListing> readMovieCSV() 
	{
		String filePath = "src/storage/MovieListing.csv";

		BufferedReader br = null;
		String line = ""; 
		String cvsSplitBy = ",";

		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();

		try {
				br = new BufferedReader(new FileReader(filePath));
				System.out.println("--------All Movies-----------");
				
				while ((line = br.readLine()) != null) 
				{
					String[] value = line.split(cvsSplitBy);

					int movieID = Integer.parseInt(value[0]);
					String movieTitle = value[1];
					// movieStatus
					MovieListing.MovieStatus movieStatus = MovieListing.MovieStatus.valueOf(value[2]);
					String movieDirector = value[3];
					int movieDuration = Integer.parseInt(value[4]);
					// casts arrayList
					ArrayList<String> casts = new ArrayList<String>();
					String[] castsList = value[4].split(";");
					for (String cast : castsList) 
					{
						casts.add(cast);
					}
					MovieListing.MovieGenre movieGenre = MovieListing.MovieGenre.valueOf(value[6]);

					MovieListing.MovieRating movieRating = MovieListing.MovieRating.valueOf(value[7]);

					String movieSynopsis = value[8];

					MovieListing movie = new MovieListing(movieID, movieTitle, movieStatus, movieDirector,
							movieDuration, casts, movieGenre, movieRating, movieSynopsis);
					movies.add(movie);
				}
		
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (br != null) 
				{
					try 
					{
						br.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
				return movies;
	}
	
	/**
	 * 
	 * @param title for user input
	 * @return 
	 */
	//Search movie by title
	public static ArrayList<MovieListing> searchMovieCSV(String title) 
	{
		String filePath = "src/storage/MovieListing.csv";

		BufferedReader br = null;
		String line = ""; 
		String cvsSplitBy = ",";

		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();

		try {
				br = new BufferedReader(new FileReader(filePath));
				System.out.println("--------Search Movie Result-----------");
												
					while ((line = br.readLine()) != null) 
					{
						String[] value = line.split(cvsSplitBy);
						
						if(value[1].toLowerCase().contains(title.toLowerCase()))
						{
							int movieID = Integer.parseInt(value[0]);
							String movieTitle = value[1];
							// movieStatus
							MovieListing.MovieStatus movieStatus = MovieListing.MovieStatus.valueOf(value[2]);
							String movieDirector = value[3];
							int movieDuration = Integer.parseInt(value[4]);
							// casts arrayList
							ArrayList<String> casts = new ArrayList<String>();
							String[] castsList = value[4].split(";");
							for (String cast : castsList) 
							{
								casts.add(cast);
							}
							MovieListing.MovieGenre movieGenre = MovieListing.MovieGenre.valueOf(value[6]);

							MovieListing.MovieRating movieRating = MovieListing.MovieRating.valueOf(value[7]);

							String movieSynopsis = value[8];

							MovieListing movie = new MovieListing(movieID, movieTitle, movieStatus, movieDirector,
									movieDuration, casts, movieGenre, movieRating, movieSynopsis);
							movies.add(movie);							
						} 
					}
		
					//System.out.println("No existing movie");				
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (br != null) 
				{
					try 
					{
						br.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
				return movies;
	}
}
