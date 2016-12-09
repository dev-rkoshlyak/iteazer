
import java.util.*;
/**
 *
 * @author WslF@ITeazer
 */
public class Solution {

    void solve() {
		Scanner in = new Scanner( System.in );
        int n = in.nextInt();
        
        int[] distance = new int[4];
        for (int i = 0; i < n; i++) {
            char c = in.next().charAt(0);
            int d = in.nextInt();
            int j = -1;
            switch (c) {
                case 'N':
                    j = 0;
                    break;
                case 'E':
                    j = 1;
                    break;
                case 'W':
                    j = 2;
                    break;
                default:
                    j = 3;
            }
            
            distance[j] += d;
        }
        
        for (int i = 0; i < distance.length; i++) {
            System.out.println(distance[i]);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solve();
    }
}
