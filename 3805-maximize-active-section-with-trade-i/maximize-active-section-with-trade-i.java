class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int totalOnes = 0;
        int maxGain = 0;
        
        int prevZeroLen = 0;
        int currZeroLen = 0;
        
        int n = s.length();
        int i = 0;
        
        while (i < n) {
            if (s.charAt(i) == '1') {
                totalOnes++;
                i++;
            } else {
                // Measure the length of the current '0' block
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                currZeroLen = i - start;
                
                // If we've seen a previous '0' block separated by a '1' block
                if (prevZeroLen > 0) {
                    maxGain = Math.max(maxGain, prevZeroLen + currZeroLen);
                }
                
                prevZeroLen = currZeroLen;
            }
        }
        
        return totalOnes + maxGain;
    }
}