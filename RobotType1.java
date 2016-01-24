public class RobotType1 extends Robot {

    public Cell[] getMovements(Cell current) {

	Cell[] movements = new Cell[4];
	movements[0] = 	new Cell(current.getX()-1, current.getY());
	movements[1] = new Cell(current.getX(), current.getY()-1);
	movements[2] = new Cell(current.getX(), current.getY()+1);
	movements[3] = new Cell(current.getX()+1, current.getY());
	return movements;

    }    
}
