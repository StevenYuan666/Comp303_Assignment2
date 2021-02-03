import java.io.File;
import java.util.HashMap;
import java.util.Optional;

public class Movie implements Watchable, Sortable<Movie>{
	
	final private File path;
	final private Formats format;
	private Status status;
	
	final private String title;
	final private String language;
	final private String studio;
	private Optional<Movie> previous;
	private Optional<Movie> next;
	/*
	 * Use HashMap to store the key-value pairs
	 * Choose the type String, since String is general enough to store any information
	 * like name, length, or anything related to the movie
	 */
	private HashMap<String, String> custom;
	
	public Movie(File inputPath, String inputTitle, String inputLanguage, String inputStudio) {
		//check if the input file with acceptable formats
		String inputFormat = inputPath.getPath().substring(inputPath.getPath().lastIndexOf(".") + 1);
		switch (inputFormat.toUpperCase()) {
			case "MP4":
				this.format = Formats.MP4;
				break;
			case "MOV":
				this.format = Formats.MOV;
				break;
			case "WMV":
				this.format = Formats.WMV;
				break;
			case "AVI":
				this.format = Formats.AVI;
				break;
			case "FLV":
				this.format = Formats.FLV;
				break;
			case "MKV":
				this.format = Formats.MKV;
				break;
			default:
				//Raise an error if the format is not acceptable
				throw new AssertionError("Error: Your input path is not matched with any acceptable path");
		}
		this.path = inputPath;
		//check if the file exists or not
		if(inputPath.exists()) {
			this.status = Status.Valid;
		}
		else {
			this.status = Status.Invalid;
		}
		//Initialize the info
		this.title = inputTitle;
		this.language = inputLanguage;
		this.studio = inputStudio;
		this.custom = new HashMap<String, String>();
		this.previous = Optional.empty();
		this.next = Optional.empty();
	}
	
	//to Deeply Copy a movie object
	public Movie(Movie m) {
		this.path = m.path;
		this.format = m.format;
		this.status = m.status;
		this.title = m.title;
		this.language = m.language;
		this.studio = m.studio;
		this.custom = new HashMap<String, String>(m.custom);
		this.next = m.next;
		this.previous = m.previous;
	}

	//Getters for the fields
	public String getPath() {
		return this.path.getPath();
	}
	public Formats getFormat() {
		return this.format;
	}
	//Methods for modify the custom information
	public String getInfo(String key) {
		return this.custom.get(key);
	}
	public void addPair(String key, String value) {
		this.custom.put(key, value);
	}
	public void removePair(String key) {
		this.custom.remove(key);
	}
	public void modifyPair(String key, String changed) {
		this.custom.replace(key, changed);
	}
	
	//Easier for client to print out
	@Override
	public String toString() {
		return this.title;
	}
	
	//Override methods of Watchable Interface
	@Override
	//Update the status of the movie, to check if the file exists or not
	public void updateStatus() {
		if(this.path.exists()) {
			this.status = Status.Valid;
		}
		else {
			this.status = Status.Invalid;
		}
	}
	@Override
	/*
	 * Check if the two movies have same file, even though they are two object
	 * Used in WatchList class and Library class
	 * I assume that two Movies are same if they are refer to the same file
	 */
	public boolean ifSame(Watchable m) {
		if(!(m instanceof Movie)) {
			return false;
		}
		Movie toCompare = (Movie) m;
		return this.path.equals(toCompare.path);
	}
	@Override
	public Status getValidity() {
		this.updateStatus();
		return this.status;
	}
	//Getters for the required information
	@Override
	public String getTitle() {
		return this.title;
	}
	@Override
	public String getLanguage() {
		return this.language;
	}
	@Override
	public String getStudio() {
		return this.studio;
	}
	@Override
	public Watchable getCopy() {
		return new Movie(this);
	}

	//Override the methods of Sortable interface
	@Override
	public Movie getNext() {
		//Throw out the NoSuchElement Error automatically, if the next is empty
		return this.next.get();
	}
	@Override
	public void setNext(Movie input) {
		if(input != null) {
			this.next = Optional.of(input);
			input.setPrevious(this);
		}
		else {
			this.next = Optional.empty();
		}
	}
	@Override
	public Movie getPrevious() {
		//Throw out the NoSuchElement Error automatically, if the previous is empty
		return this.previous.get();
	}
	@Override
	public void setPrevious(Movie input) {
		if(input != null) {
			this.previous = Optional.of(input);
			input.setNext(this);
		}
		else {
			this.next = Optional.empty();
		}
	}
}
