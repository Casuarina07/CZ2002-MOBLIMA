package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.MovieListing;

/**
 * 
 * @author Loh Hui Qi
 *
 */


public class SearchAndListController {

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
				
				
				while ((line = br.readLine()) != null) 
				{
					String[] value = line.split(cvsSplitBy);

					String movieTitle = value[0];
					// movieStatus
					MovieListing.MovieStatus movieStatus = MovieListing.MovieStatus.valueOf(value[1]);
					String movieDirector = value[2];
					int movieDuration = Integer.parseInt(value[3]);
					// casts arrayList
					ArrayList<String> casts = new ArrayList<String>();
					String[] castsList = value[4].split(";");
					for (String cast : castsList) 
					{
						casts.add(cast);
					}
					MovieListing.MovieGenre movieGenre = MovieListing.MovieGenre.valueOf(value[5]);

					MovieListing.MovieRating movieRating = MovieListing.MovieRating.valueOf(value[6]);

					String movieSynopsis = value[7];

					MovieListing movie = new MovieListing(movieTitle, movieStatus, movieDirector,
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
		int c=0; //counter to check if movie exist

		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();

		try {
				br = new BufferedReader(new FileReader(filePath));
				System.out.println("--------Search Movie Result-----------");
												
					while ((line = br.readLine()) != null) 
					{
						String[] value = line.split(cvsSplitBy);
						
						if(value[0].toLowerCase().contains(title.toLowerCase()))
						{
							String movieTitle = value[0];
							// movieStatus
							MovieListing.MovieStatus movieStatus = MovieListing.MovieStatus.valueOf(value[1]);
							String movieDirector = value[2];
							int movieDuration = Integer.parseInt(value[3]);
							// casts arrayList
							ArrayList<String> casts = new ArrayList<String>();
							String[] castsList = value[4].split(";");
							for (String cast : castsList) 
							{
								casts.add(cast);
							}
							MovieListing.MovieGenre movieGenre = MovieListing.MovieGenre.valueOf(value[5]);

							MovieListing.MovieRating movieRating = MovieListing.MovieRating.valueOf(value[6]);

							String movieSynopsis = value[7];

							MovieListing movie = new MovieListing(movieTitle, movieStatus, movieDirector,
									movieDuration, casts, movieGenre, movieRating, movieSynopsis);
							movies.add(movie);	
							c=1; // if movie found
						} 
					}
					if (c==0) //if no movie found
					{
						System.out.println("No existing movie");
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
}
