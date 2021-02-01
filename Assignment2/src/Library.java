import java.util.ArrayList;
import java.util.HashMap;

public class Library {
	/*
	 * A name list to store the name has already used
	 * since two library with same name don't make sense, so the name should be unique
	 */
	static private ArrayList<String> nameList = new ArrayList<String>();;
	private String name;
	//A library can store movies and watch lists
	//Using HashMap and using Path and Name as key to avoid the duplicates in Library
	private HashMap<String, Movie> movies;
	private HashMap<String, WatchList> watchLists;
	private HashMap<String, Episode> episodes;
	private HashMap<String, TVShow> tvShows;
	
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
		this.movies = new HashMap<String, Movie>();
		this.watchLists = new HashMap<String, WatchList>();
		this.episodes = new HashMap<String, Episode>();
		this.tvShows = new HashMap<String, TVShow>();
	}
	
	public void addMovie(Movie m) {
		this.movies.put(m.getPath(), m);
	}
	
	public void addList(WatchList w) {
		this.watchLists.put(w.getName(), w);
	}
	
	public void addEpisode(Episode e) {
		this.episodes.put(e.getPath(), e);
	}
	
	public void addTVShow(TVShow t) {
		this.tvShows.put(t.getTitle(), t);
	}
	
	public void removeMovie(Movie m) {
		this.movies.remove(m.getPath());
	}
	
	public void removeWatchList(WatchList w) {
		this.watchLists.remove(w.getName());
	}

	public void removeEpisode(Episode e) {
		this.episodes.remove(e.getPath());
	}
	
	public void removeTVShow(TVShow t) {
		this.tvShows.remove(t.getTitle());
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
}
