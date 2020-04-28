import java.util.Iterator;

public class JacobsLinkedList<junk> implements JacobsList<junk> {

    private int size=0;
    private LinkNode sentinal;


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

    public JacobsLinkedList(){
        sentinal = new LinkNode(null, null, null);
    }

    public JacobsLinkedList(junk item){
        LinkNode firstItem = new LinkNode(item,null,null);
        sentinal = new LinkNode(null,firstItem,firstItem);
        size = 1;
    }

    //adds item to front of list
    @Override
    public boolean add(junk item){
        if (item == null){
            return false;
        }

        LinkNode lastItem = new LinkNode(item,null,sentinal.previous);
        if (isEmpty()) {
            sentinal.next = lastItem;
            sentinal.previous = lastItem;
            size++;
            return true;
        }

        LinkNode oldLast = sentinal.previous;
        oldLast.next = lastItem;
        sentinal.previous =lastItem;
        size++;
        return true;
    }

    private LinkNode findForwards(int index, LinkNode node){
        if (index == 0){
            return node;
        }
        return findForwards(index-1, node.next);
    }

    private LinkNode findBackwards(int index, LinkNode node){
        if (index == size - 1){
            return node;
        }
        return findBackwards(index+1, node.previous);
    }

    //finds whether going backwards or forward is more efficient
    private LinkNode findDirection(int index){
        if (index < size/2){
            return findForwards(index, sentinal.next);
        }
        return findBackwards(index, sentinal.previous);
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
    public boolean addAll(JacobsCollection items) {
        return false;
    }

    @Override
    public void clear() {
        sentinal.next =null;
        sentinal.previous = null;
        size = 0;
    }

    @Override
    public boolean contains(junk item) {
        LinkNode ptr = new LinkNode(null,sentinal.next,null);
        for (int i=0; i<size;i++){
            if(ptr.item == item){return true;}
            ptr=ptr.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object item) {
        return 0;
    }

    @Override
    public int[] indexOf(Object item, boolean all) {
        return new int[0];
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
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean remove(Object O) {
        return false;
    }

    @Override
    public junk remove(int index) {
        LinkNode removedNode = findDirection(index);
        removedNode.previous.next = removedNode.next;
        removedNode.next.previous = removedNode.previous;
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

        LinkNode temp = new LinkNode(null,sentinal.next,null);

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
