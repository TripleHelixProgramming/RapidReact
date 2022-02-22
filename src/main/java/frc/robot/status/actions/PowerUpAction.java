/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.status.actions;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class PowerUpAction extends LedAction {

    // State
    private int leadingIndex = 0;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int brightness = 0;

    // Default will run a red pattern.
    public PowerUpAction() {
        super();

        red = 255;
        brightness = 100;

        // Run forever, 10ms
        intervalCount = -1;
        intervalTime = 0.010;
    }

    /**
     * PowerUp animation using a Color
     * 
     * @param color
     * @param brightness 0.0 to 1.0
     */
    public PowerUpAction(Color color, double brightness) {
        int intBrightness = 0;
        if (0.0 > brightness) {
            intBrightness = 0;
        } if (1.0 < brightness) {
            intBrightness = 255;
        } else {
            intBrightness  = (int) (255 * brightness);
        }
        Color8Bit intColor = new Color8Bit(color);

        this.brightness = intBrightness;
        this.red = intColor.red;
        this.green = intColor.green;
        this.blue = intColor.blue;

        // Run forever, 10ms
        intervalCount = -1;
        intervalTime = 0.010;
    }

    public PowerUpAction(int red, int green, int blue, int brightness) {
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

        for (var i = 0; i < buffer.getLength(); i++) {
            if (i <= leadingIndex) {
                // The leading pixel.
                buffer.setRGB(i, r, g, b);
            }
        }
        ++leadingIndex;
    }
}
