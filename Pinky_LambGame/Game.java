// The Game class models the game’s behavior. It contains a Cooky object and an array list
// of Guy objects. Important: the GameFrame and GamePanel classes only communicate
// with the Game object; they never directly communicate with the Cooky or Guy classes. It
// has an update method that is called every time the Timer fires off an Action event. In
// this update method:
//      o the Cooky’s move method is called
//      o new Guy objects are spawned
//      o each Guy object is moved
//      o collisions between the Cooky and Guy objects are detected
//      o Guy objects that have moved off the left edge of the panel are removed
import java.awt.*;
import java.util.*;

public class Game{
    private boolean playing = false;
    private int width, height;
    private Cooky cooky;
    private ArrayList<Guy> list = new ArrayList<Guy>();
    private int score;
    private int eaten;
    private int numGood;
    private int numBad;
    public Game( int w, int h ){
        width = w;
        height = h;
        cooky = new Cooky( 5, height/2 - 10, width, height );
    }

    // sets up the game
    public void init(){
        score = 0;
        numGood = 0;
        numBad = 0;
        eaten = 0;
        cooky = new Cooky( 5, height/2 - 10, width, height );
        list.clear();   // removes all elements
    }

    public void updateCookyMotion( int dx, int dy  ){
        cooky.setSpeed( dx, dy );
    }

    public void update(){
        // update the Cooky's position
        cooky.move();
        //  maybe* spawn a new Guy
        //    set x so that it begins just off the right side of the panel
        //    set y to a random value
        //    there should be a 50-50 chance of a Guy being good or bad
        //    add the guy to the array list
        int gx = width;
        int gy = (int)((height-1)*Math.random());
        boolean b = false;
        if (Math.random() < 0.5 )
            b = true;
        int rn = (int)(26*Math.random());
        if (Math.abs(1-rn)<.01){
            list.add(new Guy(gx,gy,b));
            Guy g = list.get(list.size()-1);
            if(g.isGood())
                numGood++;
            else
                numBad++;
        }
        // * you do NOT want to add a new Guy every time the update method is called

        // loop through the array list of Guy objects and tell each one to move
        for ( Guy g : list ){
            g.move();
            if ( g.getX() <= 0 || g.getX() >= width )
                g.setDx( -g.getDx() );
            if (g.getY() <= 0 || g.getY() >= height )
                g.setDy( -g.getDy() );
        }
        // look for collisions between the Cooky and the each Guy object
        // The Rectangle class has an intersects method that returns true if two rectangles overlap
        Rectangle Cooky_r = cooky.getShape();
        for ( int k = list.size() - 1; k >= 0; k-- ){
            Guy guy = list.get( k );
            Rectangle guy_r = guy.getShape();
            if ( Cooky_r.intersects( guy_r ) ){
                // if the guy is good, increase the score
                // else the guy is bad, call the Cooky's hurt method
                // either way, remove the guy from the list
                if(guy.isGood()){
                    eaten++;
                    numGood--;
                }else{ 
                    cooky.hurt();
                    numBad--;
                }
                list.remove(k);
            }
        }
        // loop through all the guys and remove any that have moved off the left side of the panel
    }

    public boolean keepPlaying(){
        return cooky.isAlive();
    }

    public int getScore(){
        score = eaten + numBad;
        return score;
    }
    
     public int getNumEaten(){
        return eaten;
    }
    
    public int getNumGood(){
        return numGood;
    }
    
        public int getNumBad(){
        return numBad;
    }

    public void draw( Graphics g ){
        cooky.draw( g );
        for ( Guy guy : list )
            guy.draw( g );

    }
}