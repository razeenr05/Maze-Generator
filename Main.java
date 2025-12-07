import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        
        // ask for maze size
        System.out.print("Please enter the maze dimensions: ");
        String sizeLine = inputScanner.nextLine();
        String[] dimensions = sizeLine.split(",");
        
        int rows = Integer.parseInt(dimensions[0].trim());
        int cols = Integer.parseInt(dimensions[1].trim());
        
        System.out.println("\nGenerated Maze:");
        
        // build a maze with the requested size
        Maze maze = new Maze(rows, cols);
        maze.generateMaze();
        
        // print the maze to the console
        Visualization.displayMaze(maze);
        
        System.out.println("\nMaze Solver:");
        
        // run depth first search to find a path
        MazeSolver solver = new MazeSolver(maze);
        List<Cell> pathCells = solver.solveDFS();
        
        // show the path if we found one
        if (pathCells.isEmpty()) {
            System.out.println("No path found!");
        } else {
            Visualization.displaySolution(maze, pathCells);
            System.out.println("\nPath length: " + pathCells.size() + " cells");
        }
        
        inputScanner.close();
    }
}
