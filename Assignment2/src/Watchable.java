
//目前这么写貌似不是很 separation of concerns，得再改

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
