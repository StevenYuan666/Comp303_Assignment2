import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class WatchList implements Bingeable<Watchable>{
	private String name;
	private LinkedList<Watchable> watchList;
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
		LinkedList<Watchable> list =  new LinkedList<Watchable>();
		this.watchList = list;
	}
	
	@Override
	public String toString() {
		return this.watchList.toString();
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


	public ArrayList<String> allStudios() {
		ArrayList<String> list = new ArrayList<String>();
		for(Watchable m : this.watchList) {
			String s = m.getStudio();
			if(!list.contains(s)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public ArrayList<String> allLanguages() {
		ArrayList<String> list = new ArrayList<String>();
		for(Watchable m : this.watchList) {
			String l = m.getLanguage();
			if(!list.contains(l)) {
				list.add(l);
			}
		}
		return list;
	}
	
	//Override methods in Bingeable interface
	//Do not need copy here, since if the Client change the movie globally, 
	//the movie in the watch list should be changed simultaneously
	@Override
	public void add(Watchable toWatch) {
		for(Watchable m : this.watchList) {
			if(m.ifSame(toWatch)) {
				//raise an error if the movie with same information has already in the watch list
				throw new AssertionError("Error: The movie has already in the list");
			}
		}
		this.watchList.add(toWatch);
	}
	
	@Override
	public void playOne() {
		Watchable m = this.watchList.getFirst();
		//raise an error if the movie to play is not valid
		if(m.getValidity().equals(Status.Valid)) {
			this.watchList.removeFirst();
		}
		else {
			throw new AssertionError("Error: The movie you want to watch cannot be found");
		}
	}
	
	public int validNum() {
		int num = 0;
		for(Watchable m : this.watchList) {
			if(m.getValidity().equals(Status.Valid)) {
				num ++;
			}
		}
		return num;
	}

	@Override
	public Iterator<Watchable> iterator() {
		return new MyIterator(this);
	}
	/*
	 * Use a private class to implement the Iterator interface to avoid the reference leaking,
	 * Right now, if the user use for each loop on a Watchlist object, they can only access
	 * the copy of each watchable object in the watchlist, but not the reference directly
	 */
	private class MyIterator implements Iterator<Watchable>{
		private LinkedList<Watchable> allWatchable = new LinkedList<Watchable>();
		public MyIterator(WatchList list) {
			for(Watchable e : list.watchList) {
				Watchable copy = e.getCopy();
				allWatchable.add(copy);
			}
		}
		@Override
		public boolean hasNext() {
			return this.allWatchable.size() > 0;
		}
		@Override
		public Watchable next() {
			return this.allWatchable.removeFirst();
		}
	}
}
	