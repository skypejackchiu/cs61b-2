package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class VNode  {
        private int v;
        private int h;
        private Integer priority;

        private VNode(int v, int dist) {
            this.v = v;
            priority = dist + h(v);
        }
    }

    private class VNodeComparator implements Comparator<VNode> {
        @Override
        public int compare(VNode node1, VNode node2) {
            return node1.priority.compareTo(node2.priority);
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int estimatedDistance;

        int vX = maze.toX(v);
        int vY = maze.toY(v);
        int tX = maze.toX(t);
        int tY = maze.toY(t);
        estimatedDistance = Math.abs(vX- tX) + Math.abs(vY - tY);

        return estimatedDistance;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ<VNode> fringe = new MinPQ<>(maze.V(), new VNodeComparator());
        VNode currentNode = new VNode(s, distTo[s]);
        fringe.insert(currentNode);

        while (!fringe.isEmpty()) {
            currentNode = fringe.delMin();
            marked[currentNode.v] = true;
            announce();
            if (currentNode.v == t) {
                return;
            }
            for (int w : maze.adj(currentNode.v)) {
                if (distTo[currentNode.v] + 1 < distTo[w]) {
                    distTo[w] = distTo[currentNode.v] + 1;
                    edgeTo[w] = currentNode.v;
                }

                if (!marked[w]) {
                    VNode WNode = new VNode(w, distTo[w]);
                    fringe.insert(WNode);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

