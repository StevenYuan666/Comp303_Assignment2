import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


//Almost same as WatchList
public class TVShow implements Watchable, Bingeable<Episode>{
	private String title;
	//Typically, each episodes for a TV show will have same language and studio
	private String studio;
	private String language;
	private LinkedList<Episode> watchList;
	//to store the name has used, to avoid the duplicates
	static private ArrayList<String> nameList = new ArrayList<String>();
	
	public TVShow(String inputName) {
		//The watch list is identified by name, so the name cannot be same
		for(String name : nameList) {
			if(name.equals(inputName)) {
				//raise an error if the name has already existed
				throw new AssertionError("Error: The name has existed already, Please change another name");
			}
		}
		this.title  = inputName;
		nameList.add(inputName);
		this.watchList = new LinkedList<Episode>();
	}
	
	//Getter and Setter for the name
	@Override
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newName) {
		for(String name : nameList) {
			if(name.equals(newName)) {
				//raise an error if the name has already existed
				throw new AssertionError("Error: The name has existed already, Please change another name");
			}
		}
		nameList.remove(this.title);
		this.title = newName;
		nameList.add(newName);
	}

	//Do not need copy here, since if the Client change the movie globally, 
	//the movie in the watch list should be changed simultaneously
	@Override
	public void add(Episode toWatch) {
		for(Episode e : this.watchList) {
			if(e.ifSame(toWatch)) {
				//raise an error if the movie with same information has already in the watch list
				throw new AssertionError("Error: The movie has already in the list");
			}
		}
		this.watchList.add(toWatch);
		//Initialize the studio and language if these have not been initialized yet
		if(this.studio == null || this.language == null) {
			this.studio = toWatch.getStudio();
			this.language = toWatch.getLanguage();
		}
	}
	@Override
	public void watchOne() {
		Episode e = this.watchList.getFirst();
		//raise an error if the movie to play is not valid
		if(e.getValidity().equals(Status.Valid)) {
			this.watchList.removeFirst();
		}
		else {
			throw new AssertionError("Error: The movie you want to watch cannot be found");
		}
	}
	@Override
	public String getStudio() {
		return this.studio;
	}
	@Override
	public String getLanguage() {
		return this.language;
	}
	
	/*
	 * Make a copy, so the client only able to access the information, but not to the reference
	 * The client will not be able to change the info of movie by a watch list
	 */
	public ArrayList<Episode> accessAll(){
		ArrayList<Episode> all = new ArrayList<Episode>();
		for(Episode e : this.watchList) {
			Episode copy = new Episode(e);
			all.add(copy);
		}
		return all;
	}
	
	public int validEpisodes() {
		int num = 0;
		for(Episode e : this.watchList) {
			if(e.getValidity().equals(Status.Valid)) {
				num ++;
			}
		}
		return num;
	}

	@Override
	public Iterator<Episode> iterator() {
		return this.watchList.iterator();
	}
}
