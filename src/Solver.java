import java.util.*;

public class Solver {

    public static List<Solution> findAllSolutions(Maze maze) {
        Set<State> visited = new HashSet<>();
        List<Solution> solutions = new ArrayList<>();
        Deque<State> pathStack = new ArrayDeque<>();

        dfs(maze.getStart(), visited, pathStack, solutions, maze);

        return solutions;
    }

    private static void dfs(State current, Set<State> visited, Deque<State> pathStack, List<Solution> solutions, Maze maze) {
        visited.add(current);
        pathStack.push(current);

        if (current.equals(maze.getGoal())) {
            solutions.add(new Solution(new ArrayList<>(pathStack)));
            if(solutions.size()>100){
                return;
            }
        } else {
            for (State next : maze.legalNeighbors(current)) {
                if (!visited.contains(next)) {
                    dfs(next, visited, pathStack, solutions, maze);
                }
            }
        }

        visited.remove(current);
        pathStack.pop();
    }
}
