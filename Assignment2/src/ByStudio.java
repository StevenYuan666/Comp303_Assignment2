//Implement the Generator to filter by Studio
import java.util.LinkedList;

public class ByStudio implements Generator{
	private String studio;
	private LinkedList<Watchable> filtered;
	
	public ByStudio(String inputStudio) {
		this.studio = inputStudio;
		this.filtered = new LinkedList<Watchable>();
	}

	@Override
	public LinkedList<Watchable> generate(LinkedList<Watchable> allElements) {
		for(Watchable w : allElements) {
			if(w.getStudio().equalsIgnoreCase(this.studio)) {
				this.filtered.add(w);
			}
		}
		return this.filtered;
	}

}
