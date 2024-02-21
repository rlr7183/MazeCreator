import java.util.Objects;

public class State {

    int row, col, dir;

    State(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return row == state.row && col == state.col && dir == state.dir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, dir);
    }


}
