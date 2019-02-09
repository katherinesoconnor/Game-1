
import java.util.Scanner;

public class testme{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Want to know the standard deviation? Enter a list of numbers separated by commas.");
        //String input = sc.nextString();
        int [] nums = {1,2,3};
        double d = stdev(nums);
        
        System.out.print(d);
    }

    public static double stdev(int [] nums){

        double stdev = 0;
        double sum = 0;
        double [] dnums = new double [nums.length];

        for (int k = 0; k<nums.length; k++){
            dnums[k]= (double)(nums[k]);
            sum+=dnums[k];
        }
        double average = sum/(double)(nums.length);

        for (int k = 0; k<dnums.length; k++)
            stdev+=(average-dnums[k])*(average-dnums[k]);

        stdev = stdev/(double)(nums.length-1);
        stdev = Math.sqrt(stdev);

        return stdev;
    }
}
