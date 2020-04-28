/**
 *  The {@code JacobsCollection} interface represents an abstraction for a collection of items.
 *  Intended to mimic {@code java.util.Collection}
 *  @author Jacob Walsh
 */
import java.util.Iterator;
interface JacobsCollection<junk> extends Iterable<junk>{

	/**
	 * adds item to collection
	 * @param item, generic item to add
	 * @return boolean verifying item was added
	 */
	boolean add(junk item);

	/**
	 * adds collection of items to this collection
	 * @param items, collection of items to add
	 * @return boolean verifying items were added
	 */
	boolean addAll(JacobsCollection<? extends junk> items);

	/**
	 *removes all items from collection
	 */
	void clear();
 
	/**
	 * checks if item is in collection
	 * @param item, item being checked
	 * @return boolean verifying item is contained
	 */
	boolean contains(junk item);

	 /**
	  *checks if collection of items are in this collection
	  * @param items, items being checked
	  * @return boolean verifying items are present
	 */
	boolean containsAll(JacobsCollection<?> items);

	/**
	  *checks if collection of items are "equal"
	  * @param O Object being compared
	  * @return boolean verifying items this and O are the same
	 */
	boolean equals(Object O);

	/**
	  *Computes and returns collections hash code
	  * @return hash code of this collection
	 */
	int hashCode();


	/**
	  *Checks if collection is empty
	  * @return boolean verifying collection is empty
	 */
	boolean isEmpty();

	/**
	  *makes an iterator for the items in this collection
	  * @return iterator
	 */
	Iterator<junk> iterator();

	/**
	  *removes item from collection, if exists
	  * @return boolean verifying object was removed
	  */
	boolean remove(Object O);

	/**
	  * @return size of collection
	 */
	int size();

		/**
	  *turns collection to an array
	  *@param a, array type to be returned
	  *@return items in an array
	 */
	<item> item[] toArray(item[] a);
}