import java.util.LinkedList;

public class ByRandomNum implements Generator{
	private int num;
	private LinkedList<Watchable> filtered;
	
	public ByRandomNum(int times) {
		this.num = times;
		this.filtered = new LinkedList<Watchable>();
	}

	@Override
	public LinkedList<Watchable> generate(Library lib) {
		LinkedList<Watchable> all = lib.getAll();
		while(this.filtered.size() < this.num) {
			int index = (int) Math.random() * (all.size() - 1);
			this.filtered.add(all.get(index));
		}
		return this.filtered;
	}

}
