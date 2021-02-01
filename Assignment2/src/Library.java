

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	
	// Optional
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aMovies.add(pMovie);
	}
	
	// Optional
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 */
	public void addWatchList(WatchList pList) {
		aWatchLists.add(pList);
		for (Movie movie : pList.getMovies()) {
			addMovie(movie);
		}
	}
}
