package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class main {
    public static void main(String[] args) {
        Path path = new gogogogadget();
        SwerveTrajectory trajectory = path.getPath();

        // System.out.println("hi");
        System.out.println(trajectory.getInitialPose());
        System.out.println(trajectory.sample(3.0).pose);

        System.out.println(5 % Double.POSITIVE_INFINITY);
    }
}
