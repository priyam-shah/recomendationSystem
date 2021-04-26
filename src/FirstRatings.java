import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class FirstRatings {
	
//	process every record from the CSV file
//	and return an ArrayList of type Movie with all of the movie datafrom the file.
	public  ArrayList<Movie> loadMovies(String filename){
		
		ArrayList<Movie> list = new ArrayList<Movie>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		
		for (CSVRecord rec: parser) {
			Movie m = new Movie(rec.get("id"), rec.get("title"),
					rec.get("year"), 
					rec.get("genre"), rec.get("director"), rec.get("country"), 
					rec.get("poster"), Integer.parseInt(rec.get("minutes")));
			list.add(m);
		}
//		
		return list;
	}
	
	public  void testLoadMovies() {
//		String filename = "data/ratedmovies_short.csv";
		String filename = "data/ratedmoviesfull.csv";
		
		ArrayList<Movie> movieList = loadMovies(filename);
		System.out.println(filename.substring(5) +" has " + movieList.size() + " movies");
		

		
		int genreCount = 0, minuteCount = 0;
		String genre = "Comedy";
		int minute = 150;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (Movie m: movieList) {
			if (m.getGenres().contains(genre))
				genreCount ++;
			if (m.getMinutes() > minute)
				minuteCount ++;
			
			// if a director is present --> add 1 to count
			if (map.containsKey(m.getDirector()))
					map.put(m.getDirector(), map.get(m.getDirector()) + 1);
			// if a director is not present --> director count to 1
			else 
				map.put(m.getDirector(), 1);
		}
		
		System.out.println(genreCount + " " + minuteCount);
		
		// stores maximum elements max(collection) returns max ele
		int maxValue = Collections.max(map.values());
		
		//entry set simply returns all element of the HashMap
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if (pair.getValue().equals(maxValue))
				System.out.println(pair.getKey() + " directed the maximum "
						+ "number of movies, which is " + maxValue);
		}
	}
	
	public  ArrayList<Rater> loadRaters(String filename){
		
		ArrayList<Rater> list = new ArrayList<>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		
		
		for (CSVRecord rec : parser) {
			
			String rater_id = rec.get("rater_id");
			String movie_id = rec.get("movie_id");
			double rating = Double.parseDouble(rec.get("rating"));
		
			int count = 0;
			for (Rater r: list) {
				if (r.getID().contains(rater_id)) {
					
					r.addRating(movie_id, rating);
					count ++;
					break;
				}
			}
			
			if (count == 0) {
				Rater r = new Rater(rater_id);
				r.addRating(movie_id, rating);
				list.add(r);
			}
		}
		return list;
	}
	
	public void testLoadRaters() {
//		String filename = "data/ratings_short.csv";
		String filename = "data/ratings.csv";
		
		ArrayList<Rater> raterList = loadRaters(filename);
//		System.out.println(filename.substring(5) + " has " + raterList.size() + " raters");
		
		String raterID = "193";
		String movieID = "1798709";
		
		HashMap<String, Integer> map = new HashMap<>();
		
		int raterCount = 0;
		
		Set<String> set = new HashSet<>();
		
		for (Rater r: raterList) {
//			System.out.println("Rater ID " + r.getID() + " has " 
//					+ r.numRatings() + " ratings");
			ArrayList<String> ratingList = r.getItemsRated();
			
			for (String s: ratingList) {
//				System.out.println("Movie ID " + s + " is rated as " 
//						+ r.getRating(s));
				if (!set.contains(s)) {
					set.add(s);
				}
			}
			
			if (r.getID().equals(raterID)) {
//				System.out.println("\nRater ID " + raterID + " has " 
//						+ r.numRatings() + " ratings");
				
			}
			
			map.put(r.getID(), r.numRatings());
			
			if (r.hasRating(movieID)) {
				raterCount ++;
			}
			
			int maxValue = Collections.max(map.values());
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				if(pair.getValue().equals(maxValue)) {
					
//					System.out.println("Rater ID " + pair.getKey() + " has the "
//							+ "maximum number of ratings, which is " + maxValue);
				}
			}
			
			System.out.println("Movie ID " + movieID + " was rated by " 
					+ raterCount + " raters");
			System.out.println(set.size() + " movies have been rated "
					+ "by all " +  raterList.size() + " raters");
			
		
		}
				
	}

	
	public static void main(String[] args) {
		
		FirstRatings f = new FirstRatings();
//		f.testLoadMovies();
		f.testLoadRaters();
		
	}
}
