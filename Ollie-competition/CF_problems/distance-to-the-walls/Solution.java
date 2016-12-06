
import java.util.*;
/**
 *
 * @author WslF@ITeazer
 */
public class Solution {

    // maximum size (height & width) of maze
    final static int MaxN = 2002;
// impossibly high distance
    final static int infDist = 1000 * 1000 * 1000;
// number of lines in current maze
    int rowsNumber = 0;
// number of rows in current maze
    int columnsNum = 0;

// current maze. Do NOT modify it!!!
//  0    - road
// -1    - droid start position
// -2    - finish
// -3    - walls (non road)
    int[][] mazeInput = new int[MaxN][MaxN];

    int[][] distanceToWalls = new int[MaxN][MaxN];

    final static int ROAD = 0;
    final static int WALL = -3;


// read map from file to mazeInput & maze arrays
    private void read() {
        for (int i = 0; i < MaxN; i++) {
            for (int j = 0; j < MaxN; j++) {
                mazeInput[i][j] = WALL;
            }
        }

		Scanner in = new Scanner( System.in );
        rowsNumber = in.nextInt();
        columnsNum = in.nextInt();


		String s = in.nextLine();
        for (int i = 0; i < rowsNumber; i++) {
			s = in.nextLine();
            for (int j = 0; j < columnsNum; j++) {
				mazeInput[i+1][j+1] = s.charAt(j) == ' ' ? ROAD : WALL;
            }
        }
    }

    private void getCopyOfMaze(int[][] mazeCopy) {
        for (int i = 0; i < MaxN; i++) {
            for (int j = 0; j < MaxN; j++) {
                mazeCopy[i][j] = mazeInput[i][j];
            }
        }
    }


    void calcDistanceToWalls() {
        getCopyOfMaze(distanceToWalls);

        Queue<int[]> q = new LinkedList<int[]>();

        for (int i = 1; i <= rowsNumber; i++) {
            for (int j = 1; j <= columnsNum; j++) {
                if (distanceToWalls[i][j] == WALL) {
                    distanceToWalls[i][j] = 0;
                    q.add(new int[]{i, j});
                } else {
                    distanceToWalls[i][j] = infDist;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curRow = cur[0];
            int curColumn = cur[1];
            int nextVal = distanceToWalls[curRow][curColumn] + 1;

            if (curRow > 0) {
                if (distanceToWalls[curRow - 1][curColumn] > nextVal) {
                    distanceToWalls[curRow - 1][curColumn] = nextVal;
                    q.add(new int[]{curRow - 1, curColumn});
                }
            }
            if (curRow < rowsNumber) {
                if (distanceToWalls[curRow + 1][curColumn] > nextVal) {
                    distanceToWalls[curRow + 1][curColumn] = nextVal;
                    q.add(new int[]{curRow + 1, curColumn});
                }
            }
            if (curColumn > 0) {
                if (distanceToWalls[curRow][curColumn - 1] > nextVal) {
                    distanceToWalls[curRow][curColumn - 1] = nextVal;
                    q.add(new int[]{curRow, curColumn - 1});
                }
            }
            if (curColumn < columnsNum) {
                if (distanceToWalls[curRow][curColumn + 1] > nextVal) {
                    distanceToWalls[curRow][curColumn + 1] = nextVal;
                    q.add(new int[]{curRow, curColumn + 1});
                }
            }
        }

    }


    int solve() {

        read();
		calcDistanceToWalls();
		for (int i = 1; i <= rowsNumber; i++) {
			for (int j = 1; j <= columnsNum; j++) {
				System.out.print(distanceToWalls[i][j]+" ");
			}
			System.out.println();
		}

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solve();
    }
}
