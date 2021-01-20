package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(10);
        arb.enqueue("Japan");
        arb.enqueue("Arab");
        arb.enqueue("USA");
        System.out.println(arb.dequeue());
        System.out.println(arb.peek());
        System.out.println(arb.dequeue());
        arb.enqueue("China");
        System.out.println(arb.dequeue());
        System.out.println(arb.peek());
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
    }
} 
