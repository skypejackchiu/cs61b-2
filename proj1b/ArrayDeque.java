public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    private static final int INITIAL_CAPACITY = 8;

    //Creates an empty array deque
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int minusOne(int index){
        return Math.floorMod(index-1, items.length);
    }

    public int plusOne(int index){


        return Math.floorMod(index+1, items.length);
    }

    public int plusOne(int index, int length){
        return Math.floorMod(index+1, length);
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
        resizeHelper(items.length * 2);
    }

    private void reduce(){
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity){
        T[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i, temp.length)){
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    @Override
    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    //Returns the first item at the back of the deque.
    public T getFirst() {
        return items[plusOne(nextFirst)];
    }

    @Override
    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        resize();
        T res = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        return res;
    }

    @Override
    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    //Returns the last item at the back of the deque.
    public T getLast() {
        return items[minusOne(nextLast)];
    }

    @Override
    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        resize();
        T res = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;
        return res;
    }

    @Override
    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        if(index >= size || index < 0){
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst) + index, items.length);
        return items[index];
    }

    @Override
    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return (size == 0 ? true : false);
    }

    @Override
    //Returns the number of items in the deque.
    public int size(){
        return size;
    }

    @Override
    //Prints the items in the deque from first to last, separated by a space.
    // Once all the items have been printed, print out a new line.
    public void printDeque(){
        for(int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)){
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    //Creates a deep copy of other
    public ArrayDeque(ArrayDeque other){
        T[] newarray = (T[]) new Object[size()];
        System.arraycopy(other, 0, newarray, 0, size);
    }
}
