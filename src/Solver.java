import java.util.*;

public class Solver {

    private Maze maze;
    private Map<State, State> parents;
    private Set<State> visited;
    private Deque<State> stack;
    private List<Solution> solutions;

    public Solver(Maze maze) {
        this.maze = maze;
        this.parents = new HashMap<>();
        this.visited = new HashSet<>();
        this.stack = new ArrayDeque<>();
        this.solutions = new ArrayList<>();
    }

    public List<Solution> dfs() {
        State start = maze.getStart();
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            State current = stack.pop();
            if (current.equals(maze.getGoal())) {
                Solution possibleSol = makePath(current);
                //addSolution(possibleSol);
                solutions.add(possibleSol);
                return solutions;
            }
            List<State> neighbors = maze.legalNeighbors(current);
            for (State n : neighbors) {
                if (!visited.contains(n)) {
                    parents.put(n, current);
                    stack.push(n);
                    visited.add(n);
                }
            }
        }

        return solutions;
    }

    private Solution makePath(State goal) {
        List<State> path = new ArrayList<>();
        State state = goal;
        while (state != null) {
            path.add(state);
            state = parents.get(state);
        }
        Collections.reverse(path);

        return new Solution(path);
    }

    private void addSolution(Solution currentSolution) {
        for (Solution s : solutions) {
            if (s.equals(currentSolution)) {
                return;
            }
        }
        if(!currentSolution.meetReq(DifficultyLevel.EASY)) {
            return;
        }
        Set<State> setComp = new HashSet<>(currentSolution.getSol());
        if (setComp.size() != currentSolution.getSolLen()) {
            return;
        }
        solutions.add(currentSolution);
    }


}