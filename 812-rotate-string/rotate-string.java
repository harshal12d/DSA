class Solution {
    public boolean rotateString(String s, String goal) {
       int n = s.length();
       for(int i=0;i<n;i++){
        String rotate =  s.substring(i) + s.substring(0, i);
        if(rotate.equals(goal)){
            return true;
        }
        
       } 
       return false;
    }
}