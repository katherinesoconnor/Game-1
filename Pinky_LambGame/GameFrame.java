// GameFrame extendsJFrame. It creates a button (at the top), a label (at the bottom), and a GamePanel (in the middle).
// It also contains a timer and a Game object.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;

public class GameFrame extends JFrame {
    private final int WIDTH = 906;
    private final int BTN_LABEL_HEIGHT = 30;
    private final int GAME_HEIGHT = 500;
    private final int PANEL_HEIGHT = GAME_HEIGHT + 2*BTN_LABEL_HEIGHT;

    private Highscore hs = new Highscore();
    private int hscore = hs.getHighScore();
    private JLabel hlbl = new JLabel("Highscore: " + hscore);

    private Game game = new Game( WIDTH, GAME_HEIGHT );
    private JButton btn = new JButton( "Start" );
    private JLabel intro = new JLabel();
    private JLabel lbl = new JLabel("Score: 0");
    private JLabel lbl2 = new JLabel("Cookies Eaten: 0    Cookies Remaining: 0    Shookies: 0");
    private Move_Cooky gp = new Move_Cooky( game );
    private javax.swing.Timer timer;
    private AudioClip bmusic;

    public GameFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle( "Shooky or Cookie?" );
        setResizable( false );
        JPanel jp = new JPanel();
        Color col = new Color(255,228,228);
        jp.setBackground(col);
        jp.setPreferredSize( new Dimension( WIDTH, PANEL_HEIGHT ) );
        jp.setLayout( null );

        // create timer object
        timer = new javax.swing.Timer( 40, new Clock() );
        // set up the and add the button and label
        btn.setBounds(WIDTH/2-75,0,150,BTN_LABEL_HEIGHT);
        btn.setBackground(col);
        btn.addActionListener( new ButtonListener());
        jp.add(btn);
        lbl.setBounds(10,PANEL_HEIGHT-30,500,30);
        jp.add(lbl);
        lbl2.setBounds(550,PANEL_HEIGHT-30,550,30);
        jp.add(lbl2);
        hlbl.setBounds(250,PANEL_HEIGHT-30,500,30);
        jp.add(hlbl);
        intro.setBounds(53,100,800,100);
        intro.setText("<html><body>Awww, poor Cooky is starving! Help feed Cooky cookies and avoid eating any shookys!(The cookies with the faces on them) If you get hit by a shooky three times, you will die!</body></html>");
        Font f = new Font("Arial", Font.BOLD, 20);
        intro.setFont(f);
        jp.add(intro);
        gp.setBounds( 0, BTN_LABEL_HEIGHT, WIDTH, GAME_HEIGHT );
        jp.add( gp );

        getContentPane().add( jp );
        pack(); 
        try {
            bmusic = Applet.newAudioClip( getClass().getResource("DNA.wav"));
        } catch (Exception e) {
            System.out.println ( "The sound is not working :(" );
        }
        bmusic.loop();
    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    setVisible(true);
                }
            });
    }

    private class Clock implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            game.update();
            gp.repaint();   // redraw the panel
            // get the score from the game
            // if the game is over, stop the time and display a message
            // if the game is not over, just display the score.
            if(game.keepPlaying()){
                lbl.setText("Score: " + game.getScore());
                lbl2.setText("Cookies Eaten: " + game.getNumEaten()+ "   Cookies Remaining: " + game.getNumGood()+ "   Shookys: " + game.getNumBad());
            }else{
                timer.stop();
                lbl.setText("GAME OVER! Your score was " + game.getScore()+".");
                if(game.getScore()>hscore){
                    hs.setHighscore(game.getScore());
                    hlbl.setText("Highscore: " + game.getScore());
                    intro.setText("Congratulations! You set a new high score of " + game.getScore() + "!");
                }
            }
        }
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // if the button says Start, start the timer and change the text to Reset
            if( btn.getText().equals("Start")){
                timer.start();
                btn.setText("Reset");
                intro.setText("");
            }else {
                timer.stop();
                btn.setText("Start");
                game.init();
            }
            // if the button says Reset
            //      stop the time
            //      change the text to Start
            //      call the game's init method 
            //      change what is displayed in the label
            //      cause the game panel's paintComponent method to be called
            gp.requestFocusInWindow();
        }
    }
}