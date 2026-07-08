class Solution {
    public boolean isAnagram(String s, String t) {
      if(s.length() != t.length()) return false;
      int[] ctn = new int[26];
      for(char ch : s.toCharArray())
       ctn[ch-97]++;
       for(char ch : t.toCharArray())
       ctn[ch-97]--;  
       for( int val : ctn){
        if(val !=0) return false;
       }
       return true;
    }

}