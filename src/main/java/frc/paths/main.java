package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class main {
    public static void main(String[] args) {
        Path path = new gogogogadget();
        SwerveTrajectory trajectory = new SwerveTrajectory(path.getPath());

        // System.out.println("hi");
        System.out.println(trajectory.getInitialPose());
        System.out.println(trajectory.sample(1.0).pose);
    }
}
