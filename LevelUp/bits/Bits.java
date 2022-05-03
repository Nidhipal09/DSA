import java.util.*;

public class Bits {
    // int mask = 1 << i; //on
    // System.out.println(n | mask);

    // int mask1 = ~(1 << j); //off
    // System.out.println(n & mask1);

    // int mask2 = 1 << k; //toggle
    // System.out.println(n ^ mask2);

    // int mask3 = 1 << m; //check
    // // System.out.println((n&mask3) == 0 ? false : true);
    // System.out.println(~(n | (~mask3)) == 0 ? true : false);

    // rmsbm
    // int rmsbm = 1;

    // while((n&rmsbm) == 0){
    // rmsbm = (rmsbm << 1);
    // }

    // System.out.println(Integer.toBinaryString(rmsbm));

    // rmsbm1
    // int rmsbm = n&(-n);
    // System.out.println(Integer.toBinaryString(rmsbm));

    // count of 1's - kernighans algo
    // int count = 0;
    // while(n!=0){
    // int rmsbm = (n&(-n));
    // rmsbm = ~rmsbm;
    // n = (n&rmsbm);
    // count++;
    // }
    // System.out.println(count);

    // kernighans algo
    // int count = 0;
    // while(n!=0){
    // int rmsbm = (n&(-n));
    // n = n-rmsbm;
    // count++;
    // }
    // System.out.println(count);

    public static int josephus(int n) {
        // find x
        int x = 0;
        while ((int) (Math.pow(2, x)) <= n) {
            x++;
        }
        x--;

        // find l
        int l = n - (int) (Math.pow(2, x));

        int ans = 2 * l + 1; // or (l<<1) + 1
        return ans;
    }

    public static int josephus1(int n) {
        // find x
        int x = 1;
        while (x * 2 <= n) {
            x *= 2;
        }

        // find l
        int l = n - x;

        int ans = 2 * l + 1;
        return ans;
    }

    public List<Integer> grayCode(int n) {
        ArrayList<String> res = grayCodeRecursion(n);
        ArrayList<Integer> ans = new ArrayList<>();
        for (String e : res) {
            ans.add(Integer.parseInt(e, 2));
        }
        return ans;
    }

    public static ArrayList<String> grayCodeRecursion(int n) {
        if (n == 1) {
            ArrayList<String> bl = new ArrayList<>();
            bl.add("0");
            bl.add("1");
            return bl;
        }

        ArrayList<String> l = grayCodeRecursion(n - 1);
        ArrayList<String> ans = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            ans.add("0" + l.get(i));
        }

        for (int i = l.size() - 1; i >= 0; i--) {
            ans.add("1" + l.get(i));
        }

