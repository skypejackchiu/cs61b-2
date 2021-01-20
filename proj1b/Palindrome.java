public class Palindrome {
    private String input;

    public Deque<Character> wordToDeque(String word){
        Deque<Character> result = new ArrayDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word){
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque d) {
        if(d.size() <= 1) {
            return true;
        } else if (d.removeFirst() != d.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(d);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque d, CharacterComparator cc){
        if(d.size() <= 1){
            return true;
        } else{
            char a = (char) d.removeFirst();
            char b = (char) d.removeLast();
            if(!cc.equalChars(a, b)){
                return false;
            } else{
                return isPalindromeHelper(d, cc);
            }
        }
    }













}

