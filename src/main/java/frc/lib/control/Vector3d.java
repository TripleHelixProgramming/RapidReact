package frc.lib.control;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Vector3d {
    public final double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3d fromPose(Pose2d pose) {
        return new Vector3d(pose.getX(), pose.getY(), pose.getRotation().getRadians());
    }

    public Pose2d toPose() {
        return new Pose2d(new Translation2d(x, y), new Rotation2d(z));
    }

    public Vector3d plus(Vector3d vector) {
        return new Vector3d(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3d minus(Vector3d vector) {
        return new Vector3d(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3d times(double scale) {
        return new Vector3d(x * scale, y * scale, z * scale);
    }
}