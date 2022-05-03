import java.util.*;

public class Class2_leetcode {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        List<List<Integer>> ans = new ArrayList<>();
        permutationsOfAnArrayWithDistinctNums(arr, 0, new boolean[arr.length], new ArrayList<>(), ans);

        System.out.println(ans);
    }

    //39
    public static void coinChangeCombination_IN(int[] coins, int li, int tar, List<Integer> al,
            List<List<Integer>> ans) {
        if (tar == 0) {
            List<Integer> bal = new ArrayList<>(al);
            ans.add(bal);
            return;
        }

        for (int i = li; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                al.add(coins[i]);
                coinChangeCombination_IN(coins, i, tar - coins[i], al, ans);
                al.remove(al.size() - 1);
            }

        }
    }

    //40 avoid duplicates
    public List<List<Integer>> combinationSum2(int[] arr, int tar) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum2(arr, tar, 0, ans, smallAns);
        return ans;
    }
    
    private void combinationSum2(int[] arr, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return ;
        }

        int prev = -1;
        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i] && tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                combinationSum2(arr, tar - arr[i], i + 1, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
                prev = arr[i];
            }
        }
    }


    //322
    public static int min_len_Combination(int[] coins, int li, int tar, ArrayList<Integer> al) {
        if (tar == 0) {
            return al.size();
        }

        int min = (int) 1e9;
        for (int i = li; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                al.add(coins[i]);
                min = Math.min(min, min_len_Combination(coins, i, tar - coins[i], al));
                al.remove(al.size() - 1);
            }

        }

        return min;

    }

    //or
    public static int min_len_Combination(int[] coins, int li, int tar) {
        if (tar == 0) {
            return 0;
        }

        int min = (int) 1e9;
        for (int i = li; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                min = Math.min(min, min_len_Combination(coins, i, tar - coins[i]) + 1);
            }

        }

        return min;

    }

    //518
    public static int count_coin_comb_IN_SS(int[] coins, int i, int tar) { 
        if (tar == 0) {
            return 1;
        }
        if (i == coins.length)
            return 0;

        int count = 0;

        int coin = coins[i];
        if (tar - coin >= 0)
            count += count_coin_comb_IN_SS(coins, i + 1, tar);

        int j = 1;
        while (tar - j * coin >= 0) {
            count += count_coin_comb_IN_SS(coins, i + 1, tar - j * coin);
            j++;
        }

        return count;
    }

    //46
    public static void permutationsOfAnArrayWithDistinctNums(int[] nums, int b, boolean[] used, List<Integer> al, List<List<Integer>> ans) {
        if (b == nums.length) {
            List<Integer> bal = new ArrayList<>(al);
            ans.add(bal);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == false) {
                used[i] = true;
                al.add(nums[i]);
                permutationsOfAnArrayWithDistinctNums(nums, b + 1, used, al, ans);
                al.remove(b);
                used[i] = false;
            }
        }

        //or
        // for(int i=0; i<nums.length; i++){
        //     if(nums[i]>-30){
        //         int val = nums[i];
        //         nums[i]=-30; //rendom num
        //         al.add(val);
        //         permutationsOfAnArrayWithDistinctNums(nums, b+1, al, ans);
        //         al.remove(al.size()-1);
        //         nums[i]=val;
        //     }
        // }

    }

    public static void permuteUnique(int[] nums, int idx, int[] lo, int[] ansArr, List<List<Integer>> ans){
        if(idx == nums.length){
            List<Integer> bal = new ArrayList<>();
            for (int e : ansArr)
                bal.add(e);
            ans.add(bal);
            return;
        }

        int loi = lo[nums[idx]];
        for(int i=loi+1; i<nums.length; i++){
            if(ansArr[i]==0){
                ansArr[i] = nums[i];
                permuteUnique(nums, idx+1, lo, ansArr, ans);
                ansArr[i] = 0;
            }
        } 
    }

    //17
    static String[] codes = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        String[] codes = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinationsOfAPhone(digits, 0, "",codes,ans);
        return ans;
    }
    
    public static void letterCombinationsOfAPhone(String digits, int idx, String asf, String[] codes, List<String> ans ){
        if(idx==digits.length()){
            ans.add(asf);
            return;   
        }

        char ch = digits.charAt(idx);
        int num = ch-'0';

        String code = codes[num];
        for(int i=0; i<code.length(); i++){
            char cch = code.charAt(i);
            letterCombinationsOfAPhone(digits, idx+1, asf+cch, codes, ans);
        }
    }
}
