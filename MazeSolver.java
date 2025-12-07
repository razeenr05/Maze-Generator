import java.util.*;

public class MazeSolver {
    private Maze maze;
    
    public MazeSolver(Maze maze) {
        this.maze = maze;
    }
    
    public List<Cell> solveDFS() {
        Set<Cell> seenCells = new HashSet<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Stack<Cell> cellStack = new Stack<>();
        
        // start walking from the entrance and aim for the exit
        Cell startCell = maze.getStart();
        Cell endCell = maze.getEnd();
        
        cellStack.push(startCell);
        seenCells.add(startCell);
        
        while (!cellStack.isEmpty()) {
            Cell currentCell = cellStack.pop();
            
            // stop early once we hit the goal
            if (currentCell.equals(endCell)) {
                return reconstructPath(cameFrom, startCell, endCell);
            }
            
            // explore every open neighbor we have not seen yet
            for (Cell nextCell : maze.getNeighbors(currentCell)) {
                if (!seenCells.contains(nextCell)) {
                    seenCells.add(nextCell);
                    cameFrom.put(nextCell, currentCell);
                    cellStack.push(nextCell);
                }
            }
        }
        
        // no path found
        return new ArrayList<>();
    }
    
    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell startCell, Cell endCell) {
        List<Cell> path = new ArrayList<>();
        Cell walker = endCell;
        
        while (walker != null && !walker.equals(startCell)) {
            path.add(walker);
            walker = cameFrom.get(walker);
        }
        
        path.add(startCell);
        Collections.reverse(path);
        return path;
    }
}
