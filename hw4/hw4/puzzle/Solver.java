package hw4.puzzle;

import java.util.ArrayDeque;
import java.lang.Comparable;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    /** Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */

    private int moves;
    private ArrayDeque<WorldState> solution;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private int estimatedMoves;
        private int priority;
        private SearchNode prev;

        public SearchNode(WorldState state, int moves, SearchNode prev){
            this.state = state;
            this.moves = moves;
            this.prev = prev;
            this.estimatedMoves = this.state.estimatedDistanceToGoal();
            this.priority = this.moves + this.estimatedMoves;
        }

        @Override
        public int compareTo(SearchNode node) {
            int p1 = this.priority;
            int p2 = node.priority;
            return p1 - p2;
        }
    }



    /*
    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode left, SearchNode right) {
            return left.priority.compareTo(right.priority);
        }
    }
    */

    public Solver(WorldState initial){
        MinPQ<SearchNode> minPQ = new MinPQ<>();
        SearchNode initialSN = new SearchNode(initial,0, null);
        SearchNode goalSN = new SearchNode(initial,0, null);

        minPQ.insert(initialSN);

        while (!minPQ.isEmpty()) {
            SearchNode lowestSN = minPQ.delMin();
            if (lowestSN.state.isGoal()) {
                goalSN = lowestSN;
                this.moves = goalSN.moves;
                break;
            } else {
                for (WorldState state : lowestSN.state.neighbors()) {
                    int newMoves = lowestSN.moves + 1;
                    if (lowestSN.prev != null && state.equals(lowestSN.prev.state)) {
                        continue;
                    }
                    SearchNode newSN = new SearchNode(state, newMoves, lowestSN);
                    minPQ.insert(newSN);
                }
            }
        }

        solution = new ArrayDeque<WorldState>();
        solution.addFirst(goalSN.state);
        while (goalSN.prev != null) {
            goalSN = goalSN.prev;
            solution.addFirst(goalSN.state);
        }

    }


    /** Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves(){
        return moves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     *  to the solution.
     */
    public Iterable<WorldState> solution(){
        return solution;
    }

}
