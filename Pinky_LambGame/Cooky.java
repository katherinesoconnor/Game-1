// The Player class models the concept of something/someone moving around the field. It
// has a location, speed, and health attributes (as well as other attributes).
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Cooky{
    private int x, y;   // top left
    private int dx = 0, dy = 0;  // speed in the 
    private BufferedImage imgGreen, imgYellow, imgRed;
    private int health = 2;  
         // 2 means good health
         // 1 means damaged health green
         // 0 means dead

    private static final int IMG_WIDTH = 27;  // image dimensions 
    private static final int IMG_HEIGHT = 35; 
    private int panel_width, panel_height;  // size of the panel
    private BufferedImage bt21;

    public Cooky( int x, int y, int panel_w, int panel_h ){
        this.x = x;
        this.y = y;
        panel_width = panel_w;
        panel_height = panel_h;

        try {
            bt21 = ImageIO.read(new File("WBT21.png"));
            imgGreen = ImageIO.read(new File("Pinky_Lamb.png"));
            imgYellow = ImageIO.read(new File("Pinky_Lamb2.png"));
            imgRed = ImageIO.read(new File("Pinky_Lamb3.png"));
        } catch (IOException e) {
            System.out.println ( e );
        }
    }

    public void setSpeed( int dx, int dy ){
        this.dx = dx;
        this.dy = dy;
    }
    
    public void hurt(){
        health--;
    }
    
    public boolean isAlive(){
        return health > 0;
    }

    public void move(){
        x += dx;
        y += dy;
        if(x<0)
            x = 0;
        else if((x+IMG_WIDTH)>=panel_width)
            x = panel_width-IMG_WIDTH ;
        if(y<0)
            y = 0;  
        else if ((y+IMG_HEIGHT)>=panel_height)
            y = panel_height-IMG_HEIGHT;
        //add code so that the Cooky cannot move off the panel
    }

    public void draw(  Graphics g ){
        g.drawImage(bt21,0,0,null);
        if ( health == 2 )
            g.drawImage( imgGreen, x, y, null );
        else if ( health == 1 )
            g.drawImage( imgYellow, x, y, null );
        else
            g.drawImage( imgRed, x, y, null );
    }

    public Rectangle getShape(){
        return new Rectangle( x, y, IMG_WIDTH, IMG_HEIGHT );
    }
}