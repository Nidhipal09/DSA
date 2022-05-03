package Basics;
import java.util.Scanner;

public class FunctionAndArrays {

    public static void main(String args[]){  // program to find permutation
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // int r = sc.nextInt();

        // int nfact = 1;
        // for(int i=n; i>1; i--){    
        //     nfact *= i;
        // } 

        
        // // writing same thing again with n-r- avoiding DRY
        // // limitation of this approach- if there is any bug in the factorial code or there is any updation in factorial
        // // code in the future you have to update it in all places wherever its code is present.
        // int nminusrfact = 1;
        // for(int i=n-r; i>1; i--){   
        //     nminusrfact *= i;
        // }

        // System.out.println(nfact/nminusrfact);



        //function-
        // int nfact = fact(n);
        // int nminusrfact = fact(n-r);
        // System.out.println(nfact/nminusrfact);
        System.out.println(anybaseMulti(637,11,5));
    }

    static int anyBaseAddition1(int n1, int n2, int b){
        int c=0, ans=0, pow=1;
        while(n1>0 || n2>0 || c>0){
            int d1 = n1 % 10;
            int d2 = n2 % 10;

            int num = d1+d2+c;
            if(num>=b){
              c=1;
            }else{
              c=0;
            }

            ans += (num%b)*pow; 
            
            pow *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }

    static int anyBaseSubtraction1(int n1, int n2, int b){  // n2- big n1-small
        int c=0, ans=0, pow=1;
        while(n2>0){
            int d1 = n1 % 10;
            int d2 = n2 % 10;

            int num=d2+c;
            if(num < d1){
                num += b;
                c=-1;
            }
            else{
                c=0;
            }

            num = num-d1; 

            ans += num*pow;
            
            pow *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }

    static int anybaseMulti(int n1, int n2, int b){
        int pow=1, ans=0;
        while(n2>0){
            int d2 = n2%10;
            int num = multiWithSingleDigit(n1, d2, b);
            num *= pow;
            ans = anyBaseAddition1(ans, num, b);

            pow*=10;
            n2/=10;
        }
        return ans;
    }

    static int multiWithSingleDigit(int n, int d, int b){
        int c=0, ans=0, pow=1; 
        while(n>0 || c>0){
            int nd = n%10;
            
            int num = nd*d + c;
            ans += (num%b)*pow;
            c = num/b;

            pow*=10;
            n/=10;
        }
        return ans;
    }

    static int fact(int n){
        int ans = 1;
        for(int i=n; i>=2; i--) ans*=i;
        return ans;
    }



    public static int getDigitFrequency(int n, int d) {
        int count = 0;

        while (n > 0) {
            int r = n % 10;
            if (r == d)
                count++;

            n = n / 10;
        }

        return count;
    }

    public static int getValueInBase(int n, int b) {

        int num = 0, pow = 0;

        while (n > 0) {
            int r = n % b;
            num += r * Math.pow(10, pow);
            pow++;

            n /= b;
        }

        return num;
    }

    public static int getValueIndecimal(int n, int b) {
        int num = 0, pow = 0;

        while (n > 0) {
            int r = n % 10;
            num += r * Math.pow(b, pow);
            pow++;

            n /= 10;
        }

        return num;
    }

    public static int anyBaseAddition(int b, int n1, int n2) {
        int num = 0, pow = 0;

        int carry = 0;
        while (n1 > 0 || n2 > 0 || carry > 0) {
            int r1 = n1 % 10;
            int r2 = n2 % 10;

            int val = r1 + r2 + carry;

            int r = val % b;
            num += r * Math.pow(10, pow);
            pow++;

            carry = val / b;

            n1 /= 10;
            n2 /= 10;
        }

        return num;
    }

    public static int anyBaseSubtraction(int b, int n2, int n1) {
        int num = 0, pow = 1;

        int carry = 0;
        while (n1 > 0) {
            int r1 = n1 % 10;
            int r2 = n2 % 10;

            int val = 0;
            r1 = r1 + carry;
            if (r1 < r2) {
                val = (r1 + b) - r2;
                carry = -1;
            }

            else {
                val = (r1 - r2);
                carry = 0;
            }

            num += val * pow;
            pow *= 10;

            n1 /= 10;
            n2 /= 10;
        }

        return num;
    }

    public static int anyBaseMultiplication(int b, int n1, int n2) {
        int num = 0, p = 1;

        while (n2 > 0) {
            int d = n2 % 10;
            int pro = getProductWithSingleDigitOfn2(b, n1, d);
            pro *= p;
            p *= 10;
            num = anyBaseAddition(b, num, pro);
            n2 /= 10;
        }

        return num;
    }

    public static int getProductWithSingleDigitOfn2(int b, int n1, int d2) {
        int num = 0, carry = 0, p = 1;

        while (n1 > 0 || carry > 0) {
            int d = n1 % 10;
            int val = d * d2;
            val += carry;

            carry = val / b;
            num += (val % b) * p;
            p *= 10;

            n1 /= 10;
        }

        return num;
    }

    public static int span(int[] arr) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int val : arr) {
            if (val > max)
                max = val;
            if (val < min)
                min = val;
        }

        return max - min;
    }

    public static void barChart(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int val : arr) {
            if (val > max)
                max = val;
        }

