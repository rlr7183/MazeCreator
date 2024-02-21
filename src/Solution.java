import java.util.List;

public class Solution {
    private List<State> sol;
    private int solLen;
    private int turns;

    //I dont think loops get tracked bc state includes dir

    public Solution(List<State> sol){
        this.sol = sol;
        solLen = sol.size();
        turns = countTurns();
    }

    private int countTurns() {
        int turns = 0;
        for (int i = 0; i < solLen - 1; i++) {
            State curState = sol.get(i);
            State nextState = sol.get(i + 1);
            if (curState.dir != nextState.dir){
                turns ++;
            }
        }
        return turns;
    }


    public void printSol(){
        for(State s : sol){
            String direction;
            switch(s.dir){
                case 0:
                    direction = "north";
                    break;
                case 1:
                    direction = "east";
                    break;
                case 2:
                    direction = "south";
                    break;
                case 3:
                    direction = "west";
                    break;
                default:
                    direction = "ugh";
                    break;

            }
            System.out.printf("\tSTATE row: %d, col: %d, facing direction: %s", s.row, s.col, direction);
            System.out.println();
        }
    }


    public int getSolLen() {
        return solLen;
    }

    public int getTurns() {
        return turns;
    }

    public List<State> getSol() {
        return sol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution testSol = (Solution) o;
        if( sol.equals(testSol.sol)){
            return true;
        }
        return false;

    }

    public boolean meetReq(DifficultyLevel level){
        if (!(level.getSolLen() - 10 < solLen && solLen < level.getSolLen() + 10)){
            return false;
        }
        if (!(level.getNumTurns() - 5 < turns && turns < level.getNumTurns() + 5)){
            return false;
        }

        return true;
    }

    public void printMetrics(){
        System.out.printf("solLen: %d turns: %d\n", solLen, turns);
    }
}
