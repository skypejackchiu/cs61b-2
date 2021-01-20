public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N){
        this.N = N;
    }

    public boolean equalChars(char x, char y){
        int diff = x - y;
        if(diff == N || diff == -N){
            return true;
        } else{
            return false;
        }
    }

}
