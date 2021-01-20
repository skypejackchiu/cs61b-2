import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('m','n'));
        assertTrue(offByOne.equalChars('n','m'));
        assertTrue(offByOne.equalChars('%','&'));
        assertFalse(offByOne.equalChars('c','s'));
        assertFalse(offByOne.equalChars('a','j'));
    }
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

}
