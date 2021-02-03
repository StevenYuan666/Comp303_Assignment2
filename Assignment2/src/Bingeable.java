import java.util.ArrayList;

public interface Bingeable<T> extends Iterable<T>{
	public void add(T w);
	public void playOne();
	public int valid();
	public ArrayList<T> accessAll();
}
