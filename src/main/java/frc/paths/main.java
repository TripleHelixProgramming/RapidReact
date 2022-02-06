package frc.paths;

import frc.lib.control.PIDController;
import frc.lib.control.SwerveTrajectory;

public class main {
    public static void main(String[] args) {
        PIDController controller = new PIDController(1, 0, 0);
        controller.setContinous(true);
        controller.setInputRange(360);

        controller.setReference(-790);
        System.out.println(controller.calculate(130, 1));
    }
}
