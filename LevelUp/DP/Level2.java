import java.util.*;

public class Level2 {
    public static void minJumps(int arr[]) {
        int N = arr.length;

        // tabulation
        Integer[] storage = new Integer[N];
        for (int n = N - 1; n >= 0; n--) {
            if (n == N - 1) {
                storage[n] = 0;
                continue;
            } else if (arr[n] != 0) {
                int min = Integer.MAX_VALUE;
                for (int sj = 1; sj <= arr[n] && n + sj < N; sj++) {
                    if (storage[n + sj] != null)
                        min = Math.min(min, storage[n + sj]);
                }
                if (min != Integer.MAX_VALUE) {
                    storage[n] = min + 1;
                }

            }

        }
        System.out.println(storage[0]);

        // find paths
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, arr[0], storage[0], "0"));
        while (q.size() > 0) {
            Pair p = q.remove();

            if (p.idx == N - 1) {
                System.out.println(p.asf + " .");
                continue;
            }

            for (int sj = 1; sj <= p.nj && p.idx + sj < N; sj++) {
                int ni = p.idx + sj;
                if (storage[ni] != null && storage[ni] == p.mj - 1) {
                    Pair np = new Pair(ni, arr[ni], storage[ni], p.asf + " -> " + ni);
                    q.add(np);
                }
            }
        }

    }

    // public static class Pair{
    // int idx=0;
    // int nj=0;
    // int mj=0;
    // String asf="";

    // Pair(int idx, int nj, int mj, String asf){
    // this.idx=idx;
    // this.nj=nj; //no of jumps
    // this.mj=mj; //min jumps
    // this.asf=asf;
    // }
    // }

    public static void minJumps1(int arr[]) {
        int N = arr.length;

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= arr[i] && i + j < N; j++) {
                map.get(i + j).add(i);
            }
        }

        System.out.println(minJumps_memo(N - 1, map, new int[N]));

    }

    static int minJumps_memo(int i, HashMap<Integer, ArrayList<Integer>> map, int[] storage) {

        if (i == 0) {
            return storage[0] = 0;
        }

        if (storage[i] != 0)
            return storage[i];

        int min = Integer.MAX_VALUE;

        ArrayList<Integer> al = map.get(i);
        for (int s = al.size() - 1; s >= 0; s--) {
            int rres = minJumps_memo(al.get(s), map, storage);
            if (rres < min) {
                min = rres;
            }
        }

        return storage[i] = min + 1;
    }



    static int maxSquare(int m, int n, int arr[][]) {
        int[][] dp = new int[m][n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (arr[i][j] != 0) {
                    if ((i == 0 && j == 0) || (i == 0) || (j == 0)) {
                        dp[i][j] = 1;
                    } else {
                        int min = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                        dp[i][j] = Math.min(dp[i][j - 1], min) + 1;
                    }
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max;
    }

    static int maxSquare1(int m, int n, int arr[][]) {
        int[][] dp = new int[m][n];
        int max = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (arr[i][j] != 0) {
                    if ((i == m - 1 && j == n - 1) || (i == m - 1) || (j == n - 1)) {
                        dp[i][j] = 1;
                    } else {
                        int min = Math.min(dp[i + 1][j + 1], dp[i + 1][j]);
                        dp[i][j] = Math.min(dp[i][j + 1], min) + 1;
                    }
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max;
    }

    public static void mazeMinCostAndPaths(int[][] maze) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] storage = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (i == m - 1 && j == n - 1)
                    storage[i][j] = maze[i][j];
                else if (i == m - 1)
                    storage[i][j] = maze[i][j] + storage[i][j + 1];
                else if (j == n - 1)
                    storage[i][j] = maze[i][j] + storage[i + 1][j];
                else
                    storage[i][j] = maze[i][j] + Math.min(storage[i][j + 1], storage[i + 1][j]);
            }
        }

        System.out.println(storage[0][0]); // min cost

        // all min cost paths
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair("", 0, 0));

        while (q.size() > 0) {
            Pair p = q.remove();

            if (p.i == m - 1 && p.j == n - 1) {
                System.out.println(p.psf);
            }

            else if (p.i == m - 1)
                q.add(new Pair(p.psf + "H", p.i, p.j + 1));

            else if (p.j == n - 1)
                q.add(new Pair(p.psf + "V", p.i + 1, p.j));

            else {
                if (storage[p.i][p.j + 1] < storage[p.i + 1][p.j])
                    q.add(new Pair(p.psf + "H", p.i, p.j + 1));
                else if (storage[p.i][p.j + 1] > storage[p.i + 1][p.j])
                    q.add(new Pair(p.psf + "V", p.i + 1, p.j));
                else {
                    q.add(new Pair(p.psf + "V", p.i + 1, p.j));
                    q.add(new Pair(p.psf + "H", p.i, p.j + 1));

                }

            }

        }
    }

    public static class Pair {
        String psf;
        int i;
        int j;

        public Pair(String psf, int i, int j) {
            this.psf = psf;
            this.i = i;
            this.j = j;
        }
    }

    public static void targetSumSubsets(int[] arr, int tar) {
        
        boolean[][] canFormSum = new boolean[arr.length+1][tar+1];
        
        for(int i=0; i<canFormSum.length; i++){
            canFormSum[i][0] = true;
        }
           
           
        for(int i=1; i<canFormSum.length; i++){
            for(int j=1; j<canFormSum[0].length; j++){
                
                 if(arr[i-1] > j ) canFormSum[i][j]=canFormSum[i-1][j];
                 else if(canFormSum[i-1][j] || canFormSum[i-1][j-arr[i-1]])
                    canFormSum[i][j]=true; 
            }
        }
        
        System.out.println(canFormSum[canFormSum.length-1][canFormSum[0].length-1]);
        
        if(!canFormSum[canFormSum.length-1][canFormSum[0].length-1]) return;

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair("", arr.length, tar));

        while (q.size()>0) {
            Pair p = q.remove();

            if (p.j==0) {
                System.out.println(p.asf);
                continue;
            }

            if (p.j >= arr[p.i-1] && canFormSum[p.i-1][p.j-arr[p.i-1]]) { // first check if arr[p.i-1]=10, tar=10
                q.add(new Pair(p.i-1+" "+p.asf, p.i-1, p.j-arr[p.i-1])); 
            }

            if (canFormSum[p.i-1][p.j]) {
                q.add(new Pair(p.asf, p.i-1, p.j)); 
            }

            
        }

     }
    
     public static void zeroOneKnapsack(int[] values, int[] wt, int n,int cap) {
        
        int[][] ans=new int[n+1][cap+1];
        
        for(int i=1; i<ans.length; i++){
            for(int j=1; j<ans[0].length; j++){
                
                ans[i][j] = ans[i-1][j];
                if(j >= wt[i-1]){
                    ans[i][j] = Math.max(ans[i-1][j], values[i-1]+ans[i-1][j-wt[i-1]]);
                }
            }
        }
        
        System.out.println(ans[ans.length-1][ans[0].length-1]);

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair("", n, cap));//according to ans array

        while (q.size()>0) {
            Pair p = q.remove();

            if(p.j==0 || p.i==0){
                System.out.println(p.asf);
                return;
            }
            int exc = ans[p.i-1][p.j];
            
            if(p.j>=wt[p.i-1]){ // if wt = 10, cap = 9
                int inc = values[p.i-1]+ans[p.i-1][p.j-wt[p.i-1]];
                if(ans[p.i][p.j]==inc){
                  q.add(new Pair(p.i-1+" "+p.asf, p.i-1, (p.j-wt[p.i-1])));
                }
            }
            
            if(ans[p.i][p.j]==exc){
                q.add(new Pair(p.asf, p.i-1, p.j));
            }
        }
     }


    public static class Pair {
        String asf;
        int i;
        int j;

        public Pair(String asf, int i, int j) {
            this.asf = asf;
            this.i = i;
            this.j = j;
        }
    }

    public static void maxGold(int[][] maze,int m,int n){
          
        int[][] ans=new int[m][n];
        
        for(int i=0; i<m; i++){
            ans[i][n-1]=maze[i][n-1];
        }
        
        for(int j=n-2; j>=0; j--){
            for(int i=0; i<m; i++){
                if(i==0)
                ans[i][j]= maze[i][j] + Math.max(ans[i][j+1], ans[i+1][j+1]);
                
                else if(i==m-1)
                ans[i][j]= maze[i][j] + Math.max(ans[i][j+1], ans[i-1][j+1]);
                
                else
                ans[i][j]= maze[i][j] +Math.max(ans[i][j+1],Math.max(ans[i-1][j+1], ans[i+1][j+1]));
            }
        }
        
        int max=Integer.MIN_VALUE;
        int maxi=0;
        for(int i=0; i<m; i++){
           if(ans[i][0] > max){
              max = ans[i][0];
              maxi=i;
           } 
        }
        System.out.println(max);

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(maxi+"", maxi, 0));


        while (q.size()>0) {
            Pair p = q.remove();

            if (p.j==n-1) {
                System.out.println(p.asf);
                continue;
            }

            if(p.i==0){
                if(ans[p.i][p.j+1] >= ans[p.i+1][p.j+1])
                  q.add(new Pair(p.asf+" d2", p.i, p.j+1));
                if(ans[p.i][p.j+1] <= ans[p.i+1][p.j+1]) q.add(new Pair(p.asf+" d3", p.i+1, p.j+1)); 
                
            }
            
            else if(p.i==m-1){
                if(ans[p.i-1][p.j+1] >= ans[p.i][p.j+1])
                  q.add(new Pair(p.asf+" d1", p.i-1, p.j+1));
                if(ans[p.i-1][p.j+1] <= ans[p.i][p.j+1]) q.add(new Pair(p.asf+" d2", p.i, p.j+1)); 
                
            }
            
            else{
                int me = Math.max(ans[p.i-1][p.j+1], Math.max(ans[p.i][p.j+1], ans[p.i+1][p.j+1]));
                
                if(ans[p.i-1][p.j+1] == me)
                 q.add(new Pair(p.asf+" d1", p.i-1, p.j+1));
                if(ans[p.i][p.j+1] == me)
                 q.add(new Pair(p.asf+" d2", p.i, p.j+1));
                if(ans[p.i+1][p.j+1] == me)
                 q.add(new Pair(p.asf+" d3", p.i+1, p.j+1));
            }


        }

    } 

    
    public int lengthOfLIS(int[] arr) { //sc-O(n) tc-n(n+1)/2 or O(n^2)
        int n = arr.length;
        int[] ans = new int[n];
        int omax = 0;
        
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i] && ans[j]>max) {
                    max = ans[j];
                }
            }
            ans[i] = max+1;
            
            if(ans[i]>omax) omax = ans[i];
        }

        return omax;
    }

    // lis with nlogn

    public static void allLIS(int[] arr) { //sc-O(n) tc-n(n+1)/2 or O(n^2)
        int n = arr.length;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i] && ans[j]>max) {
                    max = ans[j];
                }
            }
            ans[i] = max+1;
        }


        int maxlen = 0, mleni = 0;
        for(int i=0; i<n; i++){
            if(ans[i]>maxlen){
                maxlen=ans[i];
                mleni=i;
            }
        }
        
        System.out.println(maxlen);
        
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(mleni, maxlen, arr[mleni]+""));
        
        while(q.size()>0){
            Pair p = q.remove();
            
            if(p.l==1){
                System.out.println(p.asf);
                continue;
            }
            
            for(int j=p.i-1; j>=0; j--){
                if(arr[j]<arr[p.i] && ans[j]==p.l-1){
                    q.add(new Pair(j, p.l-1, arr[j]+" -> "+p.asf));
                }
            }
        }
    }

    public static void allLISUpdated(int[] arr) { //sc-O(n) tc-n(n+1)/2 or O(n^2)
        int n = arr.length;
        int[] ans = new int[n];
        int maxlen = 0;
        
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i] && ans[j]>max) {
                    max = ans[j];
                }
            }
            ans[i] = max+1;
            
            if(ans[i]>maxlen) maxlen=ans[i];
        }

        System.out.println(maxlen);
        
        ArrayDeque<Pair> q = new ArrayDeque<>();
        for(int i=0; i<n; i++){  // for multiple ans if ,many indices have maxlen
            if(ans[i]==maxlen)
             q.add(new Pair(i, maxlen, arr[i]+""));
        }
        
        
        while(q.size()>0){
            Pair p = q.remove();
            
            if(p.l==1){
                System.out.println(p.asf);
                continue;
            }
            
            for(int j=p.i-1; j>=0; j--){
                if(arr[j]<arr[p.i] && ans[j]==p.l-1){
                    q.add(new Pair(j, p.l-1, arr[j]+" -> "+p.asf));
                }
            }
        }
    }

    public static int maxSumIS(int[] arr) { //sc-O(n) tc-n(n+1)/2 or O(n^2)
        int n = arr.length;
        int[] ans = new int[n];
        int omax = 0;
        
        for (int i = 0; i < n; i++) {
            int maxSum = 0;
            for (int j = i-1; j >= 0; j--) {
                if (arr[j]<=arr[i] && ans[j]>maxSum) {
                    maxSum = ans[j];
                }
            }
            ans[i] = maxSum+arr[i];
            
            if(ans[i]>omax) omax = ans[i];
        }

        return omax;
    }

    public static int longestBitonicSS(int[] arr){
        int n = arr.length;
        int[] left = new int[n];
        
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i] && left[j]>max) {
                    max = left[j];
                }
            }
            left[i] = max+1;
        }

        int[] right = new int[n];

        for(int i=n-1; i>=0; i--){
            int max = 0;
            for(int j=n-1; j>i; j--){
                if(arr[j]<arr[i] && right[j]>max){
                    max=right[j];
                }
            }
            right[i] = max+1;
        }

        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            int len = left[i]+right[i]-1;
            if(len>maxlen) maxlen=len;
        }

        return maxlen;

    }

    public static int maxNumOfNonOverlappingBridges(Bridge[] bridges){
        int n=bridges.length;
        Arrays.sort(bridges);
        
        int[] ans = new int[n];
        int maxlen = 0;
        
        for(int i=0; i<n; i++){
            int max = 0;
            for(int j=0; j<i; j++){
                if(bridges[j].n<bridges[i].n  && bridges[j].s<bridges[i].s && ans[j]>max){  // it is necessary to check north also in case of equal north which is not valid
                    max = ans[j];                
                }
            }
            ans[i]=max+1;
            if(ans[i]>maxlen) maxlen = ans[i]; 
        }
        
        return maxlen;
    }
    
    public static class Bridge implements Comparable<Bridge>{
        int n;
        int s;
        Bridge(int n, int s){
            this.n=n;
            this.s=s;
        }
        
        public int compareTo(Bridge o){
            if(this.n!=o.n)
              return this.n-o.n;
            else
              return this.s-o.s;
        }
    }

    public static int russianDolls(int[][] bridges){
        int n=bridges.length;
        
        Arrays.sort(bridges, new Comparator<int[]>(){
           public int compare(int[] arr1, int[] arr2){
             return arr1[0]-arr2[0];
             //or 
            //  if(arr1[0]>arr2[0]) return 1;
            //  else return -1;
           }
        });
        
        
        int[] ans = new int[n];
        int maxlen = 0;
        
        for(int i=0; i<n; i++){
            int max = 0;
            for(int j=0; j<i; j++){
                if(bridges[j][0]<bridges[i][0] && bridges[j][1]<bridges[i][1] && ans[j]>max){ // it is necessary to check width also in case of equal width which is not valid
                    max = ans[j];                
                }
            }
            ans[i]=max+1;
            if(ans[i]>maxlen) maxlen = ans[i]; 
        }
        
        return maxlen;
    }
    
    static class MyComparator implements Comparator<int[]>{  // sort a 2d array
        public int compare(int[] arr1, int[] arr2){
             return arr1[0]-arr2[0];
        }
    }
    
    public static int russianDolls1(int[][] bridges){
        int n=bridges.length;
        
        Arrays.sort(bridges, new MyComparator());
        
        
        int[] ans = new int[n];
        int maxlen = 0;
        
        for(int i=0; i<n; i++){
            int max = 0;
            for(int j=0; j<i; j++){
                if(bridges[j][0]<bridges[i][0] && bridges[j][1]<bridges[i][1] && ans[j]>max){
                    max = ans[j];                
                }
            }
            ans[i]=max+1;
            if(ans[i]>maxlen) maxlen = ans[i]; 
        }
        
        return maxlen;
    }

    public static int minSqrs(int n){
	    int[] ans = new int[n+1];
	    ans[0] = 0;
	    for(int i=1; i<=n; i++){
	        int x=1;
	        int min=Integer.MAX_VALUE;
	        while(x*x <= i){
	            min = Math.min(ans[i-x*x],min);
	            x++;
	        }
	        ans[i]=min+1;
	    }
	    
	    return ans[n];
	}

    static int catalanNum(int n){
        int[] ans = new int[n+1];
        ans[0] = 1;
        
        for(int i=1; i<=n; i++){
            for(int j=0; j<=i-1; j++){
                ans[i] += ans[j]*ans[i-(j+1)];
            }
        }
        
        return ans[n];
     }

     


    




}
