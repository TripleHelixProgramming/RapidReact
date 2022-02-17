package frc.lib.util;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class main {
    public static void main(String args[]) {
        InterpolatingPoseMap map = new InterpolatingPoseMap(50);
        System.out.println(map.getPose(1));
        map.addPose(new Pose2d(new Translation2d(1,1), new Rotation2d(0)), 0);
        map.addPose(new Pose2d(new Translation2d(2,2), new Rotation2d(0)), 1);
        
        // map.log();
       
    }
}
