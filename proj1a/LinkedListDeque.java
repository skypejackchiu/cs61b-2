public class LinkedListDeque <T> {

    public class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque () {
        sentinel = new Node((T)"99", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node((T)"99", null, null);
        sentinel.next = new Node(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size ++;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size ++;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    //Once all the items have been printed, print out a new line.
    public void printDeque() {
        Node node = sentinel;
        while (node.next != sentinel) {
            node = node.next;
            System.out.print(node.item);
            System.out.print(" ");
        }
        System.out.println();

    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (0 == size) {
            return null;
        }
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (0 == size) {
            return null;
        }
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return res;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        int count = 0;
        Node node = sentinel;
        while (node.next != sentinel) {
            node = node.next;
            if (count == index) {
                return node.item;
            }
            count++;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int count = 0;
        Node node = sentinel.next;
        return getRecursiveHelper(index, count, node);
    }

    public T getRecursiveHelper(int index, int count, Node node) {
        if (index == count) {
            return node.item;
        }
        return getRecursiveHelper(index, count+1, node.next);
    }

}