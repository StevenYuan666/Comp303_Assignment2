/*
 * All of objects which can be listed in sequence should have following methods
 * Still Using type generics here since if we want to this interface for like Songs, Opera Class
 * in the future, we can still implement this interface, which is much more convenient for
 * maintaining the code
 */
public interface Sortable<T>{
	public T getNext();
	public void setNext(T input);
	public T getPrevious();
	public void setPrevious(T input);
}