        for (int i = max; i >= 1; i--) {
            for (int val : arr) {
                if (val >= i)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void sumOfArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] sum = new int[n1 > n2 ? n1 : n2];

        int i = n1 - 1, j = n2 - 1, k = sum.length - 1;
        int c = 0, val = 0;

        while (k >= 0) {
            val = c;
            val += (i >= 0 ? arr1[i] : 0) + (j >= 0 ? arr2[j] : 0);

            int d = val % 10;
            sum[k] = d;

            c = val / 10;

            i--;
            j--;
            k--;
        }

        if (c > 0) {
            System.out.println(c);
        }
        for (int e : sum)
            System.out.println(e);
    }

    public static void diffOfArrays(int[] arr1, int[] arr2) {   //arr2-arr1
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] diff = new int[n2];

        int i = n1 - 1, j = n2 - 1, k = diff.length - 1;
        int c = 0, val = 0;

        while (k >= 0) {

            if (i >= 0) {
                if (arr2[j] + c >= arr1[i]) {
                    val = arr2[j] + c - arr1[i];
                    c = 0;
                } else {
                    val = arr2[j] + c + 10 - arr1[i];
                    c = -1;
                }
            } else {
                val = arr2[j] + c;
                c = 0;
            }

            diff[k] = val;

            i--;
            j--;
            k--;
        }

        int zeroindex = 0;
        for (int e : diff) {
            if (e != 0)
                break;
            else
                zeroindex++;
        }

        for (int x = zeroindex; x < diff.length; x++) {
            System.out.println(diff[x]);
        }
    }

    public static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public static void rotate(int[] arr, int k) {
        k %= arr.length;
        if (k < 0)
            k += arr.length;

        reverseHelper(arr, 0, arr.length - k - 1);
        reverseHelper(arr, arr.length - k, arr.length - 1);
        reverseHelper(arr, 0, arr.length - 1);
    }

    public static void reverseHelper(int[] arr, int x, int y) {
        int i = x, j = y;

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }


    public static void inverseAnArray(int[] arr) {
        int n = arr.length;
        int []narr= new int[n];

        for(int i=0; i<n; i++){
            int ni = arr[i];
            int nv = i;
            narr[ni] = nv;
        }
    }


    public static void subsetsOfAnArray1(int[] arr) {    // identify the flaws
        int n = arr.length;
        
        int nss = (int)Math.pow(2,n); // no of subsets

        for(int i=0; i<nss; i++){

            for(int j=n-1; j>=0; j--){
                int rem = i % 2;
                i /= 2;

                if(rem==0) System.out.print("\t");
                else System.out.print(arr[j]);
            }
            System.out.println();
        }
    }

    public static void subsetsOfAnArray(int[] arr) {
        int n = arr.length;

        int ss = (int) Math.pow(2, n);
        for (int i = 0; i < ss; i++) {
            String ans = "";
            // convert i to binary and get 0's and 1's to decide whether to print
            // the element or not

            int temp = i;
            for (int j = n - 1; j >= 0; j--) {
                int r = temp % 2;
                temp = temp / 2;

                if (r == 0)
                    ans = "-\t" + ans;
                else
                    ans = arr[j] + "\t" + ans;
            }
            System.out.println(ans);
        }
    }

    public static void ceilAndFloor(int[] arr, int d) {
        int ceil = Integer.MAX_VALUE; // min of bigger numbers
        int floor = Integer.MIN_VALUE; // max of smaller numbers

        for (int val : arr) {
            if (val == d) {
                System.out.println(val);
                break;
            } else {
                if (val < d && val > floor)
                    floor = val;
                else if (val > d && val < ceil)
                    ceil = val;
            }
        }

        System.out.println(ceil);
        System.out.println(floor);
    }
 
    public static void binarySearch(int[] arr, int d) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (low < high) {
            mid = (low + high) / 2;
            if (d < arr[mid]) {
                high = mid - 1;
            } else if (d > arr[mid]) {
                low = mid + 1;
            } else {
                System.out.println(mid);
                return;
            }
        }
    }

    public static void binarySearchceilAndFloor(int[] arr, int d) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;

        while (low <= high) {
            mid = (low + high) / 2;
            if (d < arr[mid]) {
                ceil = arr[mid];
                high = mid - 1;
            } else if (d > arr[mid]) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                System.out.println(mid);
                return;
            }
        }

        System.out.println(ceil);
        System.out.println(floor);
    }

    public static void binarySearchfirstAndLastIndex(int[] arr, int d) {
        int low = 0, high = arr.length - 1, mid = 0, fi = -1, li = -1;

        while (low <= high) {
            mid = (low + high) / 2;

            if (d < arr[mid]) {
                high = mid - 1;
            } else if (d > arr[mid]) {
                low = mid + 1;
            } else {
                fi = mid;
                high = mid - 1;
            }
        }

        System.out.println(fi);

        low = 0;
        high = arr.length - 1;
        mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (d < arr[mid]) {
                high = mid - 1;
            } else if (d > arr[mid]) {
                low = mid + 1;
            } else {
                li = mid;
                low = mid + 1;
            }
        }

        System.out.println(li);

    }

    static void invertedChart(int[] arr){
        int max = -1;
        for(int e: arr){
            max = e > max ? e : max;
        }

        for(int i=1; i<=max; i++){
            for(int j=0; j<arr.length; j++){
                if(arr[j] >= i) System.out.print("* ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
