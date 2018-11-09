package edu.touro.mco264;

class ConwayGameOfLife {

    private boolean[][] gridStateA;
    private boolean[][] gridStateB;
    private boolean[][] gridCurrentGen;
    private boolean stateA = true;
    private final boolean _ = false, X = true;

    public ConwayGameOfLife(String patternType) {

        switch (patternType) {
            case "Blinker":
            case "blinker":
                gridStateA = new boolean[][]{{_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, X, X, X, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _}};
                break;
            case "Beehive":
            case "beehive":
                gridStateA = new boolean[][]{{_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, _, X, X, _, _, _},
                                            {_, X, _, _, X, _, _},
                                            {_, _, X, X, _, _, _},
                                            {_, _, _, _, _, _, _}};
                break;
            case "Beacon":
            case "beacon":
                gridStateA = new boolean[][]{{_, _, _, _, _, _, _},
                                            {_, _, _, _, _, _, _},
                                            {_, X, X, _, _, _, _},
                                            {_, X, _, _, _, _, _},
                                            {_, _, _, _, X, _, _},
                                            {_, _, _, X, X, _, _},
                                            {_, _, _, _, _, _, _}};
                break;
            default:
                System.out.println("Invalid pattern type");
                break;
        }

        gridStateB = new boolean[gridStateA.length][gridStateA[0].length];
        gridCurrentGen = gridStateA;
    }

    public void evolveGrid() {

        int cellRow, cellCol, liveFieldMembers;

        for (cellRow = 1;
                cellRow < gridCurrentGen.length - 1; cellRow++) {
            for (cellCol = 1;
                    cellCol < gridCurrentGen[0].length - 1; cellCol++) {

                liveFieldMembers = getLiveFieldMembers(cellRow, cellCol);
                updateCurrentCellToNextGen(liveFieldMembers,
                        cellRow, cellCol);
            }
        }
        alternateGrid();
    }

    int getLiveFieldMembers(int cellRow, int cellCol) {

        int liveFieldMembers = 0;

        for (int fieldRow = cellRow - 1;
                fieldRow <= cellRow + 1; fieldRow++) {
            for (int fieldCol = cellCol - 1;
                    fieldCol <= cellCol + 1; fieldCol++) {
                if (gridCurrentGen[fieldRow][fieldCol]) {
                    liveFieldMembers++;
                }
            }
        }
        return liveFieldMembers;
    }

    void updateCurrentCellToNextGen(int liveFieldMembers,
            int cellRow, int cellCol) {

        boolean[][] gridNextGen = stateA ? gridStateB : gridStateA;

        if (liveFieldMembers == 3) {
            gridNextGen[cellRow][cellCol] = true;
        } else if (liveFieldMembers == 4) {
            gridNextGen[cellRow][cellCol]
                    = gridCurrentGen[cellRow][cellCol];
        } else {
            gridNextGen[cellRow][cellCol] = false;
        }
    }

    void alternateGrid() {
        stateA = !stateA;
        gridCurrentGen = stateA ? gridStateA : gridStateB;
    }

    @Override
    public String toString() {

        String gridString = "";
        int row, col;

        for (row = 0; row < gridCurrentGen.length; row++) {
            for (col = 0; col < gridCurrentGen[row].length; col++) {

                gridString += !gridCurrentGen[row][col] ? "□" : "■";
            }
            gridString += "\n";
        }
        return gridString;
    }
}
