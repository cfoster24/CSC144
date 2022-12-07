
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Interference extends JFrame {

    public static final int FRAME_WIDTH = 768;
    public static final int FRAME_HEIGHT = 768;

    public Interference() {
        this.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        this.setTitle( "The Mythical Man-Month" );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        
        Container pane = this.getContentPane();
        InterferencePanel panel = new InterferencePanel();
        pane.add( panel );

        Timer timer = new Timer( 50, panel);
        timer.start();

        this.setVisible( true );
    } // Interference()

    public static void main( String [] args ) {
        System.out.println( "It's cold outside!" );

        Interference interference = new Interference();
    } // main( String [] )

} // Interference
