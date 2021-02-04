
import java.util.LinkedList;

public class ByLanguage implements Generator{
	private String language;
	private LinkedList<Watchable> filtered;
	
	public ByLanguage(String inputLanguage) {
		this.language = inputLanguage;
		this.filtered = new LinkedList<Watchable>();
	}

	@Override
	public LinkedList<Watchable> generate(Library lib) {
		LinkedList<Watchable> all = lib.getAll();
		for(Watchable w : all) {
			if(w.getLanguage().equalsIgnoreCase(this.language)) {
				this.filtered.add(w);
			}
		}
		return this.filtered;
	}
	
}
