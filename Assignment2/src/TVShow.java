import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

//TVShow should be sortable as well, since a same TV show may have different seasons
public class TVShow implements Watchable, Bingeable<Episode>, Sortable<TVShow>{
	private Status validity;
	private String title;
	//Typically, each episodes for a TV show will have same language and studio
	private String studio;
	private String language;
	private LinkedList<Episode> watchList;
	private Optional<TVShow> next;
	private Optional<TVShow> previous;
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
		//If all episodes in a TV show are invalid, then the TV show is invalid as well
		this.validity = Status.Invalid;
		for(Episode e : this.watchList) {
			if(e.getValidity().equals(Status.Valid)) {
				this.validity = Status.Valid;
				break;
			}
		}
		//Set the previous and next as null, but use Optional here to avoid the null Pointer
		this.previous = Optional.empty();
		this.next = Optional.empty();
	}
	
	//A constructor for deeply copy a TV show object
	public TVShow(TVShow t) {
		this.title  = t.title;
		this.watchList = new LinkedList<Episode>();
		for(Episode toAdd : t.watchList) {
			Episode copy = new Episode(toAdd);
			this.watchList.add(copy);
		}
		this.language = t.language;
		this.studio = t.studio;
		this.validity = t.validity;
		this.previous = t.previous;
		this.next = t.next;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
	//Getter and Setter for the name
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

	//Override the methods in Bingeable interface
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
	public void playOne() {
		Episode e = this.watchList.getFirst();
		//raise an error if the movie to play is not valid
		if(e.getValidity().equals(Status.Valid)) {
			this.watchList.removeFirst();
		}
		else {
			throw new AssertionError("Error: The movie you want to watch cannot be found");
		}
	}
	
	public int validNum() {
		int num = 0;
		for(Episode e : this.watchList) {
			if(e.getValidity().equals(Status.Valid)) {
				num ++;
			}
		}
		return num;
	}
	//Enable the users to access all elements by iterator
	@Override
	public Iterator<Episode> iterator() {
		return new MyIterator(this);
	}
	/*
	 * Use a private class to implement the Iterator interface to avoid the reference leaking,
	 * Right now, if the user use for each loop on a TVShow object, they can only access
	 * the copy of each episode in the TV show, but not the reference directly
	 */
	private class MyIterator implements Iterator<Episode>{
		private LinkedList<Episode> allEpisodes = new LinkedList<Episode>();
		public MyIterator(TVShow show) {
			for(Episode e : show.watchList) {
				Episode copy = new Episode(e);
				allEpisodes.add(copy);
			}
		}
		@Override
		public boolean hasNext() {
			return this.allEpisodes.size() > 0;
		}
		@Override
		public Episode next() {
			return this.allEpisodes.remove();
		}
	}
	
	
	
	//Override the method in Watchable interface
	@Override
	public String getTitle() {
		return this.title;
	}
	@Override
	public String getStudio() {
		return this.studio;
	}
	@Override
	public String getLanguage() {
		return this.language;
	}
	@Override
	public Status getValidity() {
		this.updateStatus();
		return this.validity;
	}
	@Override
	public void updateStatus() {
		for(Episode e : this.watchList) {
			if(e.getValidity().equals(Status.Valid)) {
				this.validity = Status.Valid;
				break;
			}
		}
	}
	@Override
	//Two TV show are considered as same if they include same episodes
	public boolean ifSame(Watchable m) {
		if(!(m instanceof TVShow)) {
			return false;
		}
		TVShow toCompare = (TVShow) m;
		if(toCompare.watchList.size() != this.watchList.size()) {
			return false;
		}
		for(Episode e : this.watchList) {
			boolean exist = false;
			for(Episode ee : toCompare.watchList) {
				if(e.ifSame(ee)) {
					exist = true;
					break;
				}
			}
			if(!exist) {
				return false;
			}
		}
		return true;
	}
	@Override
	public Watchable getCopy() {
		return new TVShow(this);
	}

	//Override the methods in Sortable interface
	@Override
	public TVShow getNext() {
		//Throw out the NoSuchElement Error automatically, if the next is empty
		return this.next.get();
	}

	@Override
	public void setNext(TVShow input) {
		if(input != null) {
			this.next = Optional.of(input);
		}
		else {
			this.next = Optional.empty();
		}
	}

	@Override
	public TVShow getPrevious() {
		//Throw out the NoSuchElement Error automatically, if the previous is empty
		return this.previous.get();
	}

	@Override
	public void setPrevious(TVShow input) {
		if(input != null) {
			this.previous = Optional.of(input);
		}
		else {
			this.next = Optional.empty();
		}
	}
}
