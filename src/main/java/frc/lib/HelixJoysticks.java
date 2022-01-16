package frc.lib;

import edu.wpi.first.wpilibj.Joystick;

public class HelixJoysticks {
    private Joystick controller;
    private int x, y, theta;

    public HelixJoysticks(Joystick controller, int x, int y, int theta) {
        this.controller = controller;
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public double getX() {
        return controller.getRawAxis(x);
    }

    public double getY() {
        return controller.getRawAxis(y);
    }

    public double getRotation() {
        return controller.getRawAxis(theta);
    }
}
