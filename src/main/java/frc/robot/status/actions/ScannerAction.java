/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.status.actions;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ScannerAction extends LedAction {

    // State
    private int leadingIndex = 0;
    private boolean forward = true;
    private double chaseFactor = 0.10;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int brightness = 0;

    // Default will run a red pattern.
    public ScannerAction() {
        super();

        red = 255;
        brightness = 100;

        // Run forever, 10ms
        intervalCount = -1;
        intervalTime = 0.010;
    }

    public ScannerAction(Color color, int brightness, double chaseFactor, double intervalTime) {
        this(new Color8Bit(color).red, new Color8Bit(color).green, new Color8Bit(color).blue, brightness);
        this.chaseFactor = chaseFactor;
        this.intervalTime = intervalTime;
    }

    public ScannerAction(int red, int green, int blue, int brightness) {
        super();

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.brightness = brightness;

        // Run forever, 10ms
        intervalCount = -1;
        intervalTime = 0.010;
    }

    protected void updateBuffer() {

        int r = (int) (red * (brightness / 255.0));
        int g = (int) (green * (brightness / 255.0));
        int b = (int) (blue * (brightness / 255.0));

        int chaseCount = (int) (buffer.getLength() * chaseFactor);
        
        for (var i = 0; i < buffer.getLength(); i++) {

            if (i == leadingIndex) {
                // The leading pixel.
                buffer.setRGB(i, r, g, b);
            } else {

                int d = 0; // How many pixels we are from the leading pixel

                if (forward) {
                    if ((i < leadingIndex) && (i > leadingIndex - chaseCount)) {
                        d = leadingIndex - i;
                    }
                } else {
                    if ((i > leadingIndex) && (i < leadingIndex + chaseCount)) {
                        d = i - leadingIndex;
                    }
                }

                if (d != 0) {
                    double fade = 1.0 - ((double)d / (double)chaseCount);

                    int cr = (int) (r * fade);
                    int cg = (int) (g * fade);
                    int cb = (int) (b * fade);

                    buffer.setRGB(i, cr, cg, cb);
                } else {
                    buffer.setRGB(i, 0, 0, 0);
                }
            }
        }

        if (forward == true) {
            ++leadingIndex;
            if (leadingIndex == buffer.getLength()) {
                forward = false;
                --leadingIndex;
            }
        } else {
            --leadingIndex;
            if (leadingIndex < 0) {
                leadingIndex = 0;
                forward = true;
            }
        }
    }
}
