import java.util.Scanner;
import java.awt.*;
import java.util.*;
class BirthdayExp {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        double count = 0;
        System.out.print("Number of Trials:" );
        int rows = sc.nextInt();
        System.out.print("Number of People:" );
        int len = sc.nextInt();
        int [][] nums = new int [rows][len];
        fillList(nums,len);
        for(int r = 0; r<rows; r++){
         if(repeats( nums[r]))
            count++;
        }    
        System.out.println("The chance that 2 people will share the smae birthday among" + "\n" + len + " people and " + rows + " trials is " + count/rows);
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