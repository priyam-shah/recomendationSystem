import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	
	public  void printAverageRatings() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		
		System.out.println("movie size " + sr.getMovieSize() 
					+ " and rater size " + sr.getRaterSize());
		
		int minimalRaters = 12;
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		Collections.sort(ratings);
		
		for (Rating r: ratings) {
			System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
		}
	}
	
	public  void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		String movieTitle = "Vacation";
		String movieId  = sr.getId(movieTitle);
		
		int minimalRaters = 0;
	
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		
		for(Rating r: ratings) {
//			System.out.println("executing");
			if(r.getItem().equals(movieId)) {
				System.out.println("avg rating for " 
						+ movieTitle + " is " + r.getValue());
			}
		}
		
	}
	
	public static void main(String args[]) {
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
	}
}

// print the list of movies rating --> title (lowest to highest) 


