import java.util.*;

public class MazeCreator {

    Solver solver;

    public static Maze initMaze(DifficultyLevel level){
        Random rand = new Random();
        int size = rand.nextInt(3) + level.getSize();
        char[][] mazeGrid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mazeGrid[i][j] = '.';
            }
        }

        for (int j = 0; j < size; j++) {
            mazeGrid[size - 1][j] = 'x';
        }

        int start = rand.nextInt(1, size/2 - 1);
        int goal = rand.nextInt(size/2 + 1, size - 1);
        mazeGrid[size - 1][start] = '.';
        mazeGrid[size - 1][goal] = '.';



        //int blocks = rand.nextInt(size/5,size - 5);
        int blocks = 1;
        for (int i = 0; i < blocks; i++) {
            int rowStart = rand.nextInt(0, size - 2);
            int colStart = rand.nextInt(0, size - 1);
            //int blockHeight = rand.nextInt(2, size/3);
            //int blockWidth = rand.nextInt(2, size/3);
            int blockHeight = rand.nextInt(1, 3);
            int blockWidth = rand.nextInt(1, 3);
            for (int r = rowStart; r < rowStart + blockHeight && r < size; r++) {
                for (int c = colStart; c < colStart + blockWidth && c < size; c++) {
                    mazeGrid[r][c] = 'x';
                }
            }
        }



        return new Maze(size, size, mazeGrid, start, goal);
    }

    private static void adjustMaze(Maze maze) {


        List<Solution> newSolutions = Solver.dfs(maze);
        List<Solution> invalidSolutions = new ArrayList<>();
        List<Solution> validSolutions = new ArrayList<>();
        for (Solution s: newSolutions){
            if(s.meetReq(DifficultyLevel.EASY)){
                validSolutions.add(s);
            }
            else{
                invalidSolutions.add(s);
            }
        }
        List<State> possibleWalls = findLeastOptimalStates(invalidSolutions);
        int i = 0;
        Random rand = new Random();
        do {
            int row, col;
            if(!possibleWalls.isEmpty()) {
                row = possibleWalls.get(i).row;
                col = possibleWalls.get(i).col;
            }
            else {
                row = rand.nextInt(0, maze.getRows() - 1);
                col = rand.nextInt(0, maze.getCols());
                while (maze.getmaze()[row][col] != '.') {
                    row = rand.nextInt(0, maze.getRows() - 1);
                    col = rand.nextInt(0, maze.getCols());
                }
            }
            maze.getmaze()[row][col] = 'x';
            System.out.println("x added ");
            newSolutions = Solver.dfs(maze);
            invalidSolutions.clear();
            validSolutions.clear();
            for (Solution s: newSolutions){
                if(s.meetReq(DifficultyLevel.EASY)){
                    validSolutions.add(s);
                }
                else{
                    invalidSolutions.add(s);
                }
            }
            if (validSolutions.isEmpty()){
                maze.getmaze()[row][col] = '.';
                i++;
                continue;
            }
            possibleWalls = findLeastOptimalStates(invalidSolutions);
            i = 0;
        }while(!(validSolutions.size() == 1 && invalidSolutions.isEmpty()));


    }


    private static List<State> findLeastOptimalStates(List<Solution> solutions) {
        Map<State, Integer> stateFrequency = new HashMap<>();
        for (Solution solution : solutions) {
            Set<State> uniqueStates = new HashSet<>(solution.getSol());
            for (State state : uniqueStates) {
                stateFrequency.put(state, stateFrequency.getOrDefault(state, 0) + 1);
            }

        }
        PriorityQueue<Map.Entry<State, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );

        pq.addAll(stateFrequency.entrySet());

        List<State> topStates = new ArrayList<>();
        for (int i = 0; i < 5 && !pq.isEmpty(); i++) {
            topStates.add(pq.poll().getKey());
        }

        return topStates;
    }


    public static void main(String[] args) {
        //char[][] mazeGrid = {{'.','.','.','.','.'}, {'.','.','.','.','.'}, {'.','.','.','.','.'}, {'.','.','.','.','.'}, {'x','.','x','.','x',}};
        //Maze maze = new Maze(5, 5, mazeGrid, 1, 3);

        Maze maze = initMaze(DifficultyLevel.EASY);

        List<Solution> solutions = Solver.dfs(maze);

        while(solutions.size() > 10 && Solver.containsValidSol(solutions)){
            maze = initMaze(DifficultyLevel.EASY);
            solutions = Solver.dfs(maze);
        }
        System.out.println(solutions.size());
        maze.printMaze();
        adjustMaze(maze);
        maze.printMaze();
        solutions = Solver.dfs(maze);
        solutions.get(0).printMetrics();
        System.out.println(solutions.size());
    }
}
