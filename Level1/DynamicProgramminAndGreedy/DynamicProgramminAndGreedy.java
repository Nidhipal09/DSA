package DynamicProgramminAndGreedy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DynamicProgramminAndGreedy{
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in); 
        int n=sc.nextInt();
        System.out.println(fibDP(n, new int[n+1]));
      }
      
      public static int fib(int n){
        if(n==0 || n==1) return n;
       
        System.out.println("hello "+n);
        int ans = fib(n-1) + fib(n-2); 
        return ans;
      }
      
      public static int fibDP(int n, int[] ansBank){  // to memorize the ans
         if(n==0 || n==1) return n;
         else if(ansBank[n]!=0) return ansBank[n];  // acting as a protection shield- not to let going to downward code
         
         System.out.println("hello "+n);
         int ans = fibDP(n-1, ansBank) + fibDP(n-2, ansBank); 
         ansBank[n]=ans;
         
         return ans;
      }



    public static int climbStairsMemo(int n, int[] ansbank){
        if(n==0) return 1;
        else if(n<0) return 0;
        else if(ansbank[n] != 0) return ansbank[n];
        
        
        int ans= climbStairsMemo(n-1, ansbank)
                 + climbStairsMemo(n-2, ansbank)
                 + climbStairsMemo(n-3, ansbank);
                 
        ansbank[n]=ans;
        return ans;
     } 

     public static int climbStairsTab(int n){
        int[] paths=new int[n+1];
        paths[0]=1;
        
        for(int i=1; i<=n; i++){
           if(i==1) paths[i]=paths[i-1]; 
           else if(i==2) paths[i]=paths[i-1]+paths[i-2];
           else paths[i]=paths[i-1]+paths[i-2]+paths[i-3];
        }
        
        return paths[n];
    }


    public static int climbStairsWithVariableJumps(int n,int[] jumps){
        int[] ans=new int[n+1];
        
        ans[n]=1;
        
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<=jumps[i] && i+j < ans.length; j++){
                ans[i] += ans[i+j];
            }
        }
        
        return ans[0];
    }

    public static int climbStairsWithMinJumps(int n,int[] jumps){
        Integer[] minMoves=new Integer[n+1];
        
        minMoves[n]=0;
       
        for(int i=n-1; i>=0; i--){
           if(jumps[i]!=0){
               
            int min = Integer.MAX_VALUE;
            for(int j=1; j<= jumps[i] && i+j < minMoves.length; j++){
                if(minMoves[i+j]!=null)
                min = Math.min(min, minMoves[i+j]);
            }
            
            if(min!=Integer.MAX_VALUE)   
             minMoves[i]=min+1; 
            
           }
        }
        return minMoves[0];
    }


    public static int mazeMinCost(int[][] maze,int m,int n){
          
        int[][] ans=new int[m][n];
        ans[m-1][n-1]=maze[m-1][n-1];
        
        for(int j=n-2; j>=0; j--){
           ans[m-1][j] = maze[m-1][j] + ans[m-1][j+1];
        }
        
        for(int i=m-2; i>=0; i--){
            ans[i][n-1] = maze[i][n-1] + ans[i+1][n-1];
        }
        
        for(int i=m-2; i>=0; i--){
            for(int j=n-2; j>=0; j--){
                ans[i][j] = maze[i][j] + Math.min(ans[i+1][j], ans[i][j+1]);
            }
        }
        
        return ans[0][0];
    }

    public static int goldmine(int[][] maze,int m,int n){
          
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
        
        int max=0;
        for(int i=0; i<m; i++){
           max = Math.max(max, ans[i][0]); 
        }
        return max;
    }  
   

    public static boolean targetSumSubsets(int[] arr, int tar) {
        
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
        
        return canFormSum[canFormSum.length-1][canFormSum[0].length-1];
     }


     public static int CoinChangeCombination(int[] arr, int tar) {
        
        int[] ans=new int[tar+1];
        ans[0]=1;
        
        for(int i=0; i<arr.length; i++){
            for(int j=1; j<ans.length; j++){
              
              if(j >= arr[i] && ans[j-arr[i]]!=0){
                 ans[j] += ans[j-arr[i]]; 
              }  
            }
        }
        
        return ans[ans.length-1];
       }


       public static int CoinChangePermutation(int[] arr, int tar) {
        
        int[] ans=new int[tar+1];
        ans[0]=1;
        
        for(int i=1; i<ans.length; i++){ // amt - 0,1,2,3,4,5,6,7
            for(int j=0; j<arr.length; j++){ // coin - 2,3,5,6
                
                if(i >= arr[j] && ans[i-arr[j]]!=0)
                  ans[i] += ans[i-arr[j]]; 
            
                
            }
        }
        
        return ans[ans.length-1];
       }

       public static int zeroOneKnapsack(int[] values, int[] wt, int n,int cap) {
        
        int[][] ans=new int[n+1][cap+1];
        
        for(int i=1; i<ans.length; i++){
            for(int j=1; j<ans[0].length; j++){
                
                ans[i][j] = ans[i-1][j];
                if(j >= wt[i-1]){
                    ans[i][j] = Math.max(ans[i-1][j], values[i-1]+ans[i-1][j-wt[i-1]]);
                }
            }
        }
        
        return ans[ans.length-1][ans[0].length-1];
     }


     public static int unboundedKnapsack(int[] values, int[] wt, int n,int cap) {
        
        int[] ans=new int[cap+1];
        
        for(int i=0; i<n; i++){
          for(int j=1; j<ans.length; j++){
              
              if(j >= wt[i]) ans[j] = Math.max(ans[j], values[i]+ans[j-wt[i]]);
          }  
        }
        
        return ans[ans.length-1];
     }

     public static double fractionalKnapsack(int[] values, int[] wt, int n, int cap) {
        Item[] items = new Item[n];

         for(int i=0; i<n; i++){
             double rate = (values[i]*1.0)/wt[i] ;  
             Item item =new Item(values[i], wt[i], rate);
             items[i] = item;
         }

         Arrays.sort(items);

         double ans=0, rwt=cap;
         for(int i=0; i<n; i++){
            if(items[i].wt <= rwt){
                rwt -= items[i].wt;
                ans += items[i].val;
            }
            else{
                ans += rwt * 1.0 * items[i].rate;
                break;
            }
         }

         return ans;
        
     }

     
     public static class Item implements Comparable<Item>{
         int val;
         int wt;
         double rate;

         Item(int val, int wt, double rate){
             this.val = val;
             this.wt = wt;
             this.rate = rate;
         }

         public int compareTo(Item o){
            if(this.rate > o.rate) return -1;
            else if(this.rate < o.rate) return 1;
            else return 0;
         }
     }


     public static int countBinaryStrings(int n){
        int[] b0=new int[n+1];  
        int[] b1=new int[n+1];   

        b0[1]=1;
        b1[1]=1;

        for(int i=2; i<= n; i++){
            b0[i]=b1[i-1];
            b1[i]=b0[i-1] + b1[i-1];
        }

        return b0[n]+b1[n];
    }


    

     public static int countBinaryStringsWithVar(int n){
        int prev0=1, prev1=1;  
        
        for(int i=0; i<= n; i++){
            int new0=prev1;
            int new1=prev0 + prev1;

            prev0=new0;
            prev1=new1;
        }

        return prev0+prev1;
    }

    public static long arrangeBuildings(int n){
        long prev0=1, prev1=1;  
        
        for(int i=2; i<= n; i++){
            long new0=prev1;
            long new1=prev0 + prev1;

            prev0=new0;
            prev1=new1;
        }

        long temp=prev0+prev1;
        long res=temp*temp;

        return res;
    }


    public static int CountEncodings(String s){
        int[] ans=new int[s.length()];
        ans[0]=1;
        
        for(int i=1; i<ans.length; i++){

            if(s.charAt(i-1)=='0' && s.charAt(i)=='0'){
                return 0; 
            }
            
            else if(s.charAt(i-1)=='0' && s.charAt(i)!='0'){
                ans[i]=ans[i-1];  //202
            }
            else if(s.charAt(i-1)!='0' && s.charAt(i)=='0'){
                if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2') ans[i]= (i==1? 1 : ans[i-2]); //20
                else return 0;  //30
            }
            
            else if(s.charAt(i-1)!='0' && s.charAt(i)!='0'){
                if(Integer.parseInt(s.substring(i-1,i+1)) <=  26) ans[i]=ans[i-1]+(i==1 ? 1 : ans[i-2]); //16
                else ans[i]=ans[i-1];  //46
            }
            
        }
        return ans[ans.length-1];
      }
     

    
    public static int countABCss(String s){
        int a=0, ab=0, abc=0;
        
        for(int i=0; i<s.length(); i++){
            char ch=s.charAt(i);
            
            if(ch=='a'){
              a = 2*a + 1;  
            }else if(ch=='b'){
              ab = 2*ab + a; 
            }else if(ch=='c'){
              abc = 2*abc + ab;   
            }
        }
        
        return abc;
    }


    public static int maxSumNonAdjElements(int[] arr){
        int prevIn=arr[0], prevEx=0;
        
        for(int i=1; i<arr.length; i++){
            int in = arr[i] + prevEx;
            int ex = Math.max(prevIn, prevEx);
            
            prevIn=in;
            prevEx=ex;
        }
        
        return Math.max(prevIn, prevEx);
    }

    public static int maxSumNonAdjElements1(int[] arr){  //wrong
        int even=0, odd=0, n=arr.length;
        
        int i=0;
        while(i<n){
            even += arr[i];  //0,2,4
            if(i+1 < n) odd = arr[i+1];  //1,3,5

            i += 2;
        }
        
        return Math.max(even, odd);
    }



    public static int paintHouse(int[][] arr){
        int prevR=arr[0][0], prevB=arr[0][1], prevG=arr[0][2];
        
        for(int i=1; i<arr.length; i++){
            int newR = arr[i][0] + Math.min(prevB, prevG);
            int newB = arr[i][1] + Math.min(prevR, prevG);
            int newG = arr[i][2] + Math.min(prevR, prevB);
            
            prevR=newR;
            prevB=newB;
            prevG=newG;
            
        }
        
        return Math.min(prevR, Math.min(prevB, prevG));
    }


    public static int paintHouseWithManyColors(int[][] arr, int n, int k){
        int[][] ans = new int[n][k];
        
        for(int i=0; i<n; i++){ // houses
            for(int j=0; j<k; j++){ // colors 
              if(i==0) ans[0][j] = arr[0][j];
              else{
                int min=Integer.MAX_VALUE;
                for(int c=0; c<k; c++){
                    if(c!=j) min = Math.min(min, ans[i-1][c]);
                }
                ans[i][j] = arr[i][j] + min;  
              }
            }
        }
        
        int min=Integer.MAX_VALUE;
        for(int c=0; c<k; c++){
            min = Math.min(min, ans[n-1][c]);
        }
        return min;
    }


    public static int paintFence(int n, int k){
        int prevSame=k, prevDiff=k*(k-1), prevTotal=prevSame+prevDiff;
        
        for(int i=3; i<=n; i++){
            int newSame = prevDiff;
            int newDiff = prevTotal * (k-1);
            
            prevSame = newSame;
            prevDiff = newDiff;
            prevTotal = newSame+newDiff;
        }
        
        return prevTotal;
    }

    public static int tilingWith21(int n){
        int prevH=1, prevV=1, prevTotal=2;
        
        for(int i=3; i<=n; i++){
            int newH = prevV;
            int newV = prevTotal;
            
            prevH = newH;
            prevV = newV;
            prevTotal = newH+newV;
        }
        
        return prevTotal;
    }

    public static int tilingWithM1(int n, int m){
        int[] ans=new int[n+1];
        
        for(int i=1; i<=n; i++){
            if(i<m) ans[i]=1;
            else if(i==m) ans[i]=2;
            else ans[i]=ans[i-1] + ans[i-m];
        }
        
        return ans[n];
    }
    
    
    public static int friendsPairing(int n){
        int[] ans=new int[n+1];
        ans[1]=1;
        ans[2]=2;
       
        for(int i=3; i<=n; i++){
            ans[i] = ans[i-1] + (i-1)*ans[i-2];
        }
        
        return ans[n];
    }


    public static long partitionKSubsets(int n, int k)   {
        long[][] ans=new long[k+1][n+1];
        
        for(int i=1; i<=k; i++){
           for(int j=1; j<=n; j++){

               if(i==1) ans[i][j]=1;
               else if(i==j) ans[i][j]=1;
               else if(n>k) ans[i][j]= i*ans[i][j-1] + ans[i-1][j-1];
               
           }
        }
        
        return ans[k][n];
    }
    
    //https://leetcode.com/problems/partition-to-k-equal-sum-subsets/submissions/
    //https://leetcode.com/problems/paint-house-iii/discuss/2252939/java
    //https://medium.com/@richard.john.grayson/leetcode-1473-paint-house-iii-ec5d3ef5883b

}
