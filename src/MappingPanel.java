
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JPanel;

public class MappingPanel extends JPanel {

    // specify number of pixels
    // TO-DO: Experiment with different values for
    // these next 2 constants.
    private final int BITMAP_WIDTH = 48;
    private final int BITMAP_HEIGHT = 48;

    private final BufferedImage image;

    public MappingPanel() {
        int w = BITMAP_WIDTH;
        int h = BITMAP_HEIGHT;
        int imageType = BufferedImage.TYPE_INT_RGB;
        this.image = new BufferedImage(w, h, imageType);
    } // MappingPanel()

    private Color chooseColor( int i, int j ) {
        Color c = Color.WHITE;

        switch( (i * j) % 8 ) {
            case 0: c = Color.CYAN; break;
            case 1: c = Color.GREEN; break;
            case 2: c = Color.RED; break;
            case 3: c = Color.BLUE; break;
            case 4: c = Color.MAGENTA; break;
            case 5: c = Color.ORANGE; break;
            case 6: c = Color.YELLOW; break;
            case 7: c = Color.PINK; break;
            default: c = Color.BLACK;
        } // switch

        return c;
    } // chooseColor( int, int )

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Create an object to use in making
        // the picture fit the window.
        double w = this.getWidth();
        double h = this.getHeight();
        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / BITMAP_WIDTH, h / BITMAP_HEIGHT);

        // Create an array of pixels
        WritableRaster raster = this.image.getRaster();

        // define 2 colors
        // represent each color with an array of integers,
        // each integer's value is in [0, 255] and
        // the integers represent amount of red, green, blue
        // TO-DO: If you wish, replace these definitions
        // with definitions of different colors.
        int[] white = {255, 255, 255};
        int[] black = {0, 0, 0};

        // assign colors to pixels in raster
        for (int i = 0; i < BITMAP_HEIGHT; i++) {
            for (int j = 0; j < BITMAP_WIDTH; j++) {

                Color c = chooseColor( i, j );
                int[] colorArray =
                        { c.getRed(), c.getGreen(), c.getBlue() };

                raster.setPixel( i, j, colorArray );

                /*
                if (i < j) {
                    raster.setPixel(j, i, black);
                } // if
                else {
                    raster.setPixel(j, i, white);
                } // else
                */

            } // for
        } // for

        g2d.drawImage(image, scale, this);
    } // paintComponent( Graphics )

} // MappingPanel
