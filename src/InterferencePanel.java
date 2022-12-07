import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.JPanel;

public class InterferencePanel extends JPanel implements ActionListener {

    private double slant0;
    private double slant1;

    private double dx0;
    private double dx1;
    private double dx0Bound;
    private double dx1Bound;
    public InterferencePanel() {
        this.slant0 = 0.0;
        this.slant1 = 0.0;

        // Establish Global Variables for Animating Circles
        this.dx0 = 1;
        this.dx1 = 1;
        this.dx0Bound = 1.8;
        this.dx1Bound = 0.2;
        this.setBackground( Color.BLACK );
    } // InterferencePanel()

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2D = (Graphics2D) g;


        AffineTransform scale = new AffineTransform();
        double w = this.getWidth();
        double h = this.getHeight();
        scale.setToScale( w/2, h/2 );

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation( 1.0, 1.0 );

        AffineTransform transform = new AffineTransform();
        transform.concatenate( scale );
        transform.concatenate( translate );

        Stroke stroke = new BasicStroke( 2 );
        g2D.setStroke( stroke );

        Random red = new Random();
        int redInt = red.nextInt(256);

        Random green = new Random();
        int greenInt = green.nextInt(256);

        Random blue = new Random();
        int blueInt = blue.nextInt(256);

        Color color1 = new Color(0x810050);
        Color color2 = new Color(0x00FFFF);
        int rad = 10;
        int dia = 20;


        // Call to draw our circles
        drawConcentricCircles(this.dx0, rad, dia, transform, g2D, color1);

        drawConcentricCircles(this.dx1, rad, dia, transform, g2D, color2);

        /*
        drawParallelLines( this.slant0, transform, g2D );

        drawParallelLines( this.slant1, transform, g2D );
        */
    } // paintComponent( Graphics )

    public void drawConcentricCircles( double dx, int radius, int diameter, AffineTransform transform, Graphics2D g2D, Color color) {


        g2D.setColor( color );

        for(int i = 0; i < 30; i++) {

            // You can change the integers (i.e. 400, 3) in order to change the look of your concentric circles
            g2D.drawOval((int)(dx * 400)-(i*radius), 400-(i*radius),
                    (i+3)*diameter, (i+3)*diameter);
        }
    } // drawConcentricCircles(AffineTransform, Graphics2D)

    private double spacing( double x ) {
        return 0.02 + 0.01 * Math.sin( 4 * x * Math.PI ) ;
    } // spacing( double )


    public void drawParallelLines( double slant,
                                   AffineTransform transform, Graphics2D g2D, Color color ) {


        g2D.setColor( color );
        double x = -0.8;

        while( x < 0.8 ) {
            double x0 = x - slant;
            double y0 = -0.8;

            double x1 = x + slant;
            double y1 = 0.8;

            Line2D line = new Line2D.Double( x0, y0, x1, y1 );

            Shape shape = transform.createTransformedShape( line );

            g2D.draw( shape );

            x += spacing(x);
        } // while

    } // drawParallelLines()



    public void actionPerformed( ActionEvent e ) {
        this.slant0 += 0.01;
        if( this.slant0 > 0.4 ) {
            this.slant0 = 0.0;
        } // if

        this.slant1 -= 0.01;
        if( this.slant1 < -0.4 ) {
            this.slant1 = 0.0;
        } // if

        // Change the x coordinates for each set of concentric circles, so they move in opposite directions
        this.dx0 += 0.02;
        if(this.dx0 > 1.8) {
            this.dx0 = this.dx1Bound;
        } // if


        this.dx1 -= 0.02;
        if(this.dx1 < 0.2) {
            this.dx1 = this.dx0Bound;
        } // if


        this.repaint();
    } // actionPerformed( ActionEvent )

} // InterferencePanel
