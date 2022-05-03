import java.util.*;

public class Class4_sudoku {
    public static void main(String[] args) {
        System.out.println((char) ('0' + 2)); // int to char= (char)('0'+num)
        System.out.println(('2' - '0')); // char to int= ch-'0'
    }

    public static ArrayList<Integer> storeIdicesOfBlankSpaces(char[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>(); // of blank spaces
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == '.') {
                    al.add(i * 9 + j);
                }
            }
        }

        return al;
    }

    public static boolean sudokuSolver(char[][] matrix, ArrayList<Integer> al, int idx) {
        if (idx == al.size()) {
            return true;
        }

        int r = al.get(idx) / 9;
        int c = al.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (canPlaceHereNum(matrix, r, c, num)) {
                matrix[r][c] = (char) ('0' + num);
                if (sudokuSolver(matrix, al, idx + 1))
                    return true;
                matrix[r][c] = '.';
            }
        }

        return false;
    }

    public static boolean canPlaceHereNum(char[][] matrix, int r, int c, int num) { // O(27)
        for (int i = 0; i < 9; i++) {
            if (matrix[i][c] == '0' + num)
                return false;
        }

        for (int j = 0; j < 9; j++) {
            if (matrix[r][j] == '0' + num)
                return false;
        }

        int sr = r / 3 * 3;
        int sc = c / 3 * 3;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (matrix[sr + i][sc + j] == '0' + num)
                    return false;
            }
        }

        return true;
    }

    //2 way
    public static ArrayList<Integer> storeIdicesOfBlankSpaces1(char[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>(); // of blank spaces
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                char ch = matrix[i][j];
                if (ch == '.') {
                    al.add(i * 9 + j);
                }else{
                    int num= ch-'0';
                    rows[i][num] = cols[j][num] = mats[i/3][j/3][num] = true;
                }
            }
        }

        return al;
    }

    static boolean[][] rows = new boolean[10][10]; 
    static boolean[][] cols = new boolean[10][10];
    static boolean[][][] mats = new boolean[3][3][10];


    public static boolean sudokuSolver1(char[][] matrix, ArrayList<Integer> al, int idx) {
        if (idx == al.size()) {
            return true;
        }

        int r = al.get(idx) / 9;
        int c = al.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (!rows[r][num] && !cols[c][num] && !mats[r/3][c/3][num]) {
                matrix[r][c] = (char) ('0' + num);

                rows[r][num] = cols[c][num] = mats[r/3][c/3][num] = true;
                

                if (sudokuSolver(matrix, al, idx + 1))
                    return true;

                rows[r][num] = cols[c][num] = mats[r/3][c/3][num] = false;

                matrix[r][c] = '.';
            }
        }

        return false;
    }

    //3 way
    public  ArrayList<Integer> storeIdicesOfBlankSpacesUsingBits(char[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>(); // of blank spaces
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                char ch = matrix[i][j];
                if (ch == '.') {
                    al.add(i * 9 + j);
                }else{
                    int num= ch-'0';
                    int mask = (1<<num);
                    rowsb[i] |= mask;
                    colsb[j] |= mask;
                    matsb[i/3][j/3] |= mask;
                }
            }
        }

        return al;
    }

     int[] rowsb = new int[10]; 
     int[] colsb = new int[10];
     int[][] matsb = new int[3][3];


    public  boolean sudokuSolverUsingBits(char[][] matrix, ArrayList<Integer> al, int idx) {
        if (idx == al.size()) {
            return true;
        }

        int r = al.get(idx) / 9;
        int c = al.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            int mask = (1<<num);
            if (((rowsb[r] & mask)==0 ) && ((colsb[c] & mask)==0 ) && ((matsb[r/3][c/3] & mask) ==0 )) {
                matrix[r][c] = (char) ('0' + num);

                rowsb[r] |= mask;
                colsb[c] |= mask;
                matsb[r/3][c/3] |= mask;
                

                if (sudokuSolverUsingBits(matrix, al, idx + 1))
                    return true;

                rowsb[r] &= ~mask;
                colsb[c] &= ~mask;
                matsb[r/3][c/3] &= ~mask;

                matrix[r][c] = '.';
            }
        }

        return false;
    }

    // 139
    public static boolean wordBreak(String str, String asf, HashSet<String> set) {
        if (str.length() == 0) {
            System.out.println(asf);
            return true;
        }

        for (int len = 1; len <= str.length(); len++) {
            String ss = str.substring(0, len); // substring
            if (set.contains(ss)) {
                boolean isPossible = wordBreak(str.substring(len, str.length()), asf + ss + " ", set);
                if (isPossible)
                    return true;
            }
        }

        return false;
    }

    public static void wordBreak2(String str, String asf, HashSet<String> set, List<String> ans) {
        if (str.length() == 0) {
            ans.add(asf);
            return;
        }

        for (int len = 1; len <= str.length(); len++) {
            String ss = str.substring(0, len); // substring
            String rems = str.substring(len);
            if (set.contains(ss)) {
                if (rems.length() == 0)
                    wordBreak2(rems, asf + ss, set, ans);
                else
                    wordBreak2(rems, asf + ss + " ", set, ans);
            }
        }
    }

    public boolean isSolvable(String[] words, String result) {
        HashSet<Character> set = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                set.add(ch);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            set.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : set) {
            sb.append(ch);
        }

        String uniquec = sb.toString();
        if (uniquec.length() > 10)
            return false;

        return cryptarithmeticSolver(uniquec, 0, new HashMap<>(), new boolean[10], words, result);
    }

    public static boolean cryptarithmeticSolver(String s, int idx, HashMap<Character, Integer> map, boolean[] usedNum,
            String[] words, String result) {
        if (idx == s.length()) {
            int sumwords = 0;
            for (String word : words) {
                String nums = "";
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    nums += map.get(ch);
                }
                int num = Integer.parseInt(nums);
                sumwords += num;
            }

            String nums = "";
            for (int i = 0; i < result.length(); i++) {
                char ch = result.charAt(i);
                nums += map.get(ch);
            }
            int resNum = Integer.parseInt(nums);

            return sumwords == resNum;

        }

        char ch = s.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (!usedNum[num]) {
                usedNum[num] = true;
                map.put(ch, num);
                boolean isMapped = cryptarithmeticSolver(s, idx + 1, map, usedNum, words, result);
                if (isMapped)
                    return true;
                map.put(ch, null);
                usedNum[num] = false;
            }
        }

        return false;
    }
   
    public static boolean cryptarithmeticSolver1(String s, int idx, HashMap<Character, Integer> map, int usedNum,
            String[] words, String result) {
        if (idx == s.length()) {
            int sumwords = 0;
            for (String word : words) {
                String nums = "";
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    nums += map.get(ch);
                }
                int num = Integer.parseInt(nums);
                sumwords += num;
            }

            String nums = "";
            for (int i = 0; i < result.length(); i++) {
                char ch = result.charAt(i);
                nums += map.get(ch);
            }
            int resNum = Integer.parseInt(nums);

            return sumwords == resNum;

        }

        char ch = s.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            int mask = (1<<num);
            if ((usedNum & mask)==0) {
                usedNum |= mask;
                map.put(ch, num);
                boolean isMapped = cryptarithmeticSolver1(s, idx + 1, map, usedNum, words, result);
                if (isMapped)
                    return true;
                map.put(ch, null);
                usedNum &= ~mask;
            }
        }

        return false;
    }


    
}