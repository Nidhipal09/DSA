public class Class1{

    // in every dp problem - follow these steps

    // 1. faith
    // 2. tree diagram
    // 3. write recursion code
    
    // 4. recursion -> memoization 

    // 5. observation -> identify the direction of tablation (dependency)

    // 6. memoization -> tablation 

    // 7. optimization


    static void display1d(int[] arr){
        for (int e : arr) {
            System.out.print(e+" ");
        }
        System.out.println();
    }
    
    static void display2d(int[][] arr){
        for (int[] row1 : arr) {
            display1d(row1);
        }
    }

    public static int fibo_memo(int n, int[] storage) {
        if(n<=1) return storage[n]=n;
        if(storage[n]!=0) return storage[n];
        return storage[n] = fibo_memo(n-1, storage) + fibo_memo(n-2, storage);
    }

    public static int fibo_tab(int N) {
        int[] storage = new int[N+1];

        for (int n = 0; n <= N; n++) {
            if(n<=1){
              storage[n]=n;
              continue;  
            } 
            storage[n] = storage[n-1]+storage[n-2];
        }

        return storage[N];
    }

    public static int fibo_optimized(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
           int sum=a+b;
           a=b;
           b=sum;
        }

        return a;
    }


    //1137
    public int tribonacci(int n) {
        return tribonacci_opti(n);  
    }

    public int tribonacci_recursive(int n) {
        if(n<=2) return (n==0 ? 0 : 1); 
        return tribonacci_recursive(n-1)+tribonacci_recursive(n-2)+tribonacci_recursive(n-3);  
    }
    
    public int tribonacci_memo(int n, int[] storage) {
        if(n<=2) return storage[n] = (n==0 ? 0 : 1); 
        if(storage[n]!=0) return storage[n];
        return storage[n] = tribonacci_memo(n-1, storage) + tribonacci_memo(n-2, storage) + tribonacci_memo(n-3, storage); 
    }
    
    public int tribonacci_tab(int N, int[] storage) {
        for(int n=0; n<=N; n++){
            if(n<=2){
               storage[n] = (n==0 ? 0 : 1);
               continue; 
            }
            storage[n] = storage[n-1]+storage[n-2]+storage[n-3];   
        }
        
        return storage[N];
    }
    
    public int tribonacci_opti(int N) {
        int a=0, b=1, c=1;
        for(int n=0; n<N; n++){
            int sum = a+b+c;
            a=b;
            b=c;
            c=sum;
        }
        
        return a;
    }

    //70
    public int climbStairs(int n) {
        return climbStairs_optimized(n);
    }

    public int climbStairs_recursive(int n) {
        if(n==0) return 1;
        return (n-1>=0 ? climbStairs_recursive(n-1) :  0) + (n-2>=0 ? climbStairs_recursive(n-2) :  0);
    }
    
    public int climbStairs_memo(int n, int[] storage) {
        if(n<=1) return storage[n]=1;
        
        if(storage[n]!=0) return storage[n];
        
        return storage[n] = climbStairs_memo(n-1, storage) + climbStairs_memo(n-2, storage);
    }
    
    public int climbStairs_tab(int N, int[] storage) {
        for(int n=0; n<=N; n++){
           if(n<=1){
             storage[n]=1;
             continue;  
           }
           storage[n] = storage[n-1] + storage[n-2];
        }
        
        return storage[N];
    }
    
    public int climbStairs_optimized(int N){
        int a=1, b=1;
        for(int n=0; n<N; n++){
           int sum = a+b;
           a=b;
           b=sum;
        }
        
        return a;
    }

    //746
    public int minCostClimbingStairs_recursive(int n, int[] cost) {
        if(n<=1) return cost[n]; 
        
        int fcallRes = minCostClimbingStairs_recursive(n-1, cost);
        int scallRes = minCostClimbingStairs_recursive(n-2, cost);
        
        return (n==cost.length ? 0 : cost[n]) + Math.min(fcallRes, scallRes);
    }
    
    public int minCostClimbingStairs_memo(int n, int[] cost, int[] storage) {
        if(n<=1) return storage[n] = cost[n]; 
        if(storage[n]!=0) return storage[n] ;
        
        int fcallRes = minCostClimbingStairs_memo(n-1, cost, storage);
        int scallRes = minCostClimbingStairs_memo(n-2, cost, storage);
        
        return storage[n] = (n==cost.length ? 0 : cost[n]) + Math.min(fcallRes, scallRes);
    }
    
    public int minCostClimbingStairs_tab(int N, int[] cost, int[] storage) {
        for(int n=0; n<=N; n++){
           if(n<=1){
             storage[n] = cost[n];
             continue;  
           } 

            storage[n] = (n==cost.length ? 0 : cost[n]) + Math.min(storage[n-1], storage[n-2]);
        }
        
        return storage[N];
    }

    //friends pairing problem
    public long countFriendsPairings(int n) { 
      return countFriendsPairings_optimized(n);  
    }
    
    public long countFriendsPairings_recursive(int n) 
    { 
      if(n<=1) return 1;    
      long res = countFriendsPairings_recursive(n-1)+countFriendsPairings_recursive(n-2)*(n-1);
      return res%Math.round(1e9+7);
    }
    
    public long countFriendsPairings_memo(int n, long[] storage) 
    { 
      if(n<=1) return storage[n]=1;   
      if(storage[n]!=0) return storage[n];
      long res = countFriendsPairings_memo(n-1, storage)+countFriendsPairings_memo(n-2, storage)*(n-1);
      return storage[n] = res%Math.round(1e9+7);
    }
    
    public long countFriendsPairings_tab(int N, long[] storage) 
    { 
      long mod = Math.round(1e9+7);  //double to int   
      for(int n=0; n<=N; n++){
        if(n<=1){ 
            storage[n]=1;
            continue;
        }
        storage[n] = (storage[n-1]+storage[n-2]*(n-1))%mod;    //becoz ans can be very very large 
      } 
      return storage[N];
      
    }
    
    public long countFriendsPairings_optimized(int N) 
    { 
      long mod = Math.round(1e9+7);  
      long a = 1, b = 1;
      for(int n=0; n<N; n++){ 
        long next = (b+a*((n+2)-1))%mod;  
        a=b;
        b=next;
      } 
      return a;
      
    }

    static int minMovesMazePaths_memo(int m, int n, int[][] dp){
        if (m==0 && n==0) {
            return dp[0][0] = 0;
        } 
        if (dp[m][n]!=0) {
            return dp[m][n];
        }
        int min=Integer.MAX_VALUE;
        if(n-1>=0) min = Math.min(minMovesMazePaths_memo(m, n-1, dp),min);
        if(m-1>=0) min = Math.min(minMovesMazePaths_memo(m-1, n, dp),min);
        if(m-1>=0 && n-1>=0) min = Math.min(minMovesMazePaths_memo(m-1, n-1, dp),min);
       
        return dp[m][n] = min+1;
    }

    static int minMovesMazePathsWithVariableJumps_memo(int m, int n, int[][] dp){
        if (m==0 && n==0) {
            return dp[0][0] = 0;
        } 
        if (dp[m][n]!=0) {
            return dp[m][n];
        }
        int min=Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m, n-j, dp),min);
        }

        for (int j = 1; j <= m; j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m-j, n, dp),min);
        }

        for (int j = 1; j <= Math.min(m, n); j++) {
            min = Math.min(minMovesMazePathsWithVariableJumps_memo(m-j, n-j, dp),min);
        }
       
        return dp[m][n] = min+1;
    }

    
    // with dir array- maze total paths
    //sir min cost climbing stairs ko optimized two pointer way se ek baar krado- https://leetcode.com/problems/min-cost-climbing-stairs/
    
    //62 - using dir
    public int uniquePaths(int m, int n) {
        return uniquePaths_tab(m, n, new int[m][n]);  
   }
   
   public int uniquePaths_memo(int r, int c, int[][] storage) {
       if(r==storage.length-1 && c==storage[0].length-1) return storage[r][c]=1;
       if(storage[r][c]!=0) return storage[r][c];
       
       int res = 0;
       if(c<storage[0].length-1) res += uniquePaths_memo(r, c+1, storage);
       if(r<storage.length-1) res += uniquePaths_memo(r+1, c, storage);
       
       return storage[r][c]=res;
   }
   
   public int uniquePaths_tab(int m, int n, int[][] storage) {
       for (int r = m - 1; r >= 0; r--) {
           for (int c = n - 1; c >= 0; c--) {
                if(r==m-1 && c==n-1){
                    storage[r][c]=1;
                    continue;
                }
                if(r==m-1){
                    storage[r][c]=storage[r][c+1];
                    continue;
                }
                if(c==n-1){
                    storage[r][c]=storage[r+1][c];
                    continue;
                }
               
                storage[r][c] = storage[r][c+1] + storage[r+1][c];
               
           }
       }
       
       return storage[0][0];
   }

    //63
    //396
    //gfg - count no of ways to divide n in k groups
    // 64

    


    



    
    public static void main(String[] args) {
        // System.out.println(minMovesMazePathsWithVariableJumps_memo(2,2,new int[3][3]));
        System.out.println(Math.min(1, 1));
    }

}