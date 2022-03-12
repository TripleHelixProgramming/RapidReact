package frc.robot.status.actions;

public class ChaseAction extends LedAction {

    // State
    private int leadingIndex = 0;
    private double chaseFactor = 0.10;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int brightness = 0;

    // Default will run a rainbow pattern.
    public ChaseAction() {
        super();

        red = 255;
        brightness = 100;

        // Run forever, 10ms
        intervalCount = -1;
        intervalTime = 0.010;
    }

    public ChaseAction(int red, int green, int blue, int brightness) {
        super();

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.brightness = brightness;

        // Run forever, 50ms
        intervalCount = -1;
        intervalTime = 0.050;
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

                int d = 0;

                if ((i < leadingIndex) && (i > leadingIndex - chaseCount)) {
                    d = leadingIndex - i;
                }

                if (d != 0) {
                    int cr = (int) (r * (1 - d * chaseFactor));
                    int cg = (int) (g * (1 - d * chaseFactor));
                    int cb = (int) (b * (1 - d * chaseFactor));

                    buffer.setRGB(i, cr, cg, cb);
                } else {
                    buffer.setRGB(i, 245, 0, 255);
                }
            }
        }

        ++leadingIndex;
        if (leadingIndex == buffer.getLength()) {
            leadingIndex = 0;
        }
    }
}
