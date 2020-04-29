import java.util.Iterator;

public class JacobsLinkedList<junk> implements JacobsList<junk> {

    private int size=0;
    private LinkNode sentinel;

    //class to represent link in list
    private class LinkNode{
        junk item;
        LinkNode next;
        LinkNode previous;

        public LinkNode(junk item, LinkNode next, LinkNode previous){
            this.item = item;
            this.next = next;
            this.previous= previous;
        }
    }

    private class LinkListIterator implements Iterator<junk> {
        private LinkNode node;
        public LinkListIterator() {
            node = new LinkNode(sentinel.next.item,sentinel.next.next,null);
        }
        public boolean hasNext() { return node.next!=null; }
        public junk next() {
            junk returnItem = node.item;
            node = node.next;
            return returnItem;
        }
    }

    //constructor with no arguments, initializes sentinel
    public JacobsLinkedList(){
        sentinel = new LinkNode(null, null, null);
    }

    //constructor that takes item as argument, initializes sentinel and first link
    public JacobsLinkedList(junk item){
        LinkNode firstItem = new LinkNode(item,null,null);
        sentinel = new LinkNode(null,firstItem,firstItem);
        size = 1;
    }

    //adds item to front of list, does not accept null values
    @Override
    public boolean add(junk item){
        if (item == null){
            return false;
        }

        LinkNode lastItem = new LinkNode(item,null,sentinel.previous);
        if (isEmpty()) {
            sentinel.next = lastItem;
            sentinel.previous = lastItem;
            size++;
            return true;
        }

        LinkNode oldLast = sentinel.previous;
        oldLast.next = lastItem;
        sentinel.previous =lastItem;
        size++;
        return true;
    }

    //private method for find an item by index starting at the front
    private LinkNode findForwards(int index, LinkNode node){
        if (index == 0){
            return node;
        }
        return findForwards(index-1, node.next);
    }
    //private method for find an item by index starting at the back
    private LinkNode findBackwards(int index, LinkNode node){
        if (index == size - 1){
            return node;
        }
        return findBackwards(index+1, node.previous);
    }

    //finds whether going backwards or forward is more efficient
    private LinkNode findDirection(int index){
        if (index < size/2){
            return findForwards(index, sentinel.next);
        }
        return findBackwards(index, sentinel.previous);
    }

    //adds item at specified index, add at end if index is larger than list
    @Override
    public boolean add(junk item, int index) {
        if (index<0 || item ==null){return false;}
        if (index >= size){return add(item);}

        LinkNode temp = findDirection(index);
        LinkNode prev = temp.previous;
        LinkNode newNode = new LinkNode(item, temp, prev);
        prev.next = newNode;
        temp.previous = newNode;

        size++;
        return true;
    }

    @Override
    public boolean addAll(JacobsCollection items) {return false;}

    @Override
    public void clear() {
        sentinel.next =null;
        sentinel.previous = null;
        size = 0;
    }

    @Override
    public boolean contains(junk item) {
        LinkNode target = getNode(item,sentinel.next);

        try {
            return target.item.equals(item);
        } catch (Exception NullPointerException){
            return false;
        }
    }
    @Override
    public int indexOf(Object item) {
        LinkNode ptr = new LinkNode(null,sentinel.next,null);
        for (int i=0; i<size();i++){
            if(ptr.item == item){return i;}
            ptr=ptr.next;
        }
        return -1;
    }
    @Override
    public int[] indexOf(junk item, boolean all) {
        LinkNode ptr = new LinkNode(null,sentinel.next,null);

        if (all) {
            JacobsLinkedList holder = new <Integer>JacobsLinkedList();
            for (int i = 0; i < size(); i++) {
                if (ptr.item == item) {
                    holder.add(i);
                }
                ptr = ptr.next;
            }

            if (holder.size()==0){return new int[]{-1};}

            int[] indexArray = new int[holder.size()];
            for (int i = 0; i < holder.size(); i++) {
                indexArray[i] = (int) holder.get(0);
                holder.remove(0);
            }

            return indexArray;
        }
        return new int[]{indexOf(item)};
    }

    @Override
    public junk get(int index) {
        if (index < 0 || index >=size){return null;}
        return findDirection(index).item;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Iterator<junk> iterator() {
        return new LinkListIterator();
    }

    private LinkNode getNode(junk item, LinkNode node){
        try {
            if (node.item.equals(item)) {
                return node;
            }
            return getNode(item, node.next);
        } catch (Exception NullPointerException){
            return null;
        }
    }

    //removes first instance of an item
    @Override
    public boolean remove(junk item) {
        LinkNode target = getNode(item, sentinel.next);

        if (target.item==null){return false;}

        target.previous.next = target.next;
        target.next.previous = target.previous;
        size--;
        return true;
    }

    @Override
    public junk remove(int index) {
        if (index>=size){return null;}

        LinkNode removedNode = findDirection(index);
        size--;

        if (index==size){
            sentinel.previous = removedNode.previous;
            removedNode.previous.next =null;
        } else if (index ==0){
            sentinel.next = removedNode.next;
            removedNode.next.previous =null;
        }else {
            removedNode.previous.next = removedNode.next;
            removedNode.next.previous = removedNode.previous;
        }
        return removedNode.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public junk set(int index, junk item) {
        LinkNode setNode = findDirection(index);
        junk oldItem = setNode.item;
        findDirection(index).item = item;
        return  oldItem;
    }

    @Override
    public <junk>junk[] toArray(junk[] a) {
        a = (junk[])java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), size);

        LinkNode temp = new LinkNode(null,sentinel.next,null);

        for (int i =0; i<size;i++){
            a[i] = (junk) temp.next.item;
            temp = temp.next;
        }
        return a;
    }

    @Override
    public boolean containsAll(JacobsCollection items) {
        return false;
    }
}
