
/*
 * Although some methods here may not make sense, we will need all of these method in WatchList
 * to make the WatchList can contain any Watchable Objects
 * Or if we do not abstract some of methods here, we have to use "instanceof" in WatchList, which 
 * would be really inconvenient for future improving, like if we want to add a new Class Documentary
 * which implements watchable interface, if we don't have folloing methods, we have to change the 
 * WatchList Class again, which is not what we want.
 */

public interface Watchable {
	//abstract some common method in the class which can be watched
	public String getTitle();
	public String getStudio();
	public String getLanguage();
	public Status getValidity();
	public void updateStatus();
	public boolean ifSame(Watchable m);
	//Copy a same object as the one that called this method
	public Watchable getCopy();
}
