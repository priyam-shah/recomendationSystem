import java.util.*;

public class ThirdRatings {
    
	//private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
    	this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
    	
    	FirstRatings fr = new FirstRatings();
    	
//    	myRaters = new ArrayList<>();
//    	myMovies = new ArrayList<>();
    	
    	// myMovies = fr.loadMovies(moviefile);
    	myRaters = fr.loadRaters(ratingsfile);
    }
    
    
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
    	double avg = 0;
    	double sum = 0;
    	int countRaters = 0;
    	
    	for (Rater r: myRaters) {
    		if (r.hasRating(id)) {
    			countRaters++;
    			sum += r.getRating(id);
    		}
    	}
    	
    	if (countRaters >= minimalRaters) {
    		avg = sum / countRaters;
    	}
    	return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	
    	ArrayList<Rating> ratings = new ArrayList<>();
    	
    	for (String id : movies) {
    		
    		double averageRating = getAverageByID(id, minimalRaters);
    		
    		if (averageRating != 0) {
    			Rating r = new Rating(id, averageRating);
    			ratings.add(r);
    		}
    	}
    	return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
    	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	
    	ArrayList<Rating> ratings = new ArrayList<Rating>();
    	
    	for (String id: movies) {
    		
    		double averageRating = getAverageByID(id, minimalRaters);
    		
    		if (averageRating!=0) {
    			Rating r = new Rating(id, averageRating);
    			ratings.add(r);
    		}
    	}
    	
    	return ratings;
    }
    
}












