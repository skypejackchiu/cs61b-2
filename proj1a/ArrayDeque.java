public class ArrayDeque {
    private T[] items;
    private int size;
    private int INITIAL_CAPACITY = 8;

    //Creates an empty array deque
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T firstitem) {
        resize();
        System.arraycopy(items, 0, items, 1, size());
        items[0] = firstitem;
        size++;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T lastitem) {
        resize();
        items[size] = lastitem;
        size = size++;
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        T returnitem = getFirst();
        System.arraycopy(items, 1, items, 0, size - 1);
        items[size - 1] = null;
        size--;
        return returnitem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (size != 0) {
            T returnitem = getLast();
            items[size] = null;
            size--;
            return returnitem;
        }
        return null;
    }

    //Returns the first item at the back of the deque.
    public T getFirst() {
        return items[0];
    }

    //Returns the last item at the back of the deque.
    public T getLast() {
        return items[size - 1];
    }

    public void resize (){
        if(size == items.length){
            expand();
        }
        if(size < items.length / 4 && size > 8){
            reduce();
        }
    }

    private void expand(){
        T[] newitems = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, newitems, 0, size);
        items = newitems;
        newitems = null;
    }

    private void reduce(){
        T[] newitems = (T[]) new Object[items.length / 2];
        System.arraycopy(items, 0, newitems, 0, size);
        items = newitems;
        newitems = null;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        if(index >= size || index < 0){
            return null;
        }
        return items[index];
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return (size == 0 ? true : false);
    }

    //Returns the number of items in the deque.
    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    // Once all the items have been printed, print out a new line.
    public void printDeque(){
        int count = 0;
        While(count < size) {
            System.out.print(items[count] + " ");
            count++;
        }
        System.out.println();
    }

    //Creates a deep copy of other
    public ArrayDeque(ArrayDeque other){
        T[] newarray = (T[]) new Object[size()];
        System.arraycopy(other, 0, newarray, 0, size);
    }
}
