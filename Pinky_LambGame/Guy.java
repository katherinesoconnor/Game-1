// The Guy class models the concept of things moving from right to left across the field.
// Some Guy objects are “good” meaning the player should try to get them. Some Guy
// objects are “bad” meaning the player should try to avoid them.
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Guy {
    private int x;   // top left
    private int y;
    private int dx;  // speed
    private int dy;
    private boolean good;
    private BufferedImage img;
    private int img_width, img_height;

    public Guy( int x, int y, boolean good ){
        this.x = x;
        this.y = y;
        this.good = good;
        dx = -1*((int)(3*Math.random()+1));
        dy = -1*((int)(3*Math.random()+1));
        if (Math.random() < 0.5 ) dy = -dy;
        if ( good ) {
            try {
                img = ImageIO.read(new File("cookie.png"));
                img_width = 30;
                img_height = 30;
            } catch (IOException e) {
                System.out.println ( e );
            }
        } else {
            try {
                img = ImageIO.read(new File("shooky.png"));
                img_width = 30;
                img_height = 30;
            } catch (IOException e) {
                System.out.println ( e );
            }
        }
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public void setDx( int n){
        dx = n;
    }

    public void setDy( int n){
        dy = n;
    }

    public boolean isGood(){
        return good;
    }

    public void draw(  Graphics g ){
        g.drawImage( img, x, y, null );
    }

    public Rectangle getShape(){
        return new Rectangle( x, y, img_width, img_height );
    }
}