
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
    	
    	FirstRatings fr = new FirstRatings();
    	
//    	myRaters = new ArrayList<>();
//    	myMovies = new ArrayList<>();
    	
    	myMovies = fr.loadMovies(moviefile);
    	myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
    	return myMovies.size();
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
    	
    	ArrayList<Rating> ratings = new ArrayList<>();
    	for (Movie m: myMovies) {
    		double averageRating = getAverageByID(m.getID(), minimalRaters);
    		if (averageRating != 0) {
    			Rating r = new Rating(m.getID(), averageRating);
    			ratings.add(r);
    		}
    	}
    	return ratings;
    }
    
    public String getTitle(String id) {
    	for (Movie m: myMovies) {
    		if (m.getID().equals(id)) 
    			return m.getTitle();
    	}
    	return "Movie Id" + id + "id not found";
    }
    
    public String getId(String title) {
    	for (Movie m: myMovies) {
    		if (m.getTitle().equals(title)) {
    			return m.getID();
    		}
    	}
    	
    	return "Movie not found";
    }
}












