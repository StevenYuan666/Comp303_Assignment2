import java.util.ArrayList;

public interface Bingeable<T> extends Iterable<T>{
	public void add(T w);
	public void watchOne();
	public ArrayList<T> accessAll();
}
