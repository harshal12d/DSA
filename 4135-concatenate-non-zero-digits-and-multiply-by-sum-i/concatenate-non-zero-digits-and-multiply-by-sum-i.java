class Solution {
    public long sumAndMultiply(int n) {
      int original = n;
       int sum =0;

       int ans = 0 ;
       int place =1;
       while(n>0){
        int digit = n%10;
        sum += digit;
        n = n/10;
       } 
       n = original;
      while(n>0){
        int digit = n%10;
        if(digit != 0){
            ans = digit*place + ans;
            place *= 10;
        }
        n = n/10;
      } 
      
      return (long) ans*sum;
    }
}