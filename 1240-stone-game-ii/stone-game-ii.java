class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to end
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        return solve(0, 1, piles, suffix, dp);
    }

    private int solve(int i, int M, int[] piles, int[] suffix, int[][] dp) {

        // Take all remaining piles
        if (i >= piles.length) {
            return 0;
        }

        if (2 * M >= piles.length - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxStones = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {

            // Opponent gets solve(i + X, newM)
            int opponent = solve(
                i + X,
                Math.max(M, X),
                piles,
                suffix,
                dp
            );

            // Total remaining - what opponent gets
            int current = suffix[i] - opponent;

            maxStones = Math.max(maxStones, current);
        }

        dp[i][M] = maxStones;

        return maxStones;
    }
}