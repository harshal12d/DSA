class Solution {
    private static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
       int n = s.length();
        
        // Precomputing powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        int[] sumD = new int[n + 1];    // Prefix sum of digits
        int[] cntN0 = new int[n + 1];   // Prefix count of non-zero digits
        long[] p = new long[n + 1];     // Prefix rolling representation of x

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            sumD[i] = sumD[i - 1] + d;
            cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
            p[i] = d > 0 ? (p[i - 1] * 10 + d) % MOD : p[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int n0 = cntN0[r + 1] - cntN0[l]; // Count of non-zero digits in substring
            int sd = sumD[r + 1] - sumD[l];   // Sum of digits in substring

            // Math trick to extract the number formed by the substring range [l, r]
            long x = (p[r + 1] - p[l] * pow10[n0] % MOD + MOD) % MOD;

            ans[i] = (int) (x * sd % MOD);
        }

        return ans; 
    }
}