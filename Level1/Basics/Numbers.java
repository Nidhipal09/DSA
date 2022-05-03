package Basics;

public class Numbers {

    //  / - quotient and % - remainder.

    public static boolean isPrime(int n) {  //ts - sqrt(n)
        for(int div=2; div*div <= n; div++){   // div <= root(n)
            if(n%div == 0){
               return false;
            }
        }
        return true;
    }

    //   int div = 2;
    //   while (div * div <= n) {
    //       if (n % div == 0) {
    //         break;
    //       }
    //    div++;
    //   }

    //   if (div * div > n) System.out.println("prime");
    //   else System.out.println("not prime");
    

    public static void allPrimesTillN(int a, int b) {  // create a filter
        for(int n=a; n<=b; n++){

            int status=0;
            for(int div=2; div*div <= n; div++){
                if(n%div == 0){
                    status=1;
                    break;
                }
            }

            if(status==0) System.out.println(n);
        }
    }

    public static void fib(int n) {
        int a=0, b=1, next=0;
        for(int i=1; i<= n; i++){
            System.out.println(a);
            next=a+b;
            a=b;
            b=next;
        }
    }

    public static int countDigits(int n){
        int count = 0 ;
        while(n!=0){
            n /= 10;
            count++;
        }
        return count;
    }

    public static void printDigits(int n) {
        int count=0, temp=n;
        while(temp>0){
            temp/=10;
            count++;
        }
        
        int div=(int)Math.pow(10, count-1);

        while(div>0){
           System.out.println(n/div);
           n %= div;
           div /= 10;
        }
    }

    static void pd(int n){
        if(n==0) return;
        pd(n/10);
        System.out.println(n%10);
    }

    static void reverse(int n){
        int count=0, temp=n;
        while(temp>0){
            temp/=10;
            count++;
        }
        
        int mul=(int)Math.pow(10, count-1);

        int ans = 0;
        while(n!=0){
            int rem = n % 10;
            ans += rem * mul;
            mul /= 10;
            n /= 10;
        }

    }

    public static int inverse(int n) {
        
        int i=1, num=0;

        while(n>0){
            int d=n % 10;
            num += i*Math.pow(10, d-1);
            i++;
            n /= 10;
           
        }
        
        return num;
        
    }

    public static int rotate(int n, int k) {
        
        int count=0, temp=n;
        while(temp>0){
            temp /=10;
            count++;
        }
        
        k %= count;
        if(k<0) k += count;

        int div = (int)Math.pow(10, k);
        int fp = n % div;  // ans's first part
        int sp = n / div;  // ans's second part

        int div1 = (int)Math.pow(10, count);
        int mul = div1/div;
        int num = fp * mul + sp;

        return num;
    }

    public static void GCDandLCM(int a, int b) {
        
        int x=a;
        int y=b;

        while(x!=0){  // GCD
            int rem = y % x;
            y = x;
            x = rem;
        }

        System.out.println(y);
        System.out.println(a*b / y);
    }
    
    public static void factorisation(int n) {   
        //We divide the given number by the smallest number and keep on dividing till it no longer could be divided by that number.
        for(int i=2; i*i<=n; i++){
            while(n%i == 0){
                System.out.print(i+" ");
                n /= i;
            }
        }
        
        if(n!=1) System.out.print(n);  // edge cases eg-12 (rem = 3), 15 (rem = 5), 46 (rem = 23)
    }

    public static void benjaminBulbs(int n) {
        
        for(int i=1; i*i <= n; i++){
            System.out.println(i*i);
        }
    }

}
