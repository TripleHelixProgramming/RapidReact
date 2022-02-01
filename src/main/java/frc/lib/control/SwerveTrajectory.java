package frc.lib.control;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;

public class SwerveTrajectory {
    private final List<State> trajectory;

    public SwerveTrajectory(List<State> trajectory) {
        this.trajectory = trajectory;
    }

    public State sample(double time) {

    }

    private State interpolate(State start, State end, double x) {
        double newT = (end.t - start.t) * x + start.t;
        Vector3d startPoseVector = Vector3d.fromPose(start.pose);
        Vector3d endPoseVector = Vector3d.fromPose(end.pose);
        Pose2d newPose = endPoseVector.minus(startPoseVector)).times(x).plus(startPoseVector).toPose()
        Vector3d newVelocity = ((end.velocity.minus(start.velocity)).times(x)).plus(start.velocity) 
        return new State(
            newT,
            newPose,
            newVelocity
        );
    }

    public static class State {
        double t;
        Pose2d pose;
        Vector3d velocity;

        public State(double t, Pose2d pose, Vector3d velocity) {
            this.t = t;
            this.pose = pose;
            this.velocity = velocity;
        }
    }
}