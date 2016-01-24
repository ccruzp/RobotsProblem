/*
 * Class Cell.
 * Represents each space in the map
 */
public class Cell {
    private int x;
    private int y;
    
    public Cell(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public boolean unreachable(int n, int m) {
	return (this.x >= n || this.x < 0 || this.y >= m || this.y < 0);
    }

    public Cell clone() {
	return new Cell(x, y);
    }

    public boolean equals(Cell c) {
	return (c.getX() == this.x && c.getY() == this.y);
    }

    public String toString() {
	return "Cell:\nX: " + x + "\nY: " + y + "\n";
    }
	
}
