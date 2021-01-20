import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    static Palindrome palindrome = new Palindrome();
    static OffByOne newOffByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("bob"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("$"));
        assertFalse(palindrome.isPalindrome("butterfly"));
        assertFalse(palindrome.isPalindrome("Bob"));
    }

    @Test
    public void testIsPalindromeNew(){
        assertTrue(palindrome.isPalindrome("flake", newOffByOne));
        assertTrue(palindrome.isPalindrome("%", newOffByOne));
        assertTrue(palindrome.isPalindrome("", newOffByOne));
        assertFalse(palindrome.isPalindrome("aaabbbaaa", newOffByOne));
        assertFalse(palindrome.isPalindrome("jack", newOffByOne));
    }
}
