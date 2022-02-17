package frc.lib.control;

import java.util.Hashtable;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.interpolation.Interpolatable;
import edu.wpi.first.math.interpolation.TimeInterpolatableBuffer;

public class FastPoseEstimator {
    public static void main(String[] args) {
        Interpolatable<Pose2d> pose0 = new Pose2d(new Translation2d(0,1), new Rotation2d(0));
        Interpolatable<Pose2d> pose1 = new Pose2d(new Translation2d(0,2), new Rotation2d(0));

        // System.out.println(pose0.interpolate(pose1, 0.5));
    }
}