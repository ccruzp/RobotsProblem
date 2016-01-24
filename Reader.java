/* 
 * Class Reader
 * Class used to read the map file, preprocess it and create the representation
 * of the map that will be used during the execution of the program.
 */
import java.io.File;
import java.util.Scanner;

public class Reader {
    
    public static int[] preprocess(String m) {
	
	char[] map = m.toCharArray();
	int n = map.length;
	int[] p = new int[n];
	for (int i = 0; i < n; i++) {
	    if (map[i] == 'X') {
		p[i] = Integer.MAX_VALUE;
	    } else if(map[i] == '#') {
		p[i] = -1;
	    } else {
		System.out.println("Problem in file");
		System.exit(1);
	    }
	}
	return p;
    }
    
    public static int[][] read(String file) {

	int n, m;
	int[][] map = new int[1][1];
	File f = new File(file);
	try {
	    Scanner scanner = new Scanner(f);
	    n = scanner.nextInt();
	    m = scanner.nextInt();
	    map = new int[n][m];
	    scanner.nextLine();
	    for(int i = 0; i < n; i++) {
	    	map[i] = Reader.preprocess(scanner.nextLine());
	    }
	    scanner.close();
	} catch (Exception e) {
	    System.out.println("Error reading.");
	    e.printStackTrace();
	}
	    
	return map;
    }
}

