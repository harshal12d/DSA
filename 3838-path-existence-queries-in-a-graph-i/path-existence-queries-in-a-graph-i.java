class Solution {
    class DSU{
        int[] parent;
        int[] size;

        DSU(int n){
            parent = new int[n];
            size = new int[n];

            for(int i=0;i<n;i++){
                parent[i] = i;
                size[i] =1;
            }
        }
        int find(int x){
            if(parent[x] == x)
            return x;
            return parent[x] = find(parent[x]);
        }
        void union(int u, int v){
            int pu = find(u);
            int pv = find(v);
            if(pu == pv) return;
            if(size[pu] < size[pv]){
                parent[pu] = pv;
                size[pv] += size[pu];
             } else {
                parent[pv] = pu;
                size[pu] += size[pv];
            } 

                   }
    }
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);

        // Connect adjacent nodes if their difference is <= maxDiff
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                dsu.union(i, i + 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = dsu.find(u) == dsu.find(v);
        }

        return ans;
    }
}