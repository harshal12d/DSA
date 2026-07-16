class Solution {
   public static int gcd(int a, int b) {
    while (b != 0) {
        int rem = a % b;
        a = b;
        b = rem;
    }
    return a;
}
    public long gcdSum(int[] nums) {
      int n = nums.length;
      int[] prefixgcd = new int[n];
      int max =0;
      for(int i=0;i<n;i++){
        max = Math.max(max,nums[i]);
        prefixgcd[i] = gcd(nums[i], max);

      }
      Arrays.sort(prefixgcd);
      long ans = 0;
      int i =0 ,j=n-1;
      while(i<j){
        ans += gcd(prefixgcd[i],prefixgcd[j]);
        i++;
        j--;
      }
    return ans;
    }
}