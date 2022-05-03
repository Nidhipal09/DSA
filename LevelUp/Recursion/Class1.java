public class Class1 {

    public static void main(String[] args) {
        // System.out.println(mazePaths1(0,0,2,2,""));

        // int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } }; // move
        // String[] dirS = { "h", "d", "v" }; // moveName

        // System.out.println(mazePaths2(0, 0, 2, 2, "", dir, dirS));

        // System.out.println(mazePathsJumps(0, 0, 2, 2, "", dir, dirS));

        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        String[] dirS = { "u", "d1", "r", "d2", "d", "d3", "l", "d4" };
        // String[] dirS = {"u", "ne", "r", "se", "d", "sw", "l", "nw"};
        // boolean[][] visited = new boolean[3][3];
        // System.out.println(floodFill(0, 0, 1, 1, "", dir, dirS, visited));
        // System.out.println(floodFillWithJumps(0, 0, 2, 2, "", dir, dirS, visited));

        int[][] matrix = new int[3][3];
        System.out.println(floodFillShortestPath(0, 0, 2, 2, matrix, dir, dirS).path);
        // System.out.println(floodFillLongestPath(0, 0, 2, 2, matrix, dir, dirS).path);
    }

    public static int mazePaths1(int r, int c, int er, int ec, String psf) {
        if (r == er && c == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;

        if (r + 1 <= er)
            count += mazePaths1(r + 1, c, er, ec, psf + "v");
        if (c + 1 <= ec)
            count += mazePaths1(r, c + 1, er, ec, psf + "h");
        if (r + 1 <= er && c + 1 <= ec)
            count += mazePaths1(r + 1, c + 1, er, ec, psf + "d");

        return count;
    }

    public static int mazePaths2(int r, int c, int er, int ec, String psf, int[][] dir, String[] dirS) {
        if (r == er && c == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr <= er && nc <= ec)
                count += mazePaths2(nr, nc, er, ec, psf + dirS[d], dir, dirS);
        }

        return count;
    }

    public static int mazePathsJumps(int r, int c, int er, int ec, String psf, int[][] dir, String[] dirS) {
        if (r == er && c == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            for (int j = 1; j <= Math.max(er, ec); j++) { // or radius
                int nr = r + j * dir[d][0];
                int nc = c + j * dir[d][1];

                if (nr <= er && nc <= ec)
                    count += mazePathsJumps(nr, nc, er, ec, psf + dirS[d] + j, dir, dirS);
            }

        }

        return count;
    }

    public static int floodFill(int r, int c, int er, int ec, String psf, int[][] dir, String[] dirS,
            boolean[][] visited) {
        if (r == er && c == ec) {
            System.out.println(psf);
            return 1;
        }

        visited[r][c] = true;
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr >= 0 && nc >= 0 && nr <= er && nc <= ec && visited[nr][nc] == false)
                count += floodFill(nr, nc, er, ec, psf + dirS[d], dir, dirS, visited);
        }
        visited[r][c] = false;

        return count;
    }

    public static int floodFillWithJumps(int r, int c, int er, int ec, String psf, int[][] dir, String[] dirS,
            boolean[][] visited) {
        if (r == er && c == ec) {
            System.out.println(psf);
            return 1;
        }

        visited[r][c] = true;
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            for (int j = 1; j <= Math.max(er, ec); j++) { // or radius
                int nr = r + j * dir[d][0];
                int nc = c + j * dir[d][1];

                if (nr >= 0 && nc >= 0 && nr <= er && nc <= ec && visited[nr][nc] == false)
                    count += floodFillWithJumps(nr, nc, er, ec, psf + dirS[d] + j, dir, dirS, visited);
            }
        }
        visited[r][c] = false;

        return count;
    }

    public static class PairSP { // shortest path
        int len = (int) 1e9;  // 10 to the power 9 
        String path = "";
    }

    public static PairSP floodFillShortestPath(int r, int c, int er, int ec, int[][] matrix, int[][] dir,
            String[] dirS) { 
        if (r == er && c == ec) {
            PairSP bans = new PairSP();
            bans.len = 0;
            return bans;
        }

        matrix[r][c] = 1;
        PairSP ans = new PairSP();

        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr >= 0 && nc >= 0 && nr <= er && nc <= ec && matrix[nr][nc] == 0) {
                PairSP retSP = floodFillShortestPath(nr, nc, er, ec, matrix, dir, dirS);
                if (retSP.len != (int)1e9 && retSP.len + 1 < ans.len) { // don't initialize len to Integer.MAX_VALUE;
                    ans.len = retSP.len + 1;
                    ans.path = dirS[d] + retSP.path;
                }
            }
        }
        matrix[r][c] = 0;

        return ans;
    }

    public static class PairLP { // shortest path
        int len = -(int) 1e9;
        String path = "";
    }

    public static PairLP floodFillLongestPath(int r, int c, int er, int ec, int[][] matrix, int[][] dir,
            String[] dirS) { 
        if (r == er && c == ec) {
            PairLP bans = new PairLP();
            bans.len = 0;
            return bans;
        }

        matrix[r][c] = 1;
        PairLP ans = new PairLP();

        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr >= 0 && nc >= 0 && nr <= er && nc <= ec && matrix[nr][nc] == 0) {
                PairLP retSP = floodFillLongestPath(nr, nc, er, ec, matrix, dir, dirS);
                if (retSP.len != -(int) 1e9 && retSP.len + 1 > ans.len) {
                    ans.len = retSP.len + 1;
                    ans.path = dirS[d] + retSP.path;
                }
            }
        }
        matrix[r][c] = 0;

        return ans;
    }

}