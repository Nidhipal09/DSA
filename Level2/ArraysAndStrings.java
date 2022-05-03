public class ArraysAndStrings {

    public static boolean isPossible(String name, String typed) {
        int ln = name.length(), lt = typed.length();

        if (ln > lt)
            return false;

        int i = 0, j = 0;
        while (i < ln && j < lt) {
            char cn = name.charAt(i);
            char ct = typed.charAt(j);

            if (cn == ct) {
                i++;
                j++;
            } else if (i > 0 && ct == name.charAt(i - 1)) {
                j++;
            } else
                return false;
        }

        while (j != lt) {
            if (name.charAt(i - 1) != typed.charAt(j))
                return false;
            j++;
        }
        return i == ln;
    }

    public static int[] getModifiedArray(int length, int[][] queries) {
        int[] ans = new int[length];

        int si = 0, ei = 0, x = 0;
        for (int i = 0; i < queries.length; i++) { // impact
            si = queries[i][0];
            ei = queries[i][1];
            x = queries[i][2];

            ans[si] += x;
            if (ei != length - 1)
                ans[ei + 1] -= x;
        }

        int sum = ans[0];
        for (int i = 1; i < length; i++) { // prefix sum array
            sum += ans[i];
            ans[i] = sum;
        }

        return ans;
    }

    public static int mostWater(int[] heights) {
        int n = heights.length, i = 0, j = n - 1, area = 0;

        while (i != j) {
            int hi = heights[i], hj = heights[j];

            if (hi <= hj) {
                area = Math.max(area, hi * (j - i));
                i++;
            } else {
                area = Math.max(area, hj * (j - i));
                j--;
            }
        }
        return area;
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length, i = 0, j = n - 1, p = n - 1;
        int[] ans = new int[n];

        while (i <= j) {
            int x = nums[i] * nums[i], y = nums[j] * nums[j];

            if (x >= y) {
                ans[p] = x;
                i++;
            } else {
                ans[p] = y;
                j--;
            }
            p--;
        }
        return ans;
    }

    public static int potentialValidElement(int[] arr) {
        int val = arr[0], count = 1, n = arr.length;

        for (int i = 1; i < n; i++) {
            if (val == arr[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                val = arr[i];
                count = 1;
            }
        }

        return val;
    }

    public static void printMajorityElement(int[] arr) {
        int val = potentialValidElement(arr);
        int count = 0;

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (val == arr[i]) {
                count++;
            }
        }

        if (count > n / 2)
            System.out.println(val);
        else
            System.out.println("No Majority Element exist");
    }

    public static ArrayList<Integer> majorityElement2(int[] arr) {
        // write your code here

        int val1 = arr[0], count1 = 1, n = arr.length;
        int val2 = 0, count2 = 0;

        for (int i = 1; i < n; i++) {
            int e = arr[i];

            if (e == val1)
                count1++;
            else if (e == val2)
                count2++;
            else {
                if (count1 == 0) {
                    val1 = arr[i];
                    count1 = 1;
                } else if (count2 == 0) {
                    val2 = arr[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        // val1 , val2 -> potential candidates
        ArrayList<Integer> ans = new ArrayList<>();

        if (isFreqGreater(arr, val1))
            ans.add(val1);
        if (isFreqGreater(arr, val2))
            ans.add(val2);

        return ans;
    }

    public static boolean isFreqGreater(int[] arr, int val) {
        int count = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == val)
                count++;
        }

        return count > n / 3;
    }

    public static ArrayList<Integer> majorityElement(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
           int e = arr[i];
           if(map.containsKey(e)){
               int f = map.get(e);
               map.put(e, f+1);
           }
           else map.put(e, 1);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(Integer key: map.keySet()){
            int f = map.get(key);
            if(f > n/k) ans.add(key); 
        }
        
        return ans;
    }    

    public static int[] productExceptSelf(int[] arr) {
        int n = arr.length;
        
        int[] rp = new int[n];  //right product
        rp[n-1]=arr[n-1];
        
        for(int i=n-2; i>=0; i--){
            rp[i] = rp[i+1]*arr[i];
        }
        
        int lp=arr[0]; //left product
        int[] ans = new int[n];
        ans[0] = rp[1];
        
        for(int i=1; i<n; i++){
            if(i==n-1) ans[i] = lp*1; 
            else ans[i] = lp*rp[i+1];
            
            lp *= arr[i];
        }
        
        return ans;
    }

    public static int maximumProduct(int[] arr) {
        int n = arr.length;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        
        for(int i=0; i<n; i++){
            int e = arr[i];
            
            //min
            if(e<min1){
                min2=min1;
                min1=e;
            }
            else if(e<min2){
                min2=e;
            }
            
            //max
            if(e>max1){
                max3=max2;
                max2=max1;
                max1=e;
            }
            else if(e>max2){
                max3=max2;
                max2=e;
            }
            else if(e>max3){
                max3=e;
            }
        }
        
        int withNegative = min1*min2*max1;
        int onlyPositive = max1*max2*max3;
        int maxProduct = Math.max(withNegative, onlyPositive);
        
        return maxProduct;
    }

    public static void sortArrayByParity(int[] nums) {
        int n = nums.length, i=0, j=0;
        
        while(i<n){
           int e = nums[i];
           
           if(e % 2 == 0){
              int temp = nums[i];
              nums[i] = nums[j];
              nums[j] = temp;
              
              i++;
              j++;
           }
           else i++;
        }
    }

    public static void printPrimeUsingSieve(int n) {
        boolean[] ans = new boolean[n+1];
        Arrays.fill(ans,true);
        
        for(int i=2; i*i<=n+1; i++){
            if(isPrime(i)){
                for(int j=i+i; j<=n; j+=i){
                    ans[j] = false;
                }
            }
        }
        
        for(int i=2; i<=n; i++){
            if(ans[i]) System.out.print(i+" ");
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
       int[][] ans = new int[n][m];
       
       for(int j=0; j<n; j++){
          for(int i=0; i<m; i++){
             ans[j][i] = matrix[i][j];
          }         
       }
       
       return ans;
    }

    public static void transpose1(int[][] matrix) {
        int n = matrix.length;
        
        for(int i=0; i<n; i++){
           for(int j=0; j<i; j++){
             int temp = matrix[i][j];
             matrix[i][j] = matrix[j][i];
             matrix[j][i] = temp;
           }   
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
           
           for(int i=0; i<n; i++){
              for(int j=0; j<i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
              }   
           }
           
           int j=0; 
           while(j<n/2){
               for(int i=0; i<n; i++){
                   int temp = matrix[i][j];
                   matrix[i][j] = matrix[i][n-1-j];
                   matrix[i][n-1-j] = temp;
               }
               j++;
           }
     }

     

    



}
