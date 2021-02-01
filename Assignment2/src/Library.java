import java.util.ArrayList;

public class Library {
	/*
	 * A name list to store the name has already used
	 * since two library with same name don't make sense, so the name should be unique
	 */
	static private ArrayList<String> nameList = new ArrayList<String>();;
	private String name;
	//A library can store movies and watch lists
	private ArrayList<Movie> movies;
	private ArrayList<WatchList> watchLists;
	
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
		this.movies = new ArrayList<Movie>();
		this.watchLists = new ArrayList<WatchList>();
	}
	
	/*
	 * add the reference directly, since if the information of the movie changed,
	 * the information in the library should be changed as well.
	 */
	public void addMovie(Movie m) {
		//avoid the same Movie(same information but not reference) added into a library
		for(Movie movie : this.movies) {
			if(movie.ifSame(m)) {
				//raise an error if the movie with same info has already existed
				throw new AssertionError("The movie has already in the library");
			}
		}
		this.movies.add(m);
	}
	
	/*
	 * Since the name of watch list is unique, so there are only lists with same movies inside,
	 * but not the same name, so we don't have to check the duplicates
	 */
	public void addList(WatchList w) {
		this.watchLists.add(w);
	}
	
	public void removeMovie(Movie m) {
		this.movies.remove(m);
	}
	
	public void removeWatchList(WatchList w) {
		this.watchLists.remove(w);
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
