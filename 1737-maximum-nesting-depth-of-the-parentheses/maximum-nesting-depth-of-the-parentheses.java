class Solution {
    public int maxDepth(String s) {
     int max = 0;
     int ctn =0 ;
     for(char ch:s.
     toCharArray()){
        if(ch == '('){
            ctn++;
            if(ctn>max){
                max=ctn;
            }
        }
        else if(ch == ')'){
            ctn--;
        }
     }
     return max;   
    }
}