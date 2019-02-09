public class Experimental  {
    private double prob;
    private int total = 100;

    public Experimental(int p){
        int [][] nums = new int [total][p];
        fillList(nums,p);
        double count = 0;
        for(int r = 0; r<total; r++){
            if(repeats( nums[r]))
                count++;
        }    
        prob = count/total;
    }

    public double getProb(){
        return prob;
    }

    private static void fillList (int [][] nums, int len) { 
        for(int t = 0; t<nums.length; t++){
            for(int k = 0; k<len;k++){
                nums[t][k] = (int)(Math.random()*365);   
            }
        }
    }

    private static boolean repeats(int [] n){
        for(int t = 0; t<n.length ; t++){
            int a = n[t];
            for(int k = 0; k<n.length ;k++){
                if(t!=k && a==n[k])
                    return true;
            }
        }
        return false;
    }
}