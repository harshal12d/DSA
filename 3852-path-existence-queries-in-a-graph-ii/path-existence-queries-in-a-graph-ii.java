import java.util.*;

class Solution {
    // Renamed from minDistanceQueries to pathExistenceQueries to match the driver
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 1. Store pairs of (value, original_index) and sort them by value
        int[][] sortedNodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNodes[i][0] = nums[i];
            sortedNodes[i][1] = i;
        }
        Arrays.sort(sortedNodes, (a, b) -> Integer.compare(a[0], b[0]));

        // Track where each original index ended up in the sorted array
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sortedNodes[i][1]] = i;
        }

        // 2. Find the furthest reachable index to the right for each sorted position
        int[] furthestJump = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && sortedNodes[r][0] - sortedNodes[l][0] <= maxDiff) {
                r++;
            }
            furthestJump[l] = r - 1;
        }

        // Prefix sum to check connectivity between sorted indices
        int[] prefValid = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int canStep = (sortedNodes[i + 1][0] - sortedNodes[i][0] <= maxDiff) ? 1 : 0;
            prefValid[i + 1] = prefValid[i] + canStep;
        }

        // 3. Build the Binary Lifting Table
        int LOG = 18; 
        int[][] up = new int[n][LOG];
        for (int i = 0; i < n; i++) {
            up[i][0] = furthestJump[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // 4. Process Queries
        int numQueries = queries.length;
        int[] ans = new int[numQueries];

        for (int q = 0; q < numQueries; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            int p1 = pos[u];
            int p2 = pos[v];
            if (p1 > p2) {
                int temp = p1;
                p1 = p2;
                p2 = temp;
            }

            // Check if there is any broken link/gap between p1 and p2 in the sorted array
            if (prefValid[p2] - prefValid[p1] != p2 - p1) {
                ans[q] = -1;
                continue;
            }

            // Use binary lifting to count the jumps needed to reach or cross p2
            int jumps = 0;
            int curr = p1;

            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < p2) {
                    curr = up[curr][j];
                    jumps += (1 << j);
                }
            }

            // One final jump to land on or cross over p2
            jumps++;
            ans[q] = jumps;
        }

        return ans;
    }
}