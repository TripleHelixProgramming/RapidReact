package frc.robot.status.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.status.Status;

/**
 * Use an image file to set the addressable LEDs
 * 
 * Provide the pathname to an image file.
 * Interval can be set which is the delay between frames.
 * Oscillate tells whether to start walking through the columns back at 0 or to count down when the end of the image is reached.
 * Count is how many times to go through the image. LEDs will be left at the last "frame". -1 cycles forever.
 *  
 * Each column in the image file will be treated as a "frame" or a full buffer.
 * 
 * Height of the image does not matter, pixel selection will be scaled to the size of the buffer.
 * This is a naive percentage scaling. If the LED being set is 1/2 way through the buffer, 
 * the pixel 1/2 way up the image will be used. 
 * e.g. If the buffer is 60 LEDs long and the image is 240 pixels high and LED 30 is being set,
 * pixel 120 will be selected from the image.
 * 
 * The "bottom" of the image is LED 0. 0,0 is usually the top, left of an image.
 * For our purposes, LED 0 will get the highest numbered pixel.
 * 
 */
public class ImageAction extends LedAction {

    public static final double DEFAULT_INTERVAL = 0.050;
    public static final int FOREVER = -1; // forever

    private File imageFile;
    private BufferedImage readImage;
    private BufferedImage scaledImage;

    private int currentColumn = 0;
    private boolean oscillate = false;
    private boolean goForward = true;
    private double brightness = 1.0;

    public ImageAction() {
        super();
        intervalTime = DEFAULT_INTERVAL;
        intervalCount = FOREVER;
    }

    public ImageAction(String pathname) {
        this(pathname, DEFAULT_INTERVAL, FOREVER);
    }

    public ImageAction(String pathname, double interval) {
        this(pathname, interval, FOREVER);
    }

    public ImageAction(String pathname, double interval, int count) {
        super();
        intervalTime = interval;
        try {
            File deployDir = Filesystem.getDeployDirectory();
            String pathPrefix = deployDir.getAbsolutePath() + "/images/";

            imageFile = new File( pathPrefix+ pathname);
            readImage = ImageIO.read(imageFile);
            scaledImage = readImage;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem opening image. Check the path.\nImage path = " + pathname);
        }
        intervalCount = count * scaledImage.getWidth();

        curIntCount = intervalCount;

    }

    public ImageAction(final BufferedImage readImage, final double interval, final int count) {

            /**
             * The below is very frustrating.
             * 
             * getScaledInstance() returns an Image. But we read in a BufferedImage.
             * Converting from Image back to BufferedImage causes the simulator Java to lock up on a Mac.
             * But it works fine on the Windows simulator.
             * So no convenient image scaling.
             */
            // Scale the image to the height of the LED strip
            // Image si = readImage.getScaledInstance(-1, Status.ADDRESSABLE_LED_COUNT, Image.SCALE_DEFAULT);
            // scaledImage = imageToBufferedImage(si);
            scaledImage = readImage;
            
            intervalTime = interval;

            intervalCount = count * scaledImage.getWidth();

            curIntCount = intervalCount;
    }

    public void reset() {
        super.reset();
        this.currentColumn = 0;
    }

    public ImageAction brightness(final double brightness) {
        setBrightness(brightness);
        return this;
    }

    public void setBrightness(double brightness) {
        if (0.0 > brightness) { brightness = 0.0;}
        if (1.0 < brightness) { brightness = 1.0;}
        this.brightness = brightness;
    }

    public ImageAction oscillate() {
        this.oscillate = true;
        return this;
    }

    public void setOscillate(final boolean b) {
        oscillate = b;
    }

    protected void updateBuffer() {
        for (var i = 0; i < buffer.getLength(); i++) {

            // Scale the height of the image to the size of the buffer
            // Do some shenanigans to force float arithmetic
            double percent = (i * 1.0) / (buffer.getLength()-1);
            int imageY = (int)((1.0 - percent) * (scaledImage.getHeight()-1));

            int rgb = scaledImage.getRGB(currentColumn, imageY);
            Color pixelColor = intToColor(rgb);
            buffer.setLED(i, pixelColor);
        }

        if (currentColumn < (scaledImage.getWidth()-1)) {
            if (goForward) {
                currentColumn++;
            } else { 
                currentColumn--;
            }
        } else { // reached the end of the image
            if (oscillate) {
                goForward = false;
                currentColumn--;
            } else {
                currentColumn=0;
            }
        }

        // Always move forward from the beginning
        if (0 >= currentColumn) { 
            currentColumn = 0; // Handle edge case of drawing the zeroth column which then gets decremented.
            goForward = true;
        }
    }

    /**
     * Translate an integer representation INT_ARGB of a pixel's colors to 
     * a WPILib Color object.
     * @param pixel
     * @return WPILIB Color
     */
    private Color intToColor(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int redi = (pixel >> 16) & 0xff;
        int greeni = (pixel >> 8) & 0xff;
        int bluei = (pixel) & 0xff;

        double brightness = (alpha / 255.0) * this.brightness;
        double red = brightness * (redi / 255.0);
        double green = brightness * (greeni / 255.0);
        double blue = brightness * (bluei / 255.0);

        return new Color(red, green, blue);
    }

    public BufferedImage getImage() {
        return readImage;
    }

    public void setImage(BufferedImage image) {
        this.readImage = image;
    }

    public static BufferedImage imageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
           (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
     }    
}