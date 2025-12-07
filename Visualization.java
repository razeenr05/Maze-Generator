import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Visualization {
    
    public static void displayMaze(Maze maze) {
        int rowCount = maze.getRows();
        int colCount = maze.getCols();
        boolean[][] hWalls = maze.getHorizontalWalls();
        boolean[][] vWalls = maze.getVerticalWalls();
        Cell start = maze.getStart();
        Cell end = maze.getEnd();
        
        // draw the top border
        for (int c = 0; c < colCount; c++) {
            System.out.print("+---");
        }
        System.out.println("+");
        
        // print each row of cells
        for (int r = 0; r < rowCount; r++) {
            // print cells and their right walls
            System.out.print("|");
            for (int c = 0; c < colCount; c++) {
                // mark start, end, or empty space
                if (r == start.getRow() && c == start.getColumn()) {
                    System.out.print(" S ");
                } else if (r == end.getRow() && c == end.getColumn()) {
                    System.out.print(" E ");
                } else {
                    System.out.print("   ");
                }
                
                // choose between a wall and an opening
                if (vWalls[r][c]) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            // underline each cell with horizontal walls
            for (int c = 0; c < colCount; c++) {
                System.out.print("+");
                if (hWalls[r][c]) {
                    System.out.print("---");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("+");
        }
    }
    
    public static void displaySolution(Maze maze, List<Cell> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No solution found!");
            return;
        }
        
        int rowCount = maze.getRows();
        int colCount = maze.getCols();
        boolean[][] hWalls = maze.getHorizontalWalls();
        boolean[][] vWalls = maze.getVerticalWalls();
        Cell start = maze.getStart();
        Cell end = maze.getEnd();
        
        // keep a set for quick path checks
        Set<Cell> pathCells = new HashSet<>(path);
        
        // draw the top border
        for (int c = 0; c < colCount; c++) {
            System.out.print("+---");
        }
        System.out.println("+");
        
        // print each row of cells
        for (int r = 0; r < rowCount; r++) {
            // print cells and their right walls
            System.out.print("|");
            for (int c = 0; c < colCount; c++) {
                Cell currentCell = new Cell(r, c);
                
                // mark start, end, path, or empty space
                if (r == start.getRow() && c == start.getColumn()) {
                    System.out.print(" S ");
                } else if (r == end.getRow() && c == end.getColumn()) {
                    System.out.print(" E ");
                } else if (pathCells.contains(currentCell)) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
                
                // choose between a wall and an opening
                if (vWalls[r][c]) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            // underline each cell with horizontal walls
            for (int c = 0; c < colCount; c++) {
                System.out.print("+");
                if (hWalls[r][c]) {
                    System.out.print("---");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("+");
        }
    }
}
