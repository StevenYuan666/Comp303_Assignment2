//Implement the Generator to filter by Language
import java.util.LinkedList;

public class ByLanguage implements Generator{
	private String language;
	private LinkedList<Watchable> filtered;
	
	public ByLanguage(String inputLanguage) {
		this.language = inputLanguage;
		this.filtered = new LinkedList<Watchable>();
	}

	@Override
	public LinkedList<Watchable> generate(LinkedList<Watchable> allElements) {
		for(Watchable w : allElements) {
			if(w.getLanguage().equalsIgnoreCase(this.language)) {
				this.filtered.add(w);
			}
		}
		return this.filtered;
	}
	
}
