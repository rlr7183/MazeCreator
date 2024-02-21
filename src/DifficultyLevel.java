public enum DifficultyLevel {
    EASY("Easy Peasy", 6, 10, 45, 2),
    MEDIUM("Mid", 15, 20, 150, 5),
    HARD("God Mode", 25, 30, 200, 10);

    private final String name;
    private final int size; // Example attribute (e.g., number of enemies)
    private final int numTurns; // Another attribute (e.g., number of obstacles)
    private final int solLen; // Another attribute (e.g., speed multiplier)
    private final int DELen;


    DifficultyLevel(String name, int size, int numTurns, int solLen, int DELen) {
        this.name = name;
        this.size = size;
        this.numTurns = numTurns;
        this.solLen = solLen;
        this.DELen = DELen;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }


    public int getNumTurns() {
        return numTurns;
    }

    public int getSolLen() {
        return solLen;
    }

    public int getDELen() {
        return DELen;
    }
}
