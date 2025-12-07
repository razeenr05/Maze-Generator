# Maze Generator and Solver
Console based maze generator and solver made with Java that draws the grid with simple ASCII characters. Builds a random perfect maze with Prim-style carving and finds a route using depth first search. This project was created for CS2336.

## Design choices
- **Implicit grid graph:** Two boolean wall arrays (`horizontalWalls`, `verticalWalls`) let me check openings in O(1) time without an adjacency matrix. Neighbors are derived from open walls at runtime instead of storing a full adjacency list.
- **Prim-style generation:** Start from a random cell, keep a frontier of neighbors, punch through a random visited neighbor, and repeat. This guarantees a perfect maze that has one path between any two cells.
- **Iterative DFS solver:** A stack, a visited set, and a parent map keep memory low while still making sure a path exists. Parents reconstruct the route once the end is found.
- **ASCII visualization:** Simple characters (`+ - |`) for walls and `S`/`E`/`*` for start, end, and solution path so the output is readable in any terminal.

## Files
- `Cell.java` stores row/column coordinates and equality logic so cells can be used safely in sets and maps.
- `Maze.java` holds dimensions, walls, generation, and neighbor lookups.
- `MazeSolver.java` walks the maze with DFS and returns the solved path.
- `Visualization.java` prints the maze and its solved path.
- `Main.java` handles user input, generation, solving, and display.

## Run it
```bash
javac Cell.java Maze.java MazeSolver.java Visualization.java Main.java
java Main
```
