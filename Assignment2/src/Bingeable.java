/*
 * A Bingeable thing should be able to add a thing to the list, or play a thing
 * Using the type generics here since a Bingeable thing may not only contain watchable things
 * it also can contain listenable or playable things, so the type generics can make this 
 * interface as general as possible
 */
public interface Bingeable<T> extends Iterable<T>{
	public void add(T w);
	public void playOne();
	public int validNum();
}
