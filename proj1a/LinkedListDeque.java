public class LinkedListDeque <T> {

    private class Node {
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

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == o) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        Node node = sentinel;
        while (node.next != sentinel) {
            node = node.next;
            System.out.print(node.item);
            System.out.print(" ");
        }
        System.out.println();

    }

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

    public T getRecursiveHelper(int index, int count, Node node) {
        if (index == count) {
            return node.item;
        }
        return getRecursiveHelper(index, count+1, node.next);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int count = 0;
        Node node = sentinel.next;
        return getRecursiveHelper(index, count, node);
    }

}