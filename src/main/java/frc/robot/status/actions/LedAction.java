package frc.robot.status.actions;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.status.Status;

public class LedAction extends Action {

    // How many times to run, less than 0 will run forever.
    protected int intervalCount = 1;
    protected int curIntCount = 1; // The current interval. 

    // Buffer this action uses for sending to the LEDs.
    protected AddressableLEDBuffer buffer = new AddressableLEDBuffer(Status.ADDRESSABLE_LED_COUNT);

    public LedAction() {
        // Initialize the buffer to black.
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, 0, 0, 0);
        }
    }

    // Invoke with a specific color.
    public LedAction(int red, int green, int blue, int brightness) {
        double b = brightness / 255.0;

        red = (int) (red * b);
        green = (int) (green * b);
        blue = (int) (blue * b);

        // Set the entire buffer (string of leds) to the same color.
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, red, green, blue);
        }
    }

    public void setIntervalCount(int intervalCount) {
        this.intervalCount = intervalCount;
        this.curIntCount = intervalCount;
    }

    public int getIntervalCount() {
        return this.intervalCount;
    }

    public int getCurrentIntervalCount() {
        return this.curIntCount;
    }

    // Implementations should override the updateBuffer method.
    // This will be invoked every intervalTime seconds and only needs to
    // alter the buffer. The outer run() method will handle the intervalTime,
    // intervalCount, and set/sending the buffer to the LEDs.
    protected void updateBuffer() {

    }

    @Override
    public boolean isFinished() {
        // If the intervalCount is greater than 0 or less than zero
        // we're not finished. The run() method will decrement if greater
        // and never finish if less than (a repeating pattern).
        if (curIntCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void reset() {
        this.curIntCount = this.intervalCount;
    }


    // Require by the parent Action class.
    // This is invoked by the running thread until isFinished returns true.
    @Override
    public void run() {

        // Update the buffer.
        updateBuffer();

        // Send the buffer to the leds.
        Status.getInstance().setLedData(buffer);

        // Only decrement the intervalCount if it's over 0.
        // Otherwise it may overflow backwords and cause problems.
        if (curIntCount > 0) {
            --curIntCount;
        }
    }
}
