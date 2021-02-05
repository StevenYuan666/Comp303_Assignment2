//Enforce all generators should implement the following method, which will be used in library class
import java.util.LinkedList;

public interface Generator {
	public LinkedList<Watchable> generate(LinkedList<Watchable> allElements);
}
