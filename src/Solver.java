import java.util.*;

public class Solver {


    public static List<Solution> dfs(Maze maze) {
        Map<State, State> parents = new HashMap<>();
        Set<State> visited = new HashSet<>();
        Deque<State> stack = new ArrayDeque<>();
        List<Solution> solutions = new ArrayList<>();
        State start = maze.getStart();
        stack.push(start);

        dfsRecursive(stack, start, maze.getGoal(), visited, parents, solutions, maze);

        return solutions;
    }

    private static void dfsRecursive(Deque<State> stack, State current, State goal, Set<State> visited, Map<State, State> parents, List<Solution> solutions, Maze maze) {
        if (current.equals(goal)) {
            //System.out.println("Solution found using DFS!");
            solutions.add(makePath(current, parents));

        }

        if( containsValidSol(solutions) && solutions.size()>10){
            return;
        }

        //System.out.printf("Current %d %d %d\n", current.row, current.col, current.dir);
        visited.add(current);
        List<State> neighbors = maze.legalNeighbors(current);
        //System.out.println(neighbors.size());
        for (State n : neighbors) {

            //System.out.printf("N %d %d %d\n", n.row, n.col, n.dir);
            if (!visited.contains(n)) {
                parents.put(n, current);
                stack.push(n);
                dfsRecursive(stack, n, goal, visited, parents, solutions, maze);
                stack.pop();
                visited.remove(n);
                parents.remove(n);
            }
        }
    }

    public static boolean containsValidSol(List<Solution> solutions) {
        for(Solution s: solutions){
            if(s.meetReq(DifficultyLevel.EASY)){
                return true;
            }
        }
        return false;
    }

    private static Solution makePath(State goal, Map<State, State> parents) {
        List<State> path = new ArrayList<>();
        State state = goal;
        while (state != null) {
            path.add(state);
            state = parents.get(state);
        }
        Collections.reverse(path);
        return new Solution(path);
    }
}