        return ans;
    }

    public List<Integer> grayCode1(int n) {
        if (n == 1) {
            List<Integer> bl = new ArrayList<>();
            bl.add(0);
            bl.add(1);
            return bl;
        }
        if (n == 0) {
            List<Integer> bl = new ArrayList<>();
            return bl;
        }

        List<Integer> l = grayCode1(n - 1);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            ans.add(l.get(i));
        }

        for (int i = l.size() - 1; i >= 0; i--) {
            ans.add(l.get(i) | (1 << (n - 1)));
        }

        return ans;
    }

    static ArrayList<Integer> sol = new ArrayList<>();

    public static void minNoOfSoftwareDevelopers(int[] peopleSkills, int nskills, int cp, ArrayList<Integer> onesol,
            int skillsmask) {
        if (skillsmask == ((1 << nskills) - 1)) { // if nskills==3 then mask = 1000-1 = 0111
            if (sol.size() == 0 || onesol.size() < sol.size())
                sol = new ArrayList<>(onesol);
            return;
        }
        if (cp == peopleSkills.length)
            return;

        minNoOfSoftwareDevelopers(peopleSkills, nskills, cp + 1, onesol, skillsmask);

        onesol.add(cp);
        minNoOfSoftwareDevelopers(peopleSkills, nskills, cp + 1, onesol, skillsmask | peopleSkills[cp]);
        onesol.remove(onesol.size() - 1);

    }

    public static int[] StringArrayToBitsArray(String[] s) {
        int l = s.length;
        int[] bits = new int[l];
        for (int i = 0; i < l; i++) {
            int x = 0;
            for (char ch : s[i].toCharArray()) {
                x = (x | (1 << (ch - 'a')));
            }
            bits[i] = x;
        }
        return bits;
    }

    public static ArrayList<Integer> findNumOfValidWords(String[] words, String[] puzzles) { // O(n2)
        int pl = puzzles.length;
        int wl = words.length;

        ArrayList<Integer> res = new ArrayList<>();

        int[] puzzlesBits = StringArrayToBitsArray(puzzles);
        int[] wordsBits = StringArrayToBitsArray(words);

        for (int i = 0; i < pl; i++) {
            String p = puzzles[i];
            char ch0 = p.charAt(0);
            int count = 0;
            int puzzleBits = puzzlesBits[i];

            for (int j = 0; j < wl; j++) {
                int wordBits = wordsBits[j];
                if ((wordBits & (1 << (ch0 - 'a'))) != 0 && ((wordBits & puzzleBits) == wordBits))
                    count++;
            }
            res.add(count);
        }

        return res;
    }

    public static ArrayList<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), new ArrayList<>());
        }

        for (String word : words) {

            int wmask = 0;
            for (char ch : word.toCharArray()) {
                wmask = (wmask | (1 << (ch - 'a')));
            }

            HashSet<Character> set = new HashSet<>();

            for (char ch : word.toCharArray()) {
                if (!set.contains(ch)) {
                    set.add(ch);

                    map.get(ch).add(wmask);
                }

            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (String puzzle : puzzles) {
            int pmask = 0;
            for (char ch : puzzle.toCharArray()) {
                pmask = (pmask | (1 << (ch - 'a')));
            }

            ArrayList<Integer> al = map.get(puzzle.charAt(0));
            int count = 0;
            for (Integer wmask : al) {
                if ((wmask & pmask) == wmask)
                    count++;
            }
            res.add(count);
        }

        return res;
    }

    public static ArrayList<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        HashMap<Integer, Integer> map = new HashMap<>(); // wordMask, wordMask count
        for (String word : words) {
            int wmask = createMask(word);
            map.put(wmask, map.getOrDefault(wmask, 0) + 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (String puzzle : puzzles) {
            int pmask = createMask(puzzle);

            char firstChar = puzzle.charAt(0);
            int firstCharMask = (1 << (firstChar - 'a'));

            int sub = pmask; // sub
            int count = 0;
            while (sub != 0) {
                if ((sub & firstCharMask) != 0 && map.containsKey(sub)) {
                    count += map.get(sub);
                }

                sub = (sub - 1) & pmask; // generating all subsequences of puzzle
            }

            res.add(count);
        }

        return res;
    }

    public static int createMask(String s) {
        int mask = 0;
        for (char ch : s.toCharArray()) {
            mask = (mask | (1 << (ch - 'a')));
        }
        return mask;
    }

    public static ArrayList<Integer> generateAllSubsequences(String s) {
        ArrayList<Integer> ss = new ArrayList<>();
        int omask = createMask(s), mask = omask;

        while (mask != 0) {
            ss.add(mask);
            mask = (mask - 1) & omask;
        }

        return ss;
    }

    // All Repeating Except One
    // int xor = 0;
    // for(int e: arr){
    // xor = xor^e;
    // }
    // System.out.println(xor);

    public int[] allRepeatingExceptTwo(int[] nums) {
        int xor = 0;
        for (int e : nums)
            xor ^= e;

        int rmsbm = (xor & (-xor));

        int x = 0, y = 0;
        for (int e : nums) {
            if ((e & rmsbm) == 0)
                x ^= e;
            else
                y ^= e;
        }

        return new int[] { x, y };
    }

    public static void oneRepeatingOneMissing(int[] arr) {

        int i = 1, xor = 0;
        for (int e : arr) {
            xor = xor ^ e ^ i;
            i++;
        }

        int rmsbm = xor & (-xor);

        int e1 = 0, e2 = 0;
        for (int e : arr) {
            if ((e & rmsbm) == 0)
                e1 = e1 ^ e;
            else
                e2 = e2 ^ e;
        }

        for (int j = 1; j <= arr.length; j++) {
            if ((j & rmsbm) == 0)
                e1 = e1 ^ j;
            else
                e2 = e2 ^ j;
        }

        for (int e : arr) {
            if (e == e1) {
                System.out.println("Missing Number -> " + e2);
                System.out.println("Repeating Number -> " + e1);
                break;
            } else if (e == e2) {
                System.out.println("Missing Number -> " + e1);
                System.out.println("Repeating Number -> " + e2);
                break;
            }
        }

    }

    public static void allRepeatingThriceExceptOne(int[] arr) {
        int bi = 0;
        for (int i = 0; i < 32; i++) {
            int setBit = 0;
            for (int e : arr) {
                if ((e & (1 << i)) != 0)
                    setBit++;
            }
            if (setBit % 3 != 0)
                bi = bi | (1 << i);
        }
        System.out.println(bi);

    }

    public static void allRepeatingThriceExceptOne1(int[] arr) {
        int n0 = ~0, n1 = 0, n2 = 0;

        for (int e : arr) {
            int n0Cn = n0 & e;
            int n1Cn = n1 & e;
            int n2Cn = n2 & e;

            n0 = n0 & (~n0Cn);
            n1 = n1 | n0Cn;

            n1 = n1 & (~n1Cn);
            n2 = n2 | n1Cn;

            n2 = n2 & (~n2Cn);
            n0 = n0 | n2Cn;
        }
        System.out.println(n1);

    }

    public static void triplets1(int[] arr) { // if x is equal to y then x^y = 0
        int n = arr.length, count = 0;
        for (int i = 0; i <= n - 2; i++) {
            int xor = arr[i];
            for (int k = i + 1; k <= n - 1; k++) {
                xor = xor ^ arr[k];

                if (xor == 0)
                    count += (k - i);
            }
        }
        System.out.println(count);

    }

    public static int reduceNTo1(int n) {
        // if n is small
        int[] ans = new int[n + 1];
        ans[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                ans[i] = ans[i / 2] + 1;
            } else {
                ans[i] = Math.min(ans[i - 1] + 1, ans[(i + 1) / 2] + 2);
            }
        }
        return ans[n];
    }

    // 2 to the power x => 1<<x

    public static int reduceNTo11(long n) { // long becoz if n = Integer.MAX_VALUE then n+1 would be a negative number
                                            // which will never equate to 1 results in an infinite loop
        int op = 0;

        // while(n!=1){
        // if((n%2) == 0) n = n/2;
        // else if(n==3) {
        // op = 2;
        // break;
        // }
        // else if((n%4) == 1) n=n-1;
        // else if((n%4) == 3) n=n+1;

        // op++;
        // }

        // or
        while (n != 1) {
            if ((n & 1) == 0)
                n = n / 2; // even
            else if (n == 3) { // edge case- 3 is 4x+3 type but we get result faster by n-1
                op = 2; // reducing no of operations
                break;
            } else if ((n & 3) == 1)
                n = n - 1; // 4x+1 type
            else if ((n & 3) == 3)
                n = n + 1; // 4x+3 type

            op++;
        }

        return op;
    }

    public static long SameNoOfSetBits(long n, int k, int i) {
        if (i == 0)
            return 0;

        long count = 0, mask = (1L << i);
        if ((n & mask) == 0) {
            count += SameNoOfSetBits(n, k, i - 1);
        } else {
            count += ncr(i, k) + SameNoOfSetBits(n, k - 1, i - 1);
        }

        return count;
    }

    public static long ncr(long n, long r) {
        if (n < r) {
            return 0L;
        }

        long res = 1L;

        for (long i = 0L; i < r; i++) {
            res = res * (n - i); // till n-(r-1) n*(n-1)*(n-2)*----(n-(r-1))
            res = res / (i + 1); // till r 1*2*3------(r)
        }

        return res;
    }

    public static int XorOfSumOfAllPairs(int[] arr) {
        // int xor=0;
        // for(int e: arr){
        // xor = xor^(2*e);
        // }
        // return xor;

        int xor = 0;
        for (int e : arr) {
            xor = xor ^ e;
        }
        return 2 * xor;
    }

    public static void abbreviations(String str) {
        int n = str.length();
        int nss = (1 << n);

        for (int i = 0; i < nss; i++) {
            StringBuilder abbre = new StringBuilder();
            int count = 0;
            for (int j = 0; j < n; j++) {
                int b = n - 1 - j;
                if ((i & (1 << (b))) == 0) {
                    char ch = str.charAt(j);
                    if (count != 0) {
                        abbre.append(count + "" + ch + "");
                        count = 0;
                    } else {
                        abbre.append(ch + "");
                    }

                } else
                    count++;
            }
            if (count != 0)
                abbre.append(count);
            System.out.println(abbre);
        }
    }

    public static boolean utf8Encoding(int[] arr) {
        int rbytes = 0; // remaining bytes

        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];

            if (rbytes == 0) {
                if (e >> 7 == 0b0) { // start of 1 byte
                    rbytes = 0;
                } else if ((e >> 5) == 0b110) { // may be start of 2 byte
                    rbytes = 1;
                } else if ((e >> 4) == 0b1110) { // may be start of 3 byte
                    rbytes = 2;
                } else if ((e >> 3) == 0b11110) { // may be start of 4 byte
                    rbytes = 3;
                }
            } else {
                if ((e >> 6) == 0b10) {
                    rbytes--;
                } else {
                    return false;
                }
            }
        }

        if (rbytes != 0)
            return false; // 110----- 10------ 11100000

        return true;
    }

    public boolean validUtf8(int[] arr) {
        int rbytes = 0; // remaining bytes

        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];

            if (rbytes == 0) {
                if (e >> 7 == 0b0) { // start of 1 byte
                    rbytes = 0;
                } else if ((e >> 5) == 0b110) { // may be start of 2 byte
                    rbytes = 1;
                } else if ((e >> 4) == 0b1110) { // may be start of 3 byte
                    rbytes = 2;
                } else if ((e >> 3) == 0b11110) { // may be start of 4 byte
                    rbytes = 3;
                } else {
                    return false; // ec- no utf like 111111--, 11111---, 11111111
                }
            } else {
                if ((e >> 6) == 0b10) {
                    rbytes--;
                } else {
                    return false; // 110----- 0-------
                }
            }
        }

        if (rbytes != 0)
            return false; // ec- 110----- 10------ 11100000

        return true;
    }

    public static void solveSudoku(int[][] arr, int[] rows, int[] cols, int[][] grid, int i, int j) {

        if (i == arr.length) {
            // display(arr);
            return;
        }

        int nr = 0, nc = 0;

        if (j == 8) {
            nr = i + 1;
            nc = 0;
        } else {
            nr = i;
            nc = j + 1;
        }
        if (arr[i][j] == 0) {
            for (int num = 1; num <= 9; num++) {
                int mask = (1 << num);
                if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (grid[i / 3][j / 3] & mask) == 0) {

                    arr[i][j] = num;

                    rows[i] |= mask;
                    cols[j] |= mask;
                    grid[i / 3][j / 3] |= mask;

                    solveSudoku(arr, rows, cols, grid, nr, nc);

                    rows[i] &= ~mask;
                    cols[j] &= ~mask;
                    grid[i / 3][j / 3] &= ~mask;

                    // rows[i] ^= mask;
                    // cols[j] ^= mask;
                    // grid[i/3][j/3] ^= mask;

                    // solveSudoku(arr, rows, cols, grid, nr,nc);

                    // rows[i] ^= mask;
                    // cols[j] ^= mask;
                    // grid[i/3][j/3] ^= mask;

                    arr[i][j] = 0;
                }
            }
        } else {
            solveSudoku(arr, rows, cols, grid, nr, nc);
        }
    }

    public static void nQueensCanKillComb(boolean[][] board, int r, int cols, int ndiag, int rdiag, String asf) {

        if (r == board.length) {
            System.out.println(asf + ".");
            return;
        }

        for (int c = 0; c < board.length; c++) {

            if ((cols & (1 << c)) == 0 && (ndiag & (1 << (r + c))) == 0
                    && (rdiag & (1 << (r - c + board.length - 1))) == 0) {

                board[r][c] = true;

                cols ^= (1 << c);
                ndiag ^= (1 << (r + c));
                rdiag ^= (1 << (r - c + board.length - 1));

                nQueensCanKillComb(board, r + 1, cols, ndiag, rdiag, asf + r + "-" + c + ", ");

                cols ^= (1 << c);
                ndiag ^= (1 << (r + c));
                rdiag ^= (1 << (r - c + board.length - 1));

                board[r][c] = false;
            }
        }
    }

    // Flip Bits To Convert A To B
    // int flipped = a^b;

    // int count = 0;
    // while(flipped!=0){
    // int rmsbm = (flipped&(-flipped));
    // flipped = flipped-rmsbm;
    // count++;
    // }

    // System.out.println(count);

    // copy set bits in a range
    // int l = right-left+1;
    // int mask = (1<<l);
    // mask -= 1;
    // mask <<= (left-1);

    // mask &= a;
    // mask |= b;

    // System.out.println(mask);

    public static boolean isAPowerOf2(int n) {
        if (n <= 0)
            return false;
        return (n & (n - 1)) == 0;
    }

    public static boolean isPowerOfFour(int n) {
        if (!isAPowerOf2(n))
            return false;
        n -= 1;
        return countOfSetBits(n) % 2 == 0;
    }

    static int countOfSetBits(int n) {
        int count = 0;
        while (n != 0) {
            int rmsbm = (n & (-n));
            n = n - rmsbm;
            count++;
        }
        return count;
    }

    // solve 7n by 8
    // int nBy8 = (n >> 3);
    // System.out.println((n - nBy8)-1);

    public static int swapEvenAndOddBits(int n) {
        int evenMask = 0xAAAAAAAA, oddMask = 0x55555555;
        int evenBits = n & evenMask, oddBits = n & oddMask;

        evenBits >>= 1;
        oddBits <<= 1;

        int ans = evenBits | oddBits;
        return ans;
    }

    public static long SumOfBitDifferencesOfAllPairs(int[] arr){
        long ans = 0;  
        
        for(int i=0; i<32; i++){
            int mask = (1<<i);
            long zero = 0, one = 0; // can't use int becoz arr length can be very long 
            for(int e: arr){
               if((e&mask) == 0) zero++;
               else one++;
            }
            ans += zero*one*2;
        }
        return ans;
      }

      public static int countAllSetBitsInFirstNNums(int n){
        if(n==0 || n==1) return n;  
          
        int highestPowOf2 = highestPowerOfTwo(n);
        int res = (1<<(highestPowOf2-1))*highestPowOf2 + 
                  (n-(1<<highestPowOf2)+1) +           
                   countAllSetBitsInFirstNNums(n-(1<<highestPowOf2));     
        return res;
      }
      
      public static int highestPowerOfTwo(int n){
         int p=0;
         while((1<<p) <= n){
             p++;
         }
         
         return p-1;
      }

      public static void printBinaryAndReverseBits(int n){
        boolean found1 = false;
        int rev = 0, ri = 0;
        
        for(int i=31; i>=0; i--){
            int mask = (1<<i);
            
            if(found1){
               if((n&mask) == 0){
                  System.out.print(0);
                  
                  ri++;
               }
               else{
                  System.out.print(1); 
                  
                  rev |= (1<<ri);
                  ri++;
               } 
            }
            else{
               if((n&mask) == 0){
               }
               else{
                  System.out.print(1); 
                  found1=true;
                  
                  rev |= (1<<ri);
                  ri++;
               } 
            }
            
        }
        
        System.out.println("\n"+rev);
      }

      public static void printBinaryAndReverseBits1(int n){
        boolean found1 = false;
        int rev = 0, ri = 0, mask = (1<<31);
        
        for(int i=31; i>=0; i--){
            
            if(found1){
               if((n&mask) == 0){
                  System.out.print(0);
                  
                  ri++;
               }
               else{
                  System.out.print(1); 
                  
                  rev |= (1<<ri);
                  ri++;
               } 
            }
            else{
               if((n&mask) == 0){
               }
               else{
                  System.out.print(1); 
                  found1=true;
                  
                  rev |= (1<<ri);
                  ri++;
               } 
            }
            
            mask >>>= 1;
        }
        
        System.out.println("\n"+rev);
      }

      public int reverseBits(int n) {
        int rev = 0, mask = (1<<31);

        for(int i=31; i>=0; i--){
           if((n&mask) != 0) rev |= (1<<(31-i));
           mask >>>= 1;
        }
        return rev;
    
    }

    public static void minXorPairs(int[] arr) {
        Arrays.sort(arr); // nlogn
        
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> al = new ArrayList<>();
        
        for(int i=0; i<arr.length-1; i++){ //n
            int xor = arr[i]^arr[i+1];
            
            if(xor<min){
                min = xor;
                al = new ArrayList<>();
                al.add(arr[i]);
                al.add(arr[i+1]);
            }
            else if(xor<=min){
                al.add(arr[i]);
                al.add(arr[i+1]);
            }
        }
        
        int j=0;
        while(j<al.size()){
            System.out.println(al.get(j)+", "+al.get(j+1));
            j += 2;
        }
        
    }
        
    }

    public static int NthPalindromicBinary(int n) {
        int len = 1, noOfElementsTillNow = 1, noOfElementsInThisGroup = 1;
        
        while(noOfElementsTillNow<n){
            len++;
            noOfElementsInThisGroup = (1<<((len-1)/2));
            noOfElementsTillNow += noOfElementsInThisGroup;
        }
        
        int startOfThisGroup = noOfElementsTillNow-noOfElementsInThisGroup+1;
        
        int offset = n-startOfThisGroup;
        
        int ans = (1<<(len-1));
        ans |= (offset<<(len/2));
        
        int rev = getRev(ans>>((len+1)/2));
        ans |= rev;
        
        return ans ; 
        
    }
    
    static int getRev(int n){
        int rev = 0, prevRev = 0;
        while(n!=0){
            int lb = (n&1);
            rev |= lb;
            
            prevRev = rev;
            rev <<= 1;
            n >>= 1;
        }
        
        return prevRev;
    }

    public static int NthPalindromicBinary1(int n) {
        int len = 1, noOfElementsTillNow = 1, noOfElementsInThisGroup = 1;
        
        while(noOfElementsTillNow<n){
            len++;
            noOfElementsInThisGroup = (1<<((len-1)/2));
            noOfElementsTillNow += noOfElementsInThisGroup;
        }
        
        noOfElementsTillNow -= noOfElementsInThisGroup;
       
        Queue<StringBuilder> q = palindromicStrings(len);
        
        int num = 0;
        while(noOfElementsTillNow++<n){
            num = Integer.parseInt(q.remove().toString(), 2);
        }
        return num;
    } 
    
    static Queue<StringBuilder> palindromicStrings(int n){
        if(n==1){
            Queue<StringBuilder> bq = new ArrayDeque<>();
            bq.add(new StringBuilder("1"));
            return bq;
        }else if(n==2){
            Queue<StringBuilder> bq = new ArrayDeque<>();
            bq.add(new StringBuilder("11"));
            return bq;
        }
        
        Queue<StringBuilder> resq = new ArrayDeque<>();
        Queue<StringBuilder> q = palindromicStrings(n-1);
        
        if((n&1)==0){
            while(q.size()>0){
                StringBuilder s = q.remove();
                s.insert(n/2, s.charAt(n/2-1));
                resq.add(s);
            }     
        }
        else{
            while(q.size()>0){
                StringBuilder s0 = q.remove();
                s0.insert(n/2, "0");
                resq.add(s0);
                StringBuilder s1 = new StringBuilder(s0);
                s1.deleteCharAt(n/2);
                s1.insert(n/2, "1");
                resq.add(s1);
            }  
        }
        
        return resq;
    } 

    public int[] xorQueries(int[] arr, int[][] queries) {
        
        int n = arr.length; 
        int[] xorarr = new int[n];
        
        int xor = 0;
        for(int i=0; i<n; i++){
            xor ^= arr[i];
            xorarr[i] = xor;
        }
        
        int ql = queries.length;
        int[] ans = new int[ql];
        
        int lefti = 0, righti = 0;
        for(int i=0; i<ql; i++){
            lefti = queries[i][0];
            righti = queries[i][1];
            
            ans[i] = (lefti==0 ? xorarr[righti] : xorarr[lefti-1]^xorarr[righti]);
        }
        
        return ans;
    }

    public int[] xorQueries1(int[] arr, int[][] queries) {
        
        int n = arr.length;
        int xor = 0;
        for(int i=0; i<n; i++){
            xor ^= arr[i];
            arr[i] = xor;
        }
        
        int ql = queries.length;
        int[] ans = new int[ql];
        
        int lefti = 0, righti = 0;
        for(int i=0; i<ql; i++){
            lefti = queries[i][0];
            righti = queries[i][1];
            
            ans[i] = (lefti==0 ? arr[righti] : arr[lefti-1]^arr[righti]);
        }
        
        return ans;
    }

    public int minFlips(int a, int b, int c) {
        int aORb = (a|b);  
        int xorWithC = aORb^c;  
        
        int count = 0;
        while(xorWithC!=0){
            int rmsbm = xorWithC&(-xorWithC);  //first position where c differs xorWithC;
            
            int idx = powerOf2(rmsbm);  // position at which difference occurs
            if((c&(1<<idx))==0){  // bit at idx in c is 0
                if((a&(1<<idx))!=0 && (b&(1<<idx))!=0) count+=2;  // bit at idx in a and b is 1, switch both of them off
                else count++; // bit at idx in a is 1 or  bit at idx in b is 1 
            }
            else{  // bit at idx in c is 1
                count++;  // switch either bit at idx in a 1 or  bit at idx in b is 1 
            }
            
            xorWithC &= ~rmsbm; // move to next bit at which difference occurs
        }
        
        return count;
    }
    
    int powerOf2(int n){  // 1000 p=3
        int p = 0;
        while(n!=0){
            n >>>= 1;
            p++;
        }
        return p-1;
    }

    public int minFlips1(int a, int b, int c) {
       
        int count=0;
        
        while(a!=0 || b!=0 || c!=0){
            int lastBitOfA = (a&1);
            int lastBitOfB = (b&1);
            int lastBitOfC = (c&1);
            
            int or = lastBitOfA|lastBitOfB;
            
            if(or!=lastBitOfC){
               if(lastBitOfC==1){
                   count++;
               } 
               else{
                   if(lastBitOfA==1 && lastBitOfB==1) count+=2;
                   else count++;
               }
            }
            
            a >>>= 1;
            b >>>= 1;
            c >>>= 1;
            
        }
        
        return count;
    
    }

    public int minFlips2(int a, int b, int c) {
       
        int count=0;
        boolean lastBitOfA = false, lastBitOfB = false, lastBitOfC = false;
        while(a!=0 || b!=0 || c!=0){
             lastBitOfA = (a&1)!=0;
             lastBitOfB = (b&1)!=0;
             lastBitOfC = (c&1)!=0;
            
            if(lastBitOfC){  //1
               if(!lastBitOfA && !lastBitOfB) count++;
            }
            else{ //0
               if(lastBitOfA && lastBitOfB) count+=2;
               else if(lastBitOfA || lastBitOfB) count++; 
            }
            a >>>= 1;
            b >>>= 1;
            c >>>= 1;
            
        }
        
        return count;
    
    }









}