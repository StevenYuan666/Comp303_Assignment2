import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Library {
	/*
	 * A name list to store the name has already used
	 * since two library with same name don't make sense, so the name should be unique
	 */
	static private ArrayList<String> nameList = new ArrayList<String>();;
	private String name;
	//A library can store movies and watch lists
	//Using HashMap and using Path and Name as key to avoid the duplicates in Library
	private HashSet<Movie> movies;
	private HashSet<WatchList> watchLists;
	private HashSet<Episode> episodes;
	private HashSet<TVShow> tvShows;
	
	public Library(String inputName) {
		//check if the name has already been used
		for(String n : nameList) {
			if(inputName.equals(n)) {
				//raise an error if the name has already existed
				throw new AssertionError("The name has already existed. Please change the name");
			}
		}
		this.name = inputName;
		nameList.add(inputName);
		this.movies = new HashSet<Movie>();
		this.watchLists = new HashSet<WatchList>();
		this.episodes = new HashSet<Episode>();
		this.tvShows = new HashSet<TVShow>();
	}
	
	//Several add and remove methods for each type of objects
	public void addMovie(Movie m) {
		this.movies.add(m);
	}
	
	public void addList(WatchList w) {
		this.watchLists.add(w);
	}
	
	public void addEpisode(Episode e) {
		this.episodes.add(e);
	}
	
	public void addTVShow(TVShow t) {
		this.tvShows.add(t);
	}
	
	public void removeMovie(Movie m) {
		this.movies.remove(m);
	}
	
	public void removeWatchList(WatchList w) {
		this.watchLists.remove(w);
	}

	public void removeEpisode(Episode e) {
		this.episodes.remove(e);
	}
	
	public void removeTVShow(TVShow t) {
		this.tvShows.remove(t);
	}
	
	//Setter for name, need to check the duplicates as well
	public void setName(String newName) {
		for(String n : nameList) {
			if(n.equals(name)) {
				//raise an error if the name has already existed
				throw new AssertionError("The name has already existed. Please change the name");
			}
		}
		nameList.remove(this.name);
		this.name = newName;
		nameList.add(newName);
	}

	public String getName() {
		return name;
	}
	
	//Enable the clients to generate a Watch list by the algorithm they specified
	public WatchList generateList(String newListName, Generator g) {
		WatchList generated = new WatchList(newListName);
		LinkedList<Watchable> all = new LinkedList<Watchable>();
		all.addAll(this.episodes);
		all.addAll(this.movies);
		all.addAll(this.tvShows);
		LinkedList<Watchable> filtered = g.generate(all);
		for(Watchable w : filtered) {
			/*
			 * Add the copy of each watchable object to the new 
			 * Watchlist to avoid the reference leaking
			 */
			generated.add(w.getCopy());
		}
		return generated;
	}
}
