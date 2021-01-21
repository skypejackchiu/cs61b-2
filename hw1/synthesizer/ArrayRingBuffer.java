package synthesizer;
import synthesizer.AbstractBoundedQueue;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (!isFull()) {
            rb[last] = x;
            fillCount += 1;
            if (last != capacity - 1) {
                last += 1;
            } else {
                last = 0;
            }
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (!isEmpty()) {
            T oldestItem = rb[first];
            rb[first] = null;
            fillCount -= 1;
            if (first != capacity - 1) {
                first += 1;
                return oldestItem;
            } else {
                first = 0;
                return oldestItem;
            }
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

        /**
         * Return oldest item, but don't remove it.
         */
        public T peek () {
            // TODO: Return the first item. None of your instance variables should change.
            // TODO: When you get to part 5, implement the needed code to support iteration.
            if(isEmpty()) {
                throw new RuntimeException("Ring buffer underflow");
            } else {
                return rb[first];
            }
        }

        @Override
        public Iterator<T> iterator(){
            return new ARBufferIterator();
        }

        private class ARBufferIterator implements Iterator<T>{
            private int Pos;

            public ARBufferIterator(){
                Pos = 0;
            }

            public boolean hasNext(){
                return Pos < capacity;
            }

            public T next(){
                T returnRbItem = rb[Pos];
                Pos += 1;
                return returnRbItem;
            }
        }

}
