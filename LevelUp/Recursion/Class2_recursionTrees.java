public class Class2_recursionTrees {

    public static void main(String args[]) {
        int[] coins = { 2, 3, 5 };
        // int[] coins = { 2, 2, 3, 5 };
        // System.out.println(coinChangeCombination_SI(coins, -1, 7, ""));

        // System.out.println(coinChangeCombination_IN(coins, 0, 8, ""));

        // System.out.println(coinChangePermutation_SI(coins, 7, ""));

        // System.out.println(coinChangePermutation_IN(coins, 7, ""));

        // System.out.println(coinChangeCombination_SI_SS(coins, 0, 7, ""));

        System.out.println(coinChangeCombination_IN_SS(coins, 0, 8, ""));

        // System.out.println(coinChangePermutation_SI_SS(coins, 7, 0, ""));

        // System.out.println(coinChangePermutation_IN_SS(coins, 7, 0, ""));
    }

    // level- target, options- coins
    public static int coinChangeCombination_SI(int[] coins, int li, int tar, String asf) { // knapsack 01
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = li + 1; i < coins.length; i++) {
            if (tar - coins[i] >= 0)
                count += coinChangeCombination_SI(coins, i, tar - coins[i], asf + coins[i]);
        }

        return count;
    }

    public static int coinChangeCombination_IN(int[] coins, int li, int tar, String asf) { // knapsack unbounded
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = li; i < coins.length; i++) {
            if (tar - coins[i] >= 0)
                count += coinChangeCombination_IN(coins, i, tar - coins[i], asf + coins[i]);
        }

        return count;
    }

    public static int coinChangePermutation_SI(int[] coins, int tar, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coins[i] > 0 && tar - coin >= 0) {
                coins[i] = -coins[i]; // instead of using visited, mark coin in coins as negative to save space
                count += coinChangePermutation_SI(coins, tar - coin, asf + coin);
                coins[i] = -coins[i];

            }

        }

        return count;
    }

    public static int coinChangePermutation_IN(int[] coins, int tar, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int coin : coins) {
            if (tar - coin >= 0)
                count += coinChangePermutation_IN(coins, tar - coin, asf + coin);
        }

        return count;
    }

    // ------------------------------

    // level-coin, options- to come or not
    public static int coinChangeCombination_SI_SS(int[] coins, int i, int tar, String asf) { // subsequence
        if (tar == 0) { // to stop as soon as we get the ans (tar==0)
            System.out.println(asf);
            return 1;
        }
        if (i == coins.length) { // positive results but not ans
            return 0;
        }

        int count = 0;

        if (tar - coins[i] >= 0) { // to avoid negative base cases tar=5 coin=8 doesn't make sense
            count += coinChangeCombination_SI_SS(coins, i + 1, tar - coins[i], asf + coins[i]);
        }
        count += coinChangeCombination_SI_SS(coins, i + 1, tar, asf);

        return count;
    }

    public static int coinChangeCombination_IN_SS(int[] coins, int i, int tar, String asf) { // knapsack unbounded
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        if (i == coins.length)
            return 0;

        int count = 0;

        int coin = coins[i];
        count += coinChangeCombination_IN_SS(coins, i + 1, tar, asf);

        int j = 1;
        String path = "";
        while (tar - j * coin >= 0) {
            path += coin;
            count += coinChangeCombination_IN_SS(coins, i + 1, tar - j * coin, asf + path);
            j++;
        }

        return count;
    }

    public static int coinChangePermutation_SI_SS(int[] coins, int tar, int idx, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        if (idx == coins.length) {
            return 0;
        }

        int count = 0;
        if (coins[idx] >= 0 && tar - coins[idx] >= 0) {
            int val = coins[idx];
            coins[idx] = -coins[idx];
            count += coinChangePermutation_SI_SS(coins, tar - val, 0, asf + val + " ");
            coins[idx] = -coins[idx];
        }
        count += coinChangePermutation_SI_SS(coins, tar, idx + 1, asf);

        return count;
    }

    public static int coinChangePermutation_IN_SS(int[] coins, int tar, int idx, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        if (idx == coins.length) {
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += coinChangePermutation_IN_SS(coins, tar - coins[idx], 0, asf + coins[idx] + " ");
        count += coinChangePermutation_IN_SS(coins, tar, idx + 1, asf);

        return count;
    }



    

}