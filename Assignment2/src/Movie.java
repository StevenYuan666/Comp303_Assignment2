import java.io.File;
import java.util.HashMap;

public class Movie implements Watchable, Sortable<Movie>{
	
	final private File path;
	final private Formats format;
	private Status status;
	
	final private String title;
	final private String language;
	final private String studio;
	private int sequel;
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
		/* 
		 * Initialize the sequel number from the input name
		 * For example, if the input name is "Avengers2", the sequel number should be 2
		 * If there is no sequel number in the name, it should be initialized as 1 by default
		 * For example, if the input name is "Transformer", the sequel number should be 1
		 */
		if(this.title.charAt(this.title.length() - 1) <= '9'
				&& this.title.charAt(this.title.length() - 1) >= '1') {
			this.sequel = (int) (this.title.charAt(this.title.length() - 1) - '0');
		}
		this.sequel = 1;
	}
	
	//to Deeply Copy a movie object
	public Movie(Movie m) {
		this.sequel = m.sequel;
		this.path = m.path;
		this.format = m.format;
		this.status = m.status;
		this.title = m.title;
		this.language = m.language;
		this.studio = m.studio;
		this.custom = new HashMap<String, String>(m.custom);
	}
	
	//Easier for client to print out
	@Override
	public String toString() {
		return this.title;
	}
	
	//Update the status of the movie, to check if the file exists or not
	public void updateStatus() {
		if(this.path.exists()) {
			this.status = Status.Valid;
		}
		else {
			this.status = Status.Invalid;
		}
	}
	
	/*
	 * Check if the two movies have same file, even though they are two object
	 * Used in WatchList class and Library class
	 * I assume that two Movies are same if they are refer to the same file
	 */
	public boolean ifSame(Movie m) {
		return this.path.equals(m.path);
	}
	
	//Getters for the fields
	public String getPath() {
		return this.path.getPath();
	}
	public Status getValidity() {
		this.updateStatus();
		return this.status;
	}
	public Formats getFormat() {
		return this.format;
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

	@Override
	/*
	 * Compare the movies in alphabetical order
	 * If the name is same, compare the SeqNum
	 * For example, Avengers2 is smaller than Zootopia
	 * Transformer3 is bigger than Transformer1
	 */
	public int compareTo(Movie m) {
		return this.title.compareToIgnoreCase(m.title);
	}

	@Override
	public int getSeqNum() {
		return this.sequel;
	}

	
}
