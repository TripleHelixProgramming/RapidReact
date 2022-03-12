package frc.robot.drive.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class Test {
    public static void main(String[] args) {
        ProfiledPIDController controller = new ProfiledPIDController(1,0,0,new TrapezoidProfile.Constraints(1, 1));
        System.out.println(controller.calculate(1, 2));

    }
}