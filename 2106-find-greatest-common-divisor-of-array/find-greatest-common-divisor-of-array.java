class Solution {
    public int findGCD(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();
int min = Arrays.stream(nums).min().getAsInt();  
while (min != 0){
 int rem = max% min;
 max=min;
 min=rem;
} 
return max;
    }
}