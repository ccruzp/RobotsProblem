public class Map {

    public int n;
    public int m;
    private int[][] map;
    private int robotsNeeded;
    
    public Map(int[][] map) {
	this.n = map.length;
	this.m = map[0].length;
	this.map = map;
    }

    public int getMap(int x, int y) {
	return map[x][y];
    }
    
    public boolean setMap(int x, int y, int value) {
	map[x][y] = value;
	return true;
    }

    public int getStepsToExplore() {
	int max;
    	int[] count = new int[robotsNeeded];
	for(int i = 0; i < n; i++) {
	    for(int j = 0; j < m; j++) {
		if(map[i][j] != -1) {
		    count[map[i][j]-1]++;
		}
	    }
	}
	max = count[0];
	for(int i = 0; i < count.length; i++) {
	    if(max < count[i]) {
		max = count[i];
	    }
	}
	return max;
    }

    public int getRobotsNeeded() {
	return robotsNeeded;
    }
    
    public boolean setRobotsNeeded(int num_robots) {
	robotsNeeded = num_robots;
	return true;
    }

    // public boolean canBeExploredEfficiently() {

    // 	int[][] tour = map;
	    
    // }

    public int[][] copyMap() {
	int [][] newMap = new int[map.length][];
	for(int i = 0; i < map.length; i++) {
	    newMap[i] = new int[m];
	    System.arraycopy(map[i], 0, newMap[i], 0, m);
	}
	return newMap;
    }
}
    
