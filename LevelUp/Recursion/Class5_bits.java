public class Class5_bits {
    public static void main(String[] args){
        // System.out.println(5<<2);  //left shift
        // System.out.println(20>>2); //right shift

        System.out.println(setON(5, 1));  // 7
        System.out.println(setOFF(2, 1)); // 0
        System.out.println(EvenOrOdd(110000)); 
    }

    public static int setON(int x, int idx){
        int mask = 1<<idx;
        return x|mask;
    }

    public static int setOFF(int x, int idx){
        int mask = 1<<idx;
        mask = ~mask;
        return x&mask;
    }

    public static int check(int x, int idx){
        int mask = 1<<idx;
        if((x&mask) == 0) return 0;
        else return 1;
    }

    public static String EvenOrOdd(int x){
        if((x&1) == 0) return "Even";
        else return "Odd";
    }

    public static int multiplyBy2(int x, int powerOf2){
        return 1<<powerOf2;
    }

    public static int divideBy2(int x, int powerOf2){
        return 1>>powerOf2;
    }

    public static boolean isAPowerOf2(int n){
        if(n<=0) return false;
        return (n&(n-1)) == 0;
    }

    public static boolean isAPowerOf4(int n){
        if(n<=0) return false;
        if(!isAPowerOf2(n)) return false;

        int countOfZeros = 0;
        while(n!=0){
            if((n&1) != 1) countOfZeros++;
            n >>>= 1;  //always append 0 on the left ,  >> append 0 if msb = 0 and append 1 if msb is 1
        }
 
        return (countOfZeros&1) == 0;   // countOfZeros has to be even
    }

    // 136, 268, 191, 338
    public int singleNumber(int[] nums) {
        int n=0;
        for(int e: nums){
            n ^= e;
        }
        return n;
    }

    public int missingNumber(int[] nums) {
        int ans = 0; 
        for(int i=0; i<nums.length; i++){
            ans ^= nums[i];
            ans ^= i;
        }  
         
        return ans^nums.length; 
     }

     public int hammingWeight(int n) {
        int countOf1 = 0;
        for(int i=0; i<32; i++){
           if((n&(1<<i))!=0) countOf1++;
        }
        return countOf1;
    }
    
    public int hammingWeight1(int n) {
        int countOf1 = 0;
        
        while(n!=0){
           if((n&1)!=0) countOf1++;
           n >>>= 1; 
        }
        
        return countOf1;
    }
    
    
    public int hammingWeight2(int n) {
        int countOf1=0;
        
        while(n!=0){
            n &= (n-1);
            countOf1++;
        }
        
        return countOf1;
    }

    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        
        ans[0] = 0;
        
        for(int i=1; i<=n; i++){
            int x = i&(i-1);
            ans[i] = ans[x]+1;
        }
        
        return ans;
    }

    //260
    public int[] singleNumber1(int[] nums) {
        int xor = 0;
        for(int e: nums) xor ^= e;
        
        int last1bit = (xor & (-xor));
        
        int x=0, y=0;
        for(int e: nums){
            if((e&last1bit) == 0) x ^= e;
            else y ^= e;
        }
        
        return new int[]{x,y};
    }





}
