import java.util.*;

public class Class1 {

    // in every dp problem - follow these steps

    // 1. faith
    // 2. tree diagram
    // 3. write recursion code

    // 4. recursion -> memoization

    // 5. observation -> identify the direction of tablation (dependency)

    // 6. memoization -> tablation

    // 7. optimization

    static void display1d(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    static void display2d(int[][] arr) {
        for (int[] row1 : arr) {
            display1d(row1);
        }
    }

    public static int fibo_memo(int n, int[] storage) {
        if (n <= 1)
            return storage[n] = n;
        if (storage[n] != 0)
            return storage[n];
        return storage[n] = fibo_memo(n - 1, storage) + fibo_memo(n - 2, storage);
    }

    public static int fibo_tab(int N) {
        int[] storage = new int[N + 1];

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                storage[n] = n;
                continue;
            }
            storage[n] = storage[n - 1] + storage[n - 2];
        }

        return storage[N];
    }

    public static int fibo_optimized(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    // 1137
    public int tribonacci(int n) {
        return tribonacci_opti(n);
    }

    public int tribonacci_recursive(int n) {
        if (n <= 2)
            return (n == 0 ? 0 : 1);
        return tribonacci_recursive(n - 1) + tribonacci_recursive(n - 2) + tribonacci_recursive(n - 3);
    }

    public int tribonacci_memo(int n, int[] storage) {
        if (n <= 2)
            return storage[n] = (n == 0 ? 0 : 1);
        if (storage[n] != 0)
            return storage[n];
        return storage[n] = tribonacci_memo(n - 1, storage) + tribonacci_memo(n - 2, storage)
                + tribonacci_memo(n - 3, storage);
    }

    public int tribonacci_tab(int N, int[] storage) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                storage[n] = (n == 0 ? 0 : 1);
                continue;
            }
            storage[n] = storage[n - 1] + storage[n - 2] + storage[n - 3];
        }

        return storage[N];
    }

    public int tribonacci_opti(int N) {
        int a = 0, b = 1, c = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }

        return a;
    }

    // 70
    public int climbStairs(int n) {
        return climbStairs_optimized(n);
    }

    public int climbStairs_recursive(int n) {
        if (n == 0)
            return 1;
        return (n - 1 >= 0 ? climbStairs_recursive(n - 1) : 0) + (n - 2 >= 0 ? climbStairs_recursive(n - 2) : 0);
    }

    public int climbStairs_memo(int n, int[] storage) {
        if (n <= 1)
            return storage[n] = 1;

        if (storage[n] != 0)
            return storage[n];

        return storage[n] = climbStairs_memo(n - 1, storage) + climbStairs_memo(n - 2, storage);
    }

    public int climbStairs_tab(int N, int[] storage) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                storage[n] = 1;
                continue;
            }
            storage[n] = storage[n - 1] + storage[n - 2];
        }

        return storage[N];
    }

    public int climbStairs_optimized(int N) {
        int a = 1, b = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    // 746
    public int minCostClimbingStairs_recursive(int n, int[] cost) {
        if (n <= 1)
            return cost[n];

        int fcallRes = minCostClimbingStairs_recursive(n - 1, cost);
        int scallRes = minCostClimbingStairs_recursive(n - 2, cost);

        return (n == cost.length ? 0 : cost[n]) + Math.min(fcallRes, scallRes);
    }

    public int minCostClimbingStairs_memo(int n, int[] cost, int[] storage) {
        if (n <= 1)
            return storage[n] = cost[n];
        if (storage[n] != 0)
            return storage[n];

        int fcallRes = minCostClimbingStairs_memo(n - 1, cost, storage);
        int scallRes = minCostClimbingStairs_memo(n - 2, cost, storage);

        return storage[n] = (n == cost.length ? 0 : cost[n]) + Math.min(fcallRes, scallRes);
    }

    public int minCostClimbingStairs_tab(int N, int[] cost, int[] storage) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                storage[n] = cost[n];
                continue;
            }

            storage[n] = (n == cost.length ? 0 : cost[n]) + Math.min(storage[n - 1], storage[n - 2]);
        }

        return storage[N];
    }

    // friends pairing problem
    public long countFriendsPairings(int n) {
        return countFriendsPairings_optimized(n);
    }

    public long countFriendsPairings_recursive(int n) {
        if (n <= 1)
            return 1;
        long res = countFriendsPairings_recursive(n - 1) + countFriendsPairings_recursive(n - 2) * (n - 1);
        return res % Math.round(1e9 + 7);
    }

    public long countFriendsPairings_memo(int n, long[] storage) {
        if (n <= 1)
            return storage[n] = 1;
        if (storage[n] != 0)
            return storage[n];
        long res = countFriendsPairings_memo(n - 1, storage) + countFriendsPairings_memo(n - 2, storage) * (n - 1);
        return storage[n] = res % Math.round(1e9 + 7);
    }

    public long countFriendsPairings_tab(int N, long[] storage) {
        long mod = Math.round(1e9 + 7); // double to int
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                storage[n] = 1;
                continue;
            }
            storage[n] = (storage[n - 1] + storage[n - 2] * (n - 1)) % mod; // becoz ans can be very very large
        }
        return storage[N];

    }

    public long countFriendsPairings_optimized(int N) {
        long mod = Math.round(1e9 + 7);
        long a = 1, b = 1;
        for (int n = 0; n < N; n++) {
            long next = (b + a * ((n + 2) - 1)) % mod;
            a = b;
            b = next;
        }
        return a;

    }

    static int minMovesMazePaths_memo(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return dp[0][0] = 0;
        }
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        int min = Integer.MAX_VALUE;
        if (n - 1 >= 0)
            min = Math.min(minMovesMazePaths_memo(m, n - 1, dp), min);
        if (m - 1 >= 0)
            min = Math.min(minMovesMazePaths_memo(m - 1, n, dp), min);
        if (m - 1 >= 0 && n - 1 >= 0)
            min = Math.min(minMovesMazePaths_memo(m - 1, n - 1, dp), min);

        return dp[m][n] = min + 1;
    }

    static int minMovesMazePathsWithVariableJumps_memo(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return dp[0][0] = 0;
        }
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m, n - j, dp), min);
        }

        for (int j = 1; j <= m; j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m - j, n, dp), min);
        }

        for (int j = 1; j <= Math.min(m, n); j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m - j, n - j, dp), min);
        }

        return dp[m][n] = min + 1;
    }

    // with dir array- maze total paths
    // sir min cost climbing stairs ko optimized two pointer way se ek baar krado-
    // https://leetcode.com/problems/min-cost-climbing-stairs/

    // 62 - using dir

    public int uniquePaths(int m, int n) {
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        return uniquePaths_tab(dir, new int[m][n]);
    }

    public int uniquePaths_memo(int m, int n, int[][] dir, int[][] ans) {
        if (m == ans.length - 1 && n == ans[0].length - 1)
            return ans[m][n] = 1;

        if (ans[m][n] != 0)
            return ans[m][n];

        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int nr = m + dir[i][0], nc = n + dir[i][1];

            if (nr < ans.length && nc < ans[0].length)
                count += uniquePaths_memo(nr, nc, dir, ans);
        }

        return ans[m][n] = count;

    }

    public int uniquePaths_tab(int[][] dir, int[][] ans) {

        for (int m = ans.length - 1; m >= 0; m--) {
            for (int n = ans[0].length - 1; n >= 0; n--) {

                if (m == ans.length - 1 && n == ans[0].length - 1) {
                    ans[m][n] = 1;
                    continue;
                }
                int count = 0;
                for (int i = 0; i < dir.length; i++) {
                    int nr = m + dir[i][0], nc = n + dir[i][1];

                    if (nr < ans.length && nc < ans[0].length)
                        ans[m][n] += ans[nr][nc];
                }

            }
        }
        return ans[0][0];

    }

    public int uniquePaths1(int m, int n) {
        return uniquePaths_tab1(m, n, new int[m][n]);
    }

    public int uniquePaths_memo1(int r, int c, int[][] storage) {
        if (r == storage.length - 1 && c == storage[0].length - 1)
            return storage[r][c] = 1;
        if (storage[r][c] != 0)
            return storage[r][c];

        int res = 0;
        if (c < storage[0].length - 1)
            res += uniquePaths_memo1(r, c + 1, storage);
        if (r < storage.length - 1)
            res += uniquePaths_memo1(r + 1, c, storage);

        return storage[r][c] = res;
    }

    public int uniquePaths_tab1(int m, int n, int[][] storage) {
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == m - 1 && c == n - 1) {
                    storage[r][c] = 1;
                    continue;
                }
                if (r == m - 1) {
                    storage[r][c] = storage[r][c + 1];
                    continue;
                }
                if (c == n - 1) {
                    storage[r][c] = storage[r + 1][c];
                    continue;
                }

                storage[r][c] = storage[r][c + 1] + storage[r + 1][c];

            }
        }

        return storage[0][0];
    }

    // 63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        return uniquePathsWithObstacles_tab(obstacleGrid, dir, new int[m][n]);
    }

    public int uniquePathsWithObstacles_memo(int m, int n, int[][] obstacleGrid, int[][] dir, int[][] ans) {
        if (obstacleGrid[m][n] == 1)
            return 0;

        if (m == ans.length - 1 && n == ans[0].length - 1)
            return ans[m][n] = 1;

        if (ans[m][n] != 0)
            return ans[m][n];

        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int nr = m + dir[i][0], nc = n + dir[i][1];

            if (nr < ans.length && nc < ans[0].length && obstacleGrid[nr][nc] != 1)
                count += uniquePathsWithObstacles_memo(nr, nc, obstacleGrid, dir, ans);
        }

        return ans[m][n] = count;

    }

    public int uniquePathsWithObstacles_tab(int[][] obstacleGrid, int[][] dir, int[][] ans) {
        if (obstacleGrid[0][0] == 1 || obstacleGrid[ans.length - 1][ans[0].length - 1] == 1)
            return 0;

        for (int m = ans.length - 1; m >= 0; m--) {
            for (int n = ans[0].length - 1; n >= 0; n--) {
                if (obstacleGrid[m][n] == 1)
                    ans[m][n] = 0;
                else if (m == ans.length - 1 && n == ans[0].length - 1)
                    ans[m][n] = 1;
                else {
                    for (int i = 0; i < dir.length; i++) {
                        int nr = m + dir[i][0], nc = n + dir[i][1];

                        if (nr < ans.length && nc < ans[0].length)
                            ans[m][n] += ans[nr][nc];
                    }
                }

            }
        }

        return ans[0][0];
    }

    // 396
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int sum = 0;
        for (int e : nums)
            sum += e;

        for (int i = 0; i < n; i++) {
            ans[0] += i * nums[i];
        }

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + sum - n * nums[n - 1 - (i - 1)];
        }

        int max = Integer.MIN_VALUE;
        for (int e : ans)
            if (e > max)
                max = e;

        return max;

        // or

        // int n = nums.length;
        // int[] ans = new int[n];

        // int sum = 0;
        // for(int e: nums) sum+=e;

        // for(int i=0; i<n; i++){
        // ans[0] += i*nums[i];
        // }

        // int max = ans[0];
        // for(int i=1; i<n; i++){
        // ans[i] = ans[i-1]+sum-n*nums[n-1-(i-1)];
        // if(ans[i]>max) max=ans[i];
        // }
        // return max;

        // or

        // int n = nums.length;

        // int sum = 0;
        // for(int e: nums) sum+=e;

        // int prev = 0;
        // for(int i=0; i<n; i++){
        // prev += i*nums[i];
        // }

        // int max = prev;
        // for(int i=1; i<n; i++){
        // int curr = prev+sum-n*nums[n-1-(i-1)];
        // if(curr>max) max=curr;
        // prev = curr;
        // }
        // return max;

        // or

        // int n = nums.length;

        // int sum = 0, prev = 0;
        // for(int i=0; i<n; i++){
        // sum += nums[i];
        // prev += i*nums[i];
        // }

        // int max = prev;
        // for(int i=1; i<n; i++){
        // int curr = prev+sum-n*nums[n-1-(i-1)];
        // if(curr>max) max=curr;
        // prev = curr;
        // }
        // return max;
    }

    // 64
    static int maxGold(int m, int n, int M[][]) {
        int max = Integer.MIN_VALUE;
        int[][] ans = new int[m][n];
        // int[][] dir = {{-1,1}, {0,1}, {1,1}};
        for (int i = 0; i < m; i++) {
            max = Math.max(max, maxGold_memo(i, 0, M, ans));
        }
        return max;
    }

    static int maxGold_memo(int m, int n, int[][] mine, int[][] ans) {
        if (n == ans[0].length - 1) {
            return ans[m][n] = mine[m][n];
        }

        if (ans[m][n] != 0)
            return ans[m][n];

        int max = Integer.MIN_VALUE;

        if (m - 1 >= 0) {
            max = Math.max(max, maxGold_memo(m - 1, n + 1, mine, ans));
        }
        max = Math.max(max, maxGold_memo(m, n + 1, mine, ans));

        if (m + 1 < ans.length) {
            max = Math.max(max, maxGold_memo(m + 1, n + 1, mine, ans));
        }

        return ans[m][n] = mine[m][n] + max;
    }

    static int maxGold_memo1(int m, int n, int[][] mine, int[][] dir, int[][] ans) {
        if (n == ans[0].length - 1) {
            return ans[m][n] = mine[m][n];
        }

        if (ans[m][n] != 0)
            return ans[m][n];

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < dir.length; i++) {
            int nr = m + dir[i][0];
            int nc = n + dir[i][1];
            if (nr >= 0 && nc >= 0 && nr < mine.length && nc < mine[0].length)
                max = Math.max(max, maxGold_memo1(nr, nc, mine, dir, ans));
        }

        return ans[m][n] = mine[m][n] + max;
    }

    // gfg - count no of ways to divide n in k groups

    static int partitionIntoKSubsets(int n, int k, int[][] ans) {
        if (n == 0 || k == 0 || k > n)
            return ans[n][k] = 0;
        if (n == k || k == 1)
            return ans[n][k] = 1;

        if (ans[n][k] != 0)
            return ans[n][k];

        return ans[n][k] = partitionIntoKSubsets(n - 1, k - 1, ans) + partitionIntoKSubsets(n - 1, k, ans) * k;
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        return longestPalindromeSubseq(s, 0, n - 1, new int[n][n]);
    }

    public int longestPalindromeSubseq(String s, int i, int j, int[][] ans) {

        if (i == j)
            return ans[i][j] = 1;
        if (i > j)
            return ans[i][j] = 0;

        if (ans[i][j] != 0)
            return ans[i][j];

        char ci = s.charAt(i), cj = s.charAt(j);

        int len = 0;
        if (ci == cj) {
            len = 2 + longestPalindromeSubseq(s, i + 1, j - 1, ans);
        } else {
            len = Math.max(longestPalindromeSubseq(s, i, j - 1, ans), longestPalindromeSubseq(s, i + 1, j, ans));

        }

        return ans[i][j] = len;

    }

    public int longestPalindromeSubseq_tab(String s, int[][] ans) {
        // int n = s.length();

        // for(int c=0; c<n; c++){
        // for(int r=0; r<n-c; r++){
        // int i = r;
        // int j = r+c;

        // if(i==j){
        // ans[i][j] = 1;
        // continue;
        // }
        // if(i>j){
        // ans[i][j] = 0;
        // continue;
        // }

        // char ci = s.charAt(i), cj = s.charAt(j);

        // int len = 0;
        // if(ci==cj){
        // ans[i][j] = 2 + ans[i+1][j-1];
        // }
        // else{
        // ans[i][j] = Math.max(ans[i][j-1], ans[i+1][j]);
        // }
        // }
        // }

        // return ans[0][n-1];

        int n = s.length();

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n - gap; i++, j++) {

                if (i == j) {
                    ans[i][j] = 1;
                    continue;
                }
                if (i > j) {
                    ans[i][j] = 0;
                    continue;
                }

                char ci = s.charAt(i), cj = s.charAt(j);

                int len = 0;
                if (ci == cj) {
                    ans[i][j] = 2 + ans[i + 1][j - 1];
                } else {
                    ans[i][j] = Math.max(ans[i][j - 1], ans[i + 1][j]);
                }
            }
        }

        return ans[0][n - 1];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence_tab(text1, text2, new int[text1.length() + 1][text2.length() + 1]);
    }

    public int longestCommonSubsequence_memo(int i, int j, String s1, String s2, int[][] ans) {

        if (i == s1.length() || j == s2.length())
            return 0;

        if (ans[i][j] != 0)
            return ans[i][j];

        char ci = s1.charAt(i), cj = s2.charAt(j);

        int len = 0;
        if (ci == cj) {
            len = 1 + longestCommonSubsequence_memo(i + 1, j + 1, s1, s2, ans);
        } else {
            len = Math.max(longestCommonSubsequence_memo(i + 1, j, s1, s2, ans),
                    longestCommonSubsequence_memo(i, j + 1, s1, s2, ans));

        }

        return ans[i][j] = len;
    }

    public int longestCommonSubsequence_tab(String s1, String s2, int[][] ans) {
        int m = s1.length(), n = s2.length();

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == s1.length() || j == s2.length()) {
                    ans[i][j] = 0;
                    continue;
                }

                char ci = s1.charAt(i), cj = s2.charAt(j);

                int len = 0;
                if (ci == cj) {
                    ans[i][j] = 1 + ans[i + 1][j + 1];
                } else {
                    ans[i][j] = Math.max(ans[i + 1][j], ans[i][j + 1]);

                }
            }
        }

        return ans[0][0];
    }

    public int longestCommonSubsequence1(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        return longestCommonSubsequence_memo1(m, n, text1, text2, new int[text1.length() + 1][text2.length() + 1]);
    }

    public int longestCommonSubsequence_memo1(int i, int j, String s1, String s2, int[][] ans) {// initially i,j = m,n

        if (i == 0 || j == 0)
            return 0;

        if (ans[i][j] != 0)
            return ans[i][j];

        char ci = s1.charAt(i - 1), cj = s2.charAt(j - 1);

        int len = 0;
        if (ci == cj) {
            len = 1 + longestCommonSubsequence_memo1(i - 1, j - 1, s1, s2, ans);
        } else {
            len = Math.max(longestCommonSubsequence_memo1(i - 1, j, s1, s2, ans),
                    longestCommonSubsequence_memo1(i, j - 1, s1, s2, ans));

        }

        return ans[i][j] = len;
    }

    public int longestCommonSubsequence_tab1(String s1, String s2, int[][] ans) {
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {

                if (i == 0 || j == 0) {
                    ans[i][j] = 0;
                    continue;
                }

                char ci = s1.charAt(i - 1), cj = s2.charAt(j - 1);

                int len = 0;
                if (ci == cj) {
                    ans[i][j] = 1 + ans[i - 1][j - 1];
                } else {
                    ans[i][j] = Math.max(ans[i - 1][j], ans[i][j - 1]);

                }
            }
        }

        return ans[ans.length - 1][ans[0].length - 1];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] ans = new int[m + 1][n + 1];
        for (int[] d : ans)
            Arrays.fill(d, -1); // drastic changes in runtime - optimization

        return longestCommonSubsequence_memo2(m, n, text1, text2, ans);
    }

    public int longestCommonSubsequence_memo2(int i, int j, String s1, String s2, int[][] ans) {

        if (i == 0 || j == 0)
            return 0;

        if (ans[i][j] != -1)
            return ans[i][j]; // it indicates whether we have solved ans[i][j] before if we don't use
                              // Arrays.fill(-1) then we will use here ans[i][j]!=0. Now even if we have
                              // solved already ans[i][j] it would solve it again thinking it is not solved.
        // Hence we can't use int val = ans[i][j] as default value or as an identity.
        // default val can't equal ans otherwise it would destroy the goal of dp

        // Not using Arrays.fill(-1) would work perfectly if int ans[i][j]!=0.
        // eg- tc - abc, def

        char ci = s1.charAt(i - 1), cj = s2.charAt(j - 1);

        int len = 0;
        if (ci == cj) {
            len = 1 + longestCommonSubsequence_memo2(i - 1, j - 1, s1, s2, ans);
        } else {
            len = Math.max(longestCommonSubsequence_memo2(i - 1, j, s1, s2, ans),
                    longestCommonSubsequence_memo2(i, j - 1, s1, s2, ans));

        }

        return ans[i][j] = len;
    }

    public int editDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        return editDistance_memo(word1, word2, m, n, new int[m + 1][n + 1]);
    }

    public static int editDistance_memo(String s1, String s2, int m, int n, int[][] ans) {
        if (m == 0 || n == 0)
            return ans[m][n] = (m == 0 ? n : m);

        if (ans[m][n] != 0)
            return ans[m][n];

        int c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

        if (c1 == c2)
            return ans[m][n] = editDistance_memo(s1, s2, m - 1, n - 1, ans);

        else {
            int insertCall = editDistance_memo(s1, s2, m, n - 1, ans);
            int deleteCall = editDistance_memo(s1, s2, m - 1, n, ans);
            int updateCall = editDistance_memo(s1, s2, m - 1, n - 1, ans);

            return ans[m][n] = Math.min(insertCall, Math.min(deleteCall, updateCall)) + 1;
        }
    }

    public static int editDistance_tab(String s1, String s2, int M, int N, int[][] ans) {
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                if (m == 0 || n == 0)
                    ans[m][n] = (m == 0 ? n : m);
                else {
                    int c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

                    if (c1 == c2)
                        ans[m][n] = ans[m - 1][n - 1];

                    else {
                        int insertCall = ans[m][n - 1];
                        int deleteCall = ans[m - 1][n];
                        int updateCall = ans[m - 1][n - 1];

                        ans[m][n] = Math.min(insertCall, Math.min(deleteCall, updateCall)) + 1;
                    }
                }
            }
        }

        return ans[M][N];

    }

    public static int editDistanceWithGivenCost_memo(String s1, String s2, int m, int n, int[][] ans) {
        if (m == 0 || n == 0)
            return ans[m][n] = (m == 0 ? n : m);

        if (ans[m][n] != 0)
            return ans[m][n];

        int c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

        if (c1 == c2)
            return ans[m][n] = editDistance_memo(s1, s2, m - 1, n - 1, ans);

        else {
            int insertCall = editDistance_memo(s1, s2, m, n - 1, ans);
            int deleteCall = editDistance_memo(s1, s2, m - 1, n, ans);
            int updateCall = editDistance_memo(s1, s2, m - 1, n - 1, ans);

            return ans[m][n] = Math.min(insertCall, Math.min(deleteCall, updateCall)) + 1;
        }
    }

    // https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/

    // 115-no of given ss in the given string - gfg
    public int numDistinct(String s, String t) {
        int[][] ans = new int[s.length() + 1][t.length() + 1];
        for (int[] a : ans)
            Arrays.fill(a, -1);
        return numOfTimesAssOccursInAString_memo(s, t, s.length(), t.length(), ans);
    }

    static int numOfTimesAssOccursInAString_memo(String s1, String s2, int m, int n, int[][] ans) {
        if (n == 0)
            return ans[m][n] = 1;
        if (m == 0)
            return ans[m][n] = 0;

        if (ans[m][n] != -1)
            return ans[m][n];

        char c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

        int count = 0;
        if (c1 == c2) {
            count += numOfTimesAssOccursInAString_memo(s1, s2, m - 1, n - 1, ans);
            count += numOfTimesAssOccursInAString_memo(s1, s2, m - 1, n, ans);
        } else {
            count += numOfTimesAssOccursInAString_memo(s1, s2, m - 1, n, ans);
        }

        return ans[m][n] = count;
    }

    static int numOfTimesAssOccursInAString_tab(String s1, String s2, int M, int N, int[][] ans) {
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                if (n == 0)
                    ans[m][n] = 1;
                else if (m == 0)
                    ans[m][n] = 0;
                else {
                    char c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

                    int count = 0;
                    if (c1 == c2) {
                        count += ans[m - 1][n - 1];
                        count += ans[m - 1][n];
                    } else {
                        count += ans[m - 1][n];
                    }

                    ans[m][n] = count;
                }

            }
        }

        return ans[M][N];
    }

    // 583
    static int minDeleteOperations_memo(String s1, String s2, int m, int n, int[][] ans) {
        if (n == 0)
            return ans[m][n] = m;
        if (m == 0)
            return ans[m][n] = n;

        if (ans[m][n] != -1)
            return ans[m][n];

        char c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

        if (c1 == c2) {
            ans[m][n] = minDeleteOperations_memo(s1, s2, m - 1, n - 1, ans);
        } else {
            ans[m][n] = Math.min(minDeleteOperations_memo(s1, s2, m - 1, n, ans),
                    minDeleteOperations_memo(s1, s2, m, n - 1, ans)) + 1;
        }

        return ans[m][n];

    }

    static int minDeleteOperations_tab(String s1, String s2, int M, int N, int[][] ans) {
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                if (n == 0)
                    ans[m][n] = m;
                else if (m == 0)
                    ans[m][n] = n;
                else {
                    char c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

                    if (c1 == c2) {
                        ans[m][n] = ans[m - 1][n - 1];
                    } else {
                        ans[m][n] = Math.min(ans[m - 1][n], ans[m][n - 1]) + 1;
                    }
                }

            }
        }

        return ans[M][N];

    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] ans = new int[nums1.length + 1][nums2.length + 1];
        for (int[] a : ans)
            Arrays.fill(a, -1);
        return maxUncrossedLines_tab(nums1, nums2, nums1.length, nums2.length, ans);
    }

    static int maxUncrossedLines_memo(int[] a1, int[] a2, int m, int n, int[][] ans) {
        if (m == 0 || n == 0)
            ans[m][n] = 0;

        if (ans[m][n] != -1)
            return ans[m][n];

        int n1 = a1[m - 1], n2 = a2[n - 1];

        if (n1 == n2) {
            return ans[m][n] = maxUncrossedLines_memo(a1, a2, m - 1, n - 1, ans) + 1;
        } else {
            return ans[m][n] = Math.max(maxUncrossedLines_memo(a1, a2, m, n - 1, ans),
                    maxUncrossedLines_memo(a1, a2, m - 1, n, ans));
        }
    }

    static int maxUncrossedLines_tab(int[] a1, int[] a2, int M, int N, int[][] ans) {
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                if (m == 0 || n == 0)
                    ans[m][n] = 0;
                else {
                    int n1 = a1[m - 1], n2 = a2[n - 1];

                    if (n1 == n2) {
                        ans[m][n] = ans[m - 1][n - 1] + 1;
                    } else {
                        ans[m][n] = Math.max(ans[m - 1][n], ans[m][n - 1]);
                    }
                }

            }
        }

        return ans[M][N];

    }

    // 1035
    public String longestPalindrome(String s) {
        return longestPalindromeSubstring_tab(s, s.length(), new int[s.length()][s.length()]);
    }

    public String longestPalindromeSubstring_tab(String s, int N, int[][] ans) {
        int maxlen = 0, sidx = 0;

        for (int gap = 0; gap < N; gap++) {
            for (int m = 0, n = gap; n < N; m++, n++) {
                char c1 = s.charAt(m), c2 = s.charAt(n);

                if (gap == 0)
                    ans[m][n] = 1;
                else if (gap == 1 && c1 == c2)
                    ans[m][n] = 2;
                else if (c1 == c2 && ans[m + 1][n - 1] > 0) {
                    ans[m][n] = ans[m + 1][n - 1] + 2;
                }

                if (ans[m][n] > maxlen) {
                    maxlen = ans[m][n];
                    sidx = m;
                }
            }
        }

        return s.substring(sidx, sidx + maxlen);
    }

    int longestCommonSubstr(String s1, String s2, int m, int n) {
        return longestCommonSubstr_tab(s1, s2, m, n, new int[m + 1][n + 1]);
    }

    int longestCommonSubstr_tab(String s1, String s2, int M, int N, int[][] ans) {
        int max = 0;
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                if (m == 0 || n == 0)
                    ans[m][n] = 0;
                else {
                    char c1 = s1.charAt(m - 1), c2 = s2.charAt(n - 1);

                    if (c1 == c2) {
                        ans[m][n] = ans[m - 1][n - 1] + 1;
                        max = Math.max(max, ans[m][n]);
                    }
                }

            }
        }

        return max;
    }

    public static int countABCss(String s){
        int a=0, ab=0, abc=0, m =  (int)Math.round(1e9+7);
        
        for(int i=0; i<s.length(); i++){
            char ch=s.charAt(i);
            
            if(ch=='a'){
              a = ((2*a)%m + 1%m)%m;  
            }else if(ch=='b'){
              ab = ((2*ab)%m + a%m)%m; 
            }else if(ch=='c'){
              abc = ((2*abc)%m + ab%m)%m;   
            }
        }
        
        return  abc;
    }

    static int longestLISOfx(int[] arr, int idx, int[] ans){

        int max = 0;
        for (int i = 0; i < idx; i++) {
            if(arr[i]<arr[idx]){
               max = Math.max(ans[i], max);
            }
        }

        return ans[idx] = max+1; 
    }

    static int longestLIS(int[] arr){
        int n = arr.length, max = 0;
        int[] ans = new int[n];

        for(int i=0; i<n; i++) max = Math.max(longestLISOfx(arr, i, ans), max);

        return max;
    }

    public int maxSumIS(int arr[], int n)  
	{  
	    int[] ans = new int[n];
	    int omax = 0; 
	    
	    for(int i=0; i<n; i++){
	       omax = Math.max(maxSumISOfx(arr, i, ans), omax);
	    }
	    
	    return omax;
	} 
	
	int maxSumISOfx(int[] arr, int idx, int[] ans){
	    if(ans[idx]!=0) return ans[idx];
	    
	    int max = 0;
        for(int j = 0; j<idx; j++){
            if(arr[j]<arr[idx]){
               max = Math.max(max, ans[j]);
            }
        }
        return ans[idx] = max+arr[idx];
	        
	}

    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int[] maxlen = new int[n];
        int[] countmaxlenss = new int[n];
        
        int omaxlen = 0, omaxlenCount = 0;
        
        for (int i = 0; i < n; i++) {
            int max = 0, count = 0; 
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i]) {
                    if(maxlen[j]>max){
                      max = maxlen[j];
                      count = countmaxlenss[j];  
                    } 
                    else if(maxlen[j]==max){
                      count += countmaxlenss[j];  
                    } 
                }
            }
            maxlen[i] = max+1;
            if(count==0) count=1; 
            countmaxlenss[i] = count;
            
            if(maxlen[i]>omaxlen){
              omaxlen = maxlen[i];
              omaxlenCount = count;  
            }
            else if(maxlen[i]==omaxlen){
              omaxlenCount += count;   
            }
        }

        return omaxlenCount;
        
    }

    

    public static void main(String[] args)  {
        // System.out.println(minMovesMazePathsWithVariableJumps_memo(2,2,new
        // int[3][3]));
        System.out.println(Math.min(1, 1));
    }

}