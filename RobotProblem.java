/*
 * RobotProblem.java
 * Program to calculate the minimum number of robots needed to explore and area
 * given a map of that area
 * Author: Carlos Cruz.
 * Last modification: 20/1/2016
 */

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class RobotProblem {

    /*
     * countRobots.
     * Calculates the minimum number of robots needed to explore an area.
     * Receives: 
     * - The map of the area that is being explored.
     * - The robot that will be exploring the area.
     * Returns:
     * - Number of robots needed.
     */
    public static Map countRobots(Map map, Robot r) {
        
        Cell c;
        int num_robots = 0;
        Queue<Cell> queue = new LinkedList<Cell>();

        for(int i = 0; i < map.n; i++) {
            for(int j = 0; j < map.m; j++) {
                if(map.getMap(i, j) <= num_robots) {
                    continue;
                } else {
                    num_robots++;
                    map.setMap(i, j, num_robots);
                    queue = r.move(queue, map, new Cell(i, j));
                    while(queue.peek() != null) {
                        c = queue.poll();
			if(c.unreachable(map.n, map.m) || map.getMap(c.getX(), c.getY()) <= num_robots) {
			    continue;
			} else {
			    map.setMap(c.getX(), c.getY(), num_robots);
			    queue = r.move(queue, map, c);
			}
		    }
                }
            }
        }
	map.setRobotsNeeded(num_robots);
        return map;
    }    

    // REVISAR PEO CON LAS CASILLAS QUE ESTA DEVOLVIENDO EL MÉTODO DE GETMOVEMENTS
    public static boolean[] canBeExploredEfficiently(Map map, Robot r) {

	Cell begin, current;
	Queue<Cell> queue = new LinkedList<Cell>();
	Stack<Cell> stack = new Stack<Cell>();
	boolean[] found = new boolean[map.getRobotsNeeded()];

	for(int i = 0; i < map.getRobotsNeeded(); i++) {
	    found[i] = false;
	}

	outerloop:
	for(int i = 0; i < map.getRobotsNeeded(); i++) {
	    for(int j = 0; j < map.n; j++) {
		for(int k = 0; k < map.m; k++) {
		    int[][] newMap = map.copyMap();
		    begin = new Cell(j, k);
		    current = begin.clone();
		    Cell[] movements = r.getMovements(current);
		    for(int l = 0; l < movements.length; l++) {
			stack.push(movements[l]);
		    }
		    // queue = r.move(queue, map, current);
		    // while(queue.peek() != null) {
		    // 	Cell c = queue.poll();
		    // 	System.out.println(c.toString());
		    // 	stack.push(c);
		    // }
		    while(!stack.isEmpty()) {
			current = stack.pop();
			System.out.println(current.toString());
			switch(newMap[current.getX()][current.getY()]) {
			case -1:
			    continue;
			case 0:
			    found[i] = checkComponent(newMap, map.getRobotsNeeded(), i);
			    if(found[i]) continue outerloop;
			    continue;
			default:
			    newMap[current.getX()][current.getY()] = 0;
			    // queue = r.move(queue, map, current);
			    movements = r.getMovements(current);
			    for(int l = 0; l < movements.length; l++) {
				stack.push(movements[l]);
			    }
			    // while(queue.peek() != null) {
			    // 	stack.push(queue.poll());
			    // }
			}
			found[i] = checkComponent(newMap, map.getRobotsNeeded(), i);
		    }
		}
	    }
	}
	return found;
    }

    public static boolean checkComponent(int[][] map, int robotsNeeded, int component) {

	boolean complete = true;
	for(int i = 0; i < robotsNeeded && complete; i++) {
	    for(int j = 0; j < map.length && complete; j++) {
		for(int k = 0; k < map[0].length && complete; k++) {
		    complete = (map[j][k] != component);
		}
	    }
	}
	return complete;
    }
    
    public static void main(String args[]) {
                
        Map map;
        int result;
        Robot robot;
        boolean found[];
        System.out.println("Robot's problem");
        map = new Map(Reader.read(args[0]));
	switch(Integer.parseInt(args[1])) {
	case 1:
	    robot = new RobotType1();
	    break;
	case 2:
	    robot = new RobotType2();
	    break;
	default:
	    robot = new RobotType1();
	}
        map = countRobots(map, robot);
        System.out.println("The number of robots needed is: " + map.getRobotsNeeded());
	System.out.println("The biggest amount of steps needed are: " + map.getStepsToExplore());
	found = canBeExploredEfficiently(map, robot);
	for(int i = 0; i < found.length; i++) {
	    if(found[i]) {
		System.out.println("The " + i + "th partition can be explored efficiently.");
	    } else {
		System.out.println("The " + (i+1) + "th partition cannot be explored efficiently.");
	    }
	}
    }
}
