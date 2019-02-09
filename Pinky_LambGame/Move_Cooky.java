// GamePanel extends JPanel. This contains a reference to the same Game object that the
// GameFrame class created. It draws all the objects in the game. The GamePanel also
// contains a KeyListener that controls how the Player object moves
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class Move_Cooky extends JPanel {
    private Game game;
    private boolean [] key = new boolean[4];
    private int speed = 6;
    // key[0] is the left arrow, key[1] is right, key[2] is up, key[3] is down

    public Move_Cooky( Game g ) {
        game = g;
        Color col = new Color(228,254,255);
        setBackground(col);
        setBorder( BorderFactory.createLineBorder(Color.BLACK ) );

        addKeyListener( new Listen() );
        setFocusable(true); // very important
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g ); 
        game.draw( g );
    }

    private class Listen extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                key[0] = true;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                key[1] = true;
            else if (e.getKeyCode() == KeyEvent.VK_UP)
                key[2] = true;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                key[3] = true;

            game.updateCookyMotion( calculateDx(), calculateDy() );
        }

        @Override
        public void keyReleased(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                key[0] = false;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                key[1] = false;
            else if (e.getKeyCode() == KeyEvent.VK_UP)
                key[2] = false;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                key[3] = false;

            game.updateCookyMotion( calculateDx(), calculateDy() );
        }

        private int calculateDx(){
            int dx = 0;
            if ( key[0] )   // left arrow pressed
                dx = dx - speed;
            if ( key[1] )    // right arrow pressed
                dx = dx + speed;

            return dx;
        }

        private int calculateDy(){
            int dy = 0;
            if ( key[2] )   // up arrow pressed
                dy = dy - speed;
            if ( key[3] )    // down arrow pressed
                dy = dy + speed;

            return dy;
        }

    }
}