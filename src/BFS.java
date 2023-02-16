import java.util.*;

public class BFS {
    private static List<int[]> successors(int[] state) {
        // unpack the state
        int x = state[0];
        int y = state[1];
        int z = state[2];
        // generate the successors
        List<int[]> successors = new ArrayList<int[]>();
        successors.add(new int[] { x + 1, y, z });

        // return the successors
        return successors;
    }

    private static boolean isValid(int[] state) {
        // unpack the state
        int x = state[0];
        int y = state[1];
        int z = state[2];
        // check if the state is valid

        // return true if the state is valid
        return true;
    }

    private static boolean isGoal(int[] state) {
        // unpack the state
        int x = state[0];
        int y = state[1];
        int z = state[2];
        // check if the state is a goal

        // return true if the state is a goal
        return true;
    }

    private static int bfs(int[] start) {
        List<int[]> frontier = new LinkedList<>();
        frontier.add(start);
        HashSet<int[]> explored = new HashSet<int[]>();

        int steps = 0;
        while (!frontier.isEmpty()) {
            int[] state = frontier.remove(0);
            if (isGoal(state)) {
                return steps;
            }
            if (explored.contains(state)) {
                continue;
            }
            explored.add(state);
            for (int[] successor : successors(state)) {
                if (isValid(successor)) {
                    frontier.add(successor);
                }
            }
            steps++;
        }

        return -1;
    }
}
