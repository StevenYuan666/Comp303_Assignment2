//Implement the Generator to make a watch list with the input number
import java.util.LinkedList;

public class ByRandomNum implements Generator{
	private int num;
	private LinkedList<Watchable> filtered;
	
	public ByRandomNum(int times) {
		this.num = times;
		this.filtered = new LinkedList<Watchable>();
	}

	@Override
	public LinkedList<Watchable> generate(LinkedList<Watchable> allElements) {
		while(this.filtered.size() < this.num) {
			int index = (int) (Math.random() * (allElements.size() - 1));
			if(!this.filtered.contains(allElements.get(index))) {
				this.filtered.add(allElements.get(index));
			}
		}
		return this.filtered;
	}

}
