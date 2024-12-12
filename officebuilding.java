import java.util.*;
public class  officebuilding {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int treeR = s.nextInt();
        int treeC = s.nextInt();
        int[][] trees = new int[treeR][treeC];
        int totalAges = 0;

        for(int i = 0; i < treeR; i++) {
            for (int j = 0; j < treeC; j++){
                //System.out.println("ok"); 
                trees[i][j] = s.nextInt(); 
                totalAges += trees[i][j];
            }
        }
        //System.out.println("Ages: " + totalAges);

        int r2 = s.nextInt();
        int c2 = s.nextInt();
        //System.out.println(r2 + "," + c2);

        char[][] building = new char[r2][c2];
        for(int i = 0; i < r2; i++) {
            String word = s.next(); 
            for (int j = 0; j < c2; j++){
                building[i][j] = word.charAt(j); 
            }
        }
        int currMax = -1; 
        int[] values = new int[4]; 
        for(int i = 0; i < 4; i++) {
            char[][] temp = rotate(trees, building, totalAges);
            currMax = checkBuild(trees, temp, 0, 0, currMax, totalAges);
        }
        System.out.println(currMax); 
    }
    //rotate building, decrementing totalAges by the tree ages lost in this rotation
    public static char[][] rotate(int[][] trees, char[][] building, int totalAges) {
        char[][] temp = building;
        for(int r = 0; r < building.length; r++) {
            int len = building[r].length;
            for (int c = 0; c < len; c++) {
                ///System.out.println(r + "." + c);
                temp[r][c] = building[len - 1 - c][r];
            }
        }
        return temp;
    }

    public static int checkBuild(int[][] trees, char[][] building, int startR, int startC, int currMax, int totalAges) {
        for(int i=startR; i < startR+building.length; i++) {
            for (int j = startC; j < startC + building[i].length; j++) {
                int age = getNewAge(trees, building, startR, startC, totalAges);
                if (age > currMax) {
                    currMax = age; 
                }
            }
        }
        return currMax; 
    }

    public static int getNewAge(int[][] trees, char[][] building, int startR, int startC, int totalAges) {
        int col_len = startC + building[0].length;
        int row_len = startR + building.length;
        for(int r = startR; r < row_len; r++) {
            ///System.out.println(r + "." + c);
            for (int c = startC; c < col_len; c++) {
                char hrm = building[col_len - 1 - c][r];
                if (hrm == '#') {
                    //building exists here. remove this tree
                    totalAges -= trees[r][c]; 
                }
            }
        }
        return totalAges;
    }
}
