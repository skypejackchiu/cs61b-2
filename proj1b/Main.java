public class Main {
    public static void main(String[] args){
        String string = "chiu";
        Palindrome palindrome = new Palindrome();
        if(palindrome.isPalindrome(string)){
            System.out.println(string + " is palindrome");
        } else {
            System.out.println(string + " is NOT palindrome");
        }
    }
}
