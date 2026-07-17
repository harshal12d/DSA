class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // 1. Find the maximum value in nums to size our arrays
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // 2. Count frequencies of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // 3. Count total pairs that share 'i' as a common divisor
        long[] gcdPairs = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long countMultiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                countMultiples += freq[j];
            }
            
            // Total pairs where both elements are multiples of i
            long totalPairs = countMultiples * (countMultiples - 1) / 2;
            
            // Subtract pairs that have a larger GCD (multiples of i like 2i, 3i...)
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdPairs[j];
            }
            gcdPairs[i] = totalPairs;
        }

        // 4. Create a prefix sum array of the GCD pair counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairs[i];
        }

        // 5. Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = binarySearch(prefixSums, queries[i]);
        }

        return ans;
    }

    private int binarySearch(long[] prefixSums, long target) {
        int low = 1, high = prefixSums.length - 1;
        int result = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // We look for the first GCD index where the prefix sum is strictly greater than the target index
            if (prefixSums[mid] > target) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}