public class Cell {
    private int row;
    private int column;
    
    // coordinate value object for maze cells
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    @Override
    public boolean equals(Object obj) {
        // value equality
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return row == cell.row && column == cell.column;
    }
    
    @Override
    public int hashCode() {
        // pair hashing
        return 31 * row + column;
    }
    
    @Override
    public String toString() {
        // debugger
        return "(" + row + ", " + column + ")";
    }
}
