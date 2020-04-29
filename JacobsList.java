import java.util.Iterator;

/**
 *  The {@code JacobsList} interface represents an abstraction for a list of items.
 *  Intended to mimic {@code java.util.list}
 *  @author Jacob Walsh
 */
interface JacobsList<junk> extends JacobsCollection<junk> {

    /**
     *adds item to end of list
     * @param item, generic item to add to list
     * @return boolean verifying item was added
     */
    boolean add(junk item);

    /**
     *adds item to list at specified index
     * @param item, generic item to be added
     * @param index, location item will be added,
     *               if index is larger than list size, add to end
     */
    boolean add(junk item, int index);

    /**
     *adds collection of items to this list
     * @param items, collection of items to add
     * @return boolean verifying items were added
     */
    boolean addAll(JacobsCollection<? extends junk> items);


    /**
     *removes all items from list
     */
    void clear();

    /**
     *checks if item is in list
     * @param item, item being checked
     * @return boolean verifying item is contained
     */
    boolean contains(junk item);

    /**
     *returns the indices of specified item, if present in list
     * @param item, item being checked
     * @return index of item
     */
    int indexOf(junk item);

    /**
     *returns the indices of specified item, if present in list
     * @param item, item being checked
     * @param all, if true, will look for all occurrences of item
     * @return indices of item,  [-1] if item does not exist
     */
    int[] indexOf(junk item, boolean all);


    /**
     *checks if collection of items are in this list
     * @param items, items being checked
     * @return boolean verifying items are present
     */
    boolean containsAll(JacobsCollection<?> items);

    /**
     *checks if list of items are "equal"
     * @param O, Object being compared
     * @return boolean verifying items this and O are the same
     */
    boolean equals(Object O);

    /**
     *Computes and returns list hash code
     * @return hash code of this collection
     */
    int hashCode();

    /**
     *returns item at given index, if exists
     * @param index, index of item to be returned
     * @return item at specified index
     */
    junk get(int index);

    /**
     *Checks if list is empty
     * @return boolean verifying collection is empty
     */
    boolean isEmpty();

    /**
     *makes an iterator for the items in this list
     * @return iterator
     */
    Iterator<junk> iterator();

    /**
     *removes first occurrence of item in list, if exists
     * @return boolean verifying object was removed
     */
    boolean remove(junk item);

    /**
     *removes item at specified index from list
     * @return item removed
     */
    junk remove(int index);

    /**
     * @return size of list
     */
    int size();


    /**
     *sets index to be item, returns previous item
     * @param index, location to be set
     * @param item, item to be set
     * @return item originally at index
     */
    junk set(int index, junk item);

    /**
     *turns list to an array
     *@param a, array type to be returned
     *@return items in an array
     */
    <item> item[] toArray(item[] a);
}
