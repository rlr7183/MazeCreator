import java.util.ArrayList;
import java.util.List;

public class Maze {

    private final int[] dcol = {0, 1, 0, -1};
    private final int[] drow = {-1, 0, 1, 0};

    private State start, goal;
    private char[][] maze;
    private int rows, cols;
    private int intersections;
    private Solution solution;
    private int avgDELen;


    public Maze(int rows, int cols, char[][] maze, int start, int goal){
        this.rows = rows;
        this.cols = cols;
        this.maze = maze;
        this.start = new State(rows-1, start, 0);
        this.goal = new State(rows-1, goal, 2);
        countIntersections();
    }

    private int countIntersections() {
        int intersections = 0;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j < cols; j++){
                if(maze[i][j] == '.') {
                    int openPaths = 0;
                    if (i - 1 >= 0 && maze[i - 1][j] == '.') openPaths++;
                    if (i + 1 < rows && maze[i + 1][j] == '.') openPaths++;
                    if (j - 1 >= 0 && maze[i][j - 1] == '.') openPaths++;
                    if (j + 1 < cols && maze[i][j + 1] == '.')openPaths++;

                    if (openPaths > 2) { // More than two open paths mean an intersection
                        intersections++;
                    }
                }
            }
        }
        return intersections;
    }

    public List<State> legalNeighbors(State state) {
        List<State> neighbors = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int newDir = (state.dir + i) % 4;
            int ncol = state.col + dcol[newDir];
            int nrow = state.row + drow[newDir];

            if (ncol >= 0 && ncol < cols && nrow >= 0 && nrow < rows && maze[nrow][ncol] == '.' ) {
                neighbors.add(new State(nrow, ncol, newDir));
            }
        }
        return neighbors;
    }

    public void printMaze(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    public char[][] getmaze(){
        return maze;
    }
    public State getStart() {
        return start;
    }
    public State getGoal() {
        return goal;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Solution getSolution() {
        return solution;
    }

    public int getAvgDELen() {
        return avgDELen;
    }

    public void setAvgDELen(int avgDELen) {
        this.avgDELen = avgDELen;
    }
}
