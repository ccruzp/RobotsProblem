public class RobotType2 extends Robot {

    protected Cell[] getMovements(Cell current) {

	Cell[] movements = new Cell[8];
	movements[0] = new Cell(current.getX()-1, current.getY()-1);
	movements[1] = new Cell(current.getX()-1, current.getY());
	movements[2] = new Cell(current.getX()-1, current.getY()+1);
	movements[3] = new Cell(current.getX(), current.getY()-1);
	movements[4] = new Cell(current.getX(), current.getY()+1);
	movements[5] = new Cell(current.getX()+1, current.getY()-1);
	movements[6] = new Cell(current.getX()+1, current.getY());
	movements[7] = new Cell(current.getX()+1, current.getY()+1);
	return movements;
    }
}
