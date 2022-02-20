/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.status.actions;

public class RainbowAction extends LedAction {

    // This member tracks the hue between updateBuffer calls.
    private int rainbowHue = 0;

    // Default will run a rainbow pattern.
    public RainbowAction() {
        super();

        // Run forever, at 50ms between iterations.
        intervalCount = -1;
        intervalTime = 0.050;
    }

    protected void updateBuffer() {

        for (var i = 0; i < buffer.getLength(); i++) {

            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final var hue = (rainbowHue + (i * 180 / buffer.getLength())) % 180;

            // Set the value
            buffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        rainbowHue += 3;

        // Check bounds
        rainbowHue %= 180;
    }
}
