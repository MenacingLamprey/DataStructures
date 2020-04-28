/**
 *  The {@code JacobsCollection} interface represents an abstraction for a collection of items.
 *  Intended to mimic {@code java.util.Collection}
 *  @author Jacob Walsh
 */

import java.util.Collection;
interface JacobsCollection<junk> extends Iterable<junk>{

	/**
	 *adds item to collection
	 * @param item, generic item type to add
	 * @return boolean verifying item was added
	 */
	boolean add(junk item);


}