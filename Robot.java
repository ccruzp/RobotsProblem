/*
 * Class Robot
 * Robot that will be exploring a given area.
 */

import java.util.Queue;

abstract class Robot {
    
    abstract Cell[] getMovements(Cell current);
    
    public Queue<Cell> move(Queue<Cell> queue, Map map, Cell current) {

	Cell[] movements = getMovements(current);
	System.out.println("Father: " + current.toString());
	for(int i = 0; i < movements.length; i++) {
	    if(!movements[i].unreachable(map.n, map.m)) {
		if(map.getMap(movements[i].getX(), movements[i].getY()) > map.getMap(current.getX(), current.getY())) {
		    System.out.println("Child " + i + " " +movements[i].toString());
		    queue.add(movements[i]);
		}
	    }
	}
	return queue;
    }
    
}
