
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class Highscore{
    private int score;
    public Highscore(){
        
    }

    public int getHighScore()
    {
        String fileName = "Scores.txt";
        File fileToRead = new File(fileName);
        Scanner in = null;
        int score = 0;
        try{
            in = new Scanner(fileToRead);
            score = in.nextInt();
        }catch(FileNotFoundException fne){
            String errorString = fne.getMessage();
            System.out.println(errorString);
        }finally{
            if(in != null){
                in.close();
            }
        }
        return score;
    }
    
    public void setHighscore(int x){
        String fileName = "Scores.txt";
        PrintWriter out = null;
        String tempStr = "";
        try{
            out = new PrintWriter(fileName);
            out.println(x);
        }catch(FileNotFoundException fne){
            String errorString = fne.getMessage();
            System.out.println(errorString);
        }finally{
            if(out != null){
                out.close();
            }
        }
    }
}
