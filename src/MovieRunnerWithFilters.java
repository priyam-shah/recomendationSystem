import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
		
	public static void printAverageRatings() {
		
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
		
		System.out.println("read data for " + tr.getRaterSize() 
					+ " raters ");
		
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		System.out.println("read data for " + MovieDatabase.size() + " movies");
		
		int minimalRaters = 35;
		ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
		
		System.out.println("found " + ratings.size() + " movies");
		
		Collections.sort(ratings);
		
		for (Rating r: ratings) {
			System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	public static void printAverageRatingsByYear() {
		
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
		
		System.out.println("read data for " + tr.getRaterSize() + " raters");
		
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    
	    int minimalRaters = 20;
	    int year = 2000;
	    
	    Filter filterCriteria = new YearAfterFilter(year);
	    
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria); 
	    System.out.println("found " + ratings.size() + " movies");
	    
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " +  MovieDatabase.getTitle(r.getItem()));
	    }		
	}
	
	public static void printAverageRatingsByGenre() {
		
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
		
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    
	    int minimalRaters = 20;
	    String genre = "Comedy";
	    
	    Filter filterCriteria = new GenreFilter(genre);
	   
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    
	    System.out.println("found " + ratings.size() + " movies");
	    
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }	
	}
	
	public static void printAverageRatingsByMinutes() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 5;
	    int min = 105;
	    int max = 135;
	    Filter filterCriteria = new MinutesFilter(min, max);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    System.out.println("found " + ratings.size() + " movies");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
	    }		
	}
	
	public static void printAverageRatingsByDirectors() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 4;
	    String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"; 
	    Filter filterCriteria = new DirectorsFilter(directors);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    System.out.println("found " + ratings.size() + " movies");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getDirector(r.getItem()));
	    }	
	}
	
	public static void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 8;
	    int year = 1990;
	    String genre = "Drama";
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new GenreFilter(genre));
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }			
	}
	
	public static void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 3;
	    int min = 90;
	    int max = 180;
	    String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new DirectorsFilter(directors));
	    filterCriteria.addFilter(new MinutesFilter(min, max));
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getDirector(r.getItem()));
	    }			
	}
	
	public static void main(String args[]) {
//		printAverageRatings();
//		printAverageRatingsByYear();
//		printAverageRatingsByGenre();
//		printAverageRatingsByMinutes();
//		printAverageRatingsByDirectors();
//		printAverageRatingsByYearAfterAndGenre();
		printAverageRatingsByDirectorsAndMinutes();
	}
	
}
