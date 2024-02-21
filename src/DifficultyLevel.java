public enum DifficultyLevel {
    EASY("Easy Peasy", 8, 10, 40, 2),
    MEDIUM("Mid", 12, 10, 55, 5),
    HARD("God Mode", 25, 15, 90, 10);

    private final String name;
    private final int size;
    private final int numTurns;
    private final int solLen;
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
