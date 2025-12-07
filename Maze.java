import java.util.*;

public class Maze {
    private int rows;
    private int cols;
    private boolean[][] horizontalWalls; // walls below each cell
    private boolean[][] verticalWalls;   // walls to the right of each cell
    private Cell start;
    private Cell end;
    
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        
        // set up arrays for walls
        horizontalWalls = new boolean[rows][cols];
        verticalWalls = new boolean[rows][cols];
        
        // start with every wall in place
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                horizontalWalls[i][j] = true;
                verticalWalls[i][j] = true;
            }
        }
        
        // pick default start/end cells
        start = new Cell(0, 0);
        end = new Cell(rows - 1, cols - 1);
    }
    
    public void generateMaze() {
        Random random = new Random();
        boolean[][] carved = new boolean[rows][cols];
        List<Cell> frontierCells = new ArrayList<>();
        
        // begin carving from a random cell
        Cell seedCell = new Cell(random.nextInt(rows), random.nextInt(cols));
        carved[seedCell.getRow()][seedCell.getColumn()] = true;
        
        // queue up the neighboring cells we can grow into
        addNeighborsToFrontier(seedCell, carved, frontierCells);
        
        // grow the maze using randomized prims
        while (!frontierCells.isEmpty()) {
            // grab a random frontier cell to expand
            Cell frontierCell = frontierCells.remove(random.nextInt(frontierCells.size()));
            
            // find which of its neighbors are already carved
            List<Cell> carvedNeighbors = getMazeNeighbors(frontierCell, carved);
            
            if (!carvedNeighbors.isEmpty()) {
                // knock down a wall toward a random carved neighbor
                Cell carvedNeighbor = carvedNeighbors.get(random.nextInt(carvedNeighbors.size()));
                removeWall(frontierCell, carvedNeighbor);
                
                // mark it carved and add its untouched neighbors
                carved[frontierCell.getRow()][frontierCell.getColumn()] = true;
                addNeighborsToFrontier(frontierCell, carved, frontierCells);
            }
        }
    }
    
    private void addNeighborsToFrontier(Cell cell, boolean[][] carved, List<Cell> frontierCells) {
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] offset : offsets) {
            int nextRow = cell.getRow() + offset[0];
            int nextCol = cell.getColumn() + offset[1];
            
            if (isValid(nextRow, nextCol) && !carved[nextRow][nextCol]) {
                Cell candidate = new Cell(nextRow, nextCol);
                if (!frontierCells.contains(candidate)) {
                    frontierCells.add(candidate);
                }
            }
        }
    }
    
    private List<Cell> getMazeNeighbors(Cell cell, boolean[][] carved) {
        List<Cell> carvedNeighbors = new ArrayList<>();
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] offset : offsets) {
            int nextRow = cell.getRow() + offset[0];
            int nextCol = cell.getColumn() + offset[1];
            
            if (isValid(nextRow, nextCol) && carved[nextRow][nextCol]) {
                carvedNeighbors.add(new Cell(nextRow, nextCol));
            }
        }
        
        return carvedNeighbors;
    }
    
    private void removeWall(Cell firstCell, Cell secondCell) {
        int rowA = firstCell.getRow(), colA = firstCell.getColumn();
        int rowB = secondCell.getRow(), colB = secondCell.getColumn();
        
        if (rowA == rowB) { // same row, remove a vertical wall
            if (colA < colB) {
                verticalWalls[rowA][colA] = false;
            } else {
                verticalWalls[rowB][colB] = false;
            }
        } else { // same column, remove a horizontal wall
            if (rowA < rowB) {
                horizontalWalls[rowA][colA] = false;
            } else {
                horizontalWalls[rowB][colB] = false;
            }
        }
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> options = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getColumn();
        
        // move up if there is no ceiling
        if (row > 0 && !horizontalWalls[row - 1][col]) {
            options.add(new Cell(row - 1, col));
        }
        
        // move down if there is no floor
        if (row < rows - 1 && !horizontalWalls[row][col]) {
            options.add(new Cell(row + 1, col));
        }
        
        // move left if the left wall is open
        if (col > 0 && !verticalWalls[row][col - 1]) {
            options.add(new Cell(row, col - 1));
        }
        
        // move right if the right wall is open
        if (col < cols - 1 && !verticalWalls[row][col]) {
            options.add(new Cell(row, col + 1));
        }
        
        return options;
    }
    
    // accessor methods
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public Cell getStart() {
        return start;
    }
    
    public Cell getEnd() {
        return end;
    }
    
    public boolean[][] getHorizontalWalls() {
        return horizontalWalls;
    }
    
    public boolean[][] getVerticalWalls() {
        return verticalWalls;
    }
}
