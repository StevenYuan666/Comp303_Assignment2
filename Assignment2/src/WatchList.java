import java.util.ArrayList;
import java.util.LinkedList;

public class WatchList{
	private String name;
	private LinkedList<Movie> watchList;
	//to store the name has used, to avoid the duplicates
	static private ArrayList<String> nameList = new ArrayList<String>();
	
	public WatchList(String inputName) {
		//The watch list is identified by name, so the name cannot be same
		for(String name : nameList) {
			if(name.equals(inputName)) {
				//raise an error if the name has already existed
				throw new AssertionError("Error: The name has existed already, Please change another name");
			}
		}
		this.name  = inputName;
		nameList.add(inputName);
		LinkedList<Movie> list =  new LinkedList<Movie>();
		this.watchList = list;
	}
	
	//Getter and Setter for the name
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		for(String name : nameList) {
			if(name.equals(newName)) {
				//raise an error if the name has already existed
				throw new AssertionError("Error: The name has existed already, Please change another name");
			}
		}
		nameList.remove(this.name);
		this.name = newName;
		nameList.add(newName);
	}

	//Do not need copy here, since if the Client change the movie globally, 
	//the movie in the watch list should be changed simultaneously
	public void add(Movie toWatch) {
		for(Movie m : this.watchList) {
			if(m.ifSame(toWatch)) {
				//raise an error if the movie with same information has already in the watch list
				throw new AssertionError("Error: The movie has already in the list");
			}
		}
		this.watchList.add(toWatch);
	}
	
	public void watchOne() {
		Movie m = this.watchList.getFirst();
		//raise an error if the movie to play is not valid
		if(m.getValidity().equals(Status.Valid)) {
			this.watchList.removeFirst();
		}
		else {
			throw new AssertionError("Error: The movie you want to watch cannot be found");
		}
	}
	
	/*
	 * Make a copy, so the client only able to access the information, but not to the reference
	 * The client will not be able to change the info of movie by a watch list
	 */
	public ArrayList<Movie> accessAll(){
		ArrayList<Movie> all = new ArrayList<Movie>();
		for(Movie m : this.watchList) {
			Movie copy = new Movie(m);
			all.add(copy);
		}
		return all;
	}
	
	public int validMovies() {
		int num = 0;
		for(Movie m : this.watchList) {
			if(m.getValidity().equals(Status.Valid)) {
				num ++;
			}
		}
		return num;
	}
	
	public ArrayList<String> allStudios() {
		ArrayList<String> list = new ArrayList<String>();
		for(Movie m : this.watchList) {
			String s = m.getStudio();
			if(!list.contains(s)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public ArrayList<String> allLanguages() {
		ArrayList<String> list = new ArrayList<String>();
		for(Movie m : this.watchList) {
			String l = m.getLanguage();
			if(!list.contains(l)) {
				list.add(l);
			}
		}
		return list;
	}
}
	