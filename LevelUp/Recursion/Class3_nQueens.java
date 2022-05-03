public class Class3_nQueens {

    public static void main(String[] args) {
        // System.out.println(nQueens1dCombinations(new int[6], -1, 1, 3, ""));

        // System.out.println(nQueens1dPermutations(new int[6], 1, 3));

        // System.out.println(nQueens2dCombinations(new int[2][2], 0, -1, 1, 2, ""));

        // System.out.println(nQueens2dPermutations(new int[2][2], 1, 2));

        // System.out.println(nQueens2dAs1dCombinations(2, 2, -1, 1, 2, ""));

        // System.out.println(nQueens2dAs1dPermutations(new int[2][2], 2, 2, 1, 2));

        // System.out.println(nQueensCanKillCombinations(new int[4][4], 4, 0));

        System.out.println(nQueensCanKillPermutations(new int[4][4], 4, 4, 1, 4));
    }

    public static int nQueens1dCombinations(int[] boxes, int li, int qn, int tq, String qpsf) {
        if (qn == tq + 1) {
            System.out.println(qpsf);
            return 1;
        }

        int count = 0;
        for (int i = li + 1; i < boxes.length; i++) { // box index
            count += nQueens1dCombinations(boxes, i, qn + 1, tq, qpsf + "q" + qn + "b" + i + "  ");
        }

        return count;
    }

    public static int nQueens1dPermutations(int[] boxes, int qn, int tq) {
        if (qn == tq + 1) {
            for (int j = 0; j < boxes.length; j++) {
                if (boxes[j] > 0)
                    System.out.print("q" + boxes[j] + "b" + j + "  ");
            }
            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 0) {
                boxes[i] = qn;
                count += nQueens1dPermutations(boxes, qn + 1, tq);
                boxes[i] = 0;
            }
        }

        return count;
    }

    public static int nQueens2dCombinations(int[][] boxes, int lr, int lc, int qn, int tq, String psf) {
        if (qn == tq + 1) {
            System.out.println(psf);
            return 1;
        }

        int nr = 0, nc = 0;

        if (lc == boxes[0].length - 1) {
            nr = lr + 1;
            nc = 0;
        } else {
            nr = lr;
            nc = lc + 1;
        }

        int count = 0;
        for (int i = nr; i < boxes.length; i++) {
            for (int j = nc; j < boxes[0].length; j++) {
                count += nQueens2dCombinations(boxes, i, j, qn + 1, tq, psf + "[" + i + "," + j + "] ");
            }
        }

        return count;
    }

    public static int nQueens2dAs1dCombinations(int m, int n, int li, int qn, int tq, String psf) {
        if (qn == tq + 1) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int i = li + 1; i < m * n; i++) {
            int r = i / n;
            int c = i % n;
            count += nQueens2dAs1dCombinations(m, n, i, qn + 1, tq, psf + "[" + r + "," + c + "] ");
        }

        return count;
    }

    public static int nQueens2dPermutations(int[][] boxes, int qn, int tq) {
        if (qn == tq + 1) {
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j < boxes[0].length; j++) {
                    if (boxes[i][j] != 0) {
                        System.out.print("q" + boxes[i][j] + "[" + i + "," + j + "] ");
                    }
                }
            }
            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                if (boxes[i][j] == 0) {
                    boxes[i][j] = qn;
                    count += nQueens2dPermutations(boxes, qn + 1, tq);
                    boxes[i][j] = 0;
                }
            }
        }

        return count;
    }

    public static int nQueens2dAs1dPermutations(int[][] boxes, int m, int n, int qn, int tq) {
        if (qn == tq + 1) {
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (boxes[row][col] != 0)
                        System.out.print("q" + boxes[row][col] + "[" + row + "," + col + "] ");
                }
            }
            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < m * n; i++) {
            int r = i / n;
            int c = i % n;
            if (boxes[r][c] == 0) {
                boxes[r][c] = qn;
                count += nQueens2dAs1dPermutations(boxes, m, n, qn + 1, tq);
                boxes[r][c] = 0;
            }
        }

        return count;
    }

    public static int nQueensCanKillCombinations(int[][] boxes, int tq, int r) {
        if (tq==0) {
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j < boxes[0].length; j++) {
                    if(boxes[i][j]!=0) System.out.print("["+i+","+j+"]");
                }
            }
            System.out.println();
            return 1;
        }

        int count=0;
        for(int c=0; c<boxes[0].length; c++){
            if(isQueenSafeUsingDir(boxes,r,c)){
                boxes[r][c]=1;
                count += nQueensCanKillCombinations(boxes, tq-1, r+1);
                boxes[r][c]=0;
            }
        }

        return count;
    }

    public static boolean isQueenSafeUsingDir(int[][] boxes, int r, int c) {
        int[][] dir = {{-1,0}, {-1,1}, {-1,-1}};
        int m=boxes.length;
        int n=boxes[0].length;
        for(int i=0; i<dir.length; i++){
            for(int rad=1; rad<=r; rad++){

                int row = r + rad*dir[i][0];
                int col = c + rad*dir[i][1];

                if(row>=0 && col>=0 && row<m && col<n) if(boxes[row][col]!=0) return false;
            }
        }
 
        return true;
    }

    public static boolean isQueenSafe(int[][] boxes, int r, int c) {
        for(int i=r-1; i>=0; i--){
            if(boxes[i][c]!=0) return false;
        }
        
        for(int i=r-1, j=c-1; i>=0 && j>=0; i--, j--){
            if(boxes[i][j]!=0) return false;
        }
        
        for(int i=r-1, j=c+1; i>=0 && j<boxes[0].length; i--, j++){
            if(boxes[i][j]!=0) return false;
        }
        
        return true;
    }
    
    public static boolean isQueenSafeUsingDirForPermutations(int[][] boxes, int r, int c) {
        int[][] dir = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
        int m=boxes.length;
        int n=boxes[0].length;
        for(int i=0; i<dir.length; i++){
            for(int rad=1; rad<=n; rad++){

                int row = r + rad*dir[i][0];
                int col = c + rad*dir[i][1];

                if(row>=0 && col>=0 && row<m && col<n) if(boxes[row][col]!=0) return false;
            }
        }
 
        return true;
    }
    

    public static int nQueensCanKillPermutations(int[][] boxes, int m, int n, int qn, int tq) {
        if (qn == tq+1) {
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j < boxes[0].length; j++) {
                    if(boxes[i][j]!=0) System.out.print("q"+boxes[i][j]+"["+i+","+j+"] ");
                }
            }
            System.out.println();
            return 1;
        }

        int count=0;
        for (int i = 0; i < m * n; i++) {
            int r = i / n;
            int c = i % n;
            if (boxes[r][c] == 0 && isQueenSafeUsingDirForPermutations(boxes, r, c)) {
                boxes[r][c] = qn;
                count += nQueensCanKillPermutations(boxes, m, n, qn + 1, tq);
                boxes[r][c] = 0;
            }
        }

        return count;
    }

    //leetcode - 51, 52



    
}