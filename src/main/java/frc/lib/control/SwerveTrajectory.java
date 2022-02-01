package frc.lib.control;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class SwerveTrajectory {
    private final List<State> trajectory;

    public SwerveTrajectory(double[][] states) {
        trajectory = new ArrayList<State>();
        for (double[] state : states) {
            trajectory.add(new State(
                state[0],
                new Pose2d(new Translation2d(state[1], state[2]), new Rotation2d(state[3])),
                new Vector3d(state[4], state[5], state[6])
            ));
        }
    }

    public Pose2d getInitialPose() {
        return trajectory.get(0).pose;
    }

    public double getTotalTime() {
        return trajectory.get(trajectory.size() - 1).t;
    }

    public State sample(double time) {
        if (time < trajectory.get(0).t) {
            return trajectory.get(0);
        }
        if (time > getTotalTime()) {
            return trajectory.get(trajectory.size() - 1);
        }
        
        int low = 1;
        int high = trajectory.size() - 1;

        while (low != high) {
            int mid = (low + high) / 2;
            if (trajectory.get(mid).t < time) {
                low = mid + 1;
            } else {
                high = mid;
            }
            System.out.println(mid);
        }

        State previousState = trajectory.get(low - 1);
        State currentState = trajectory.get(low);

        if (currentState.t - previousState.t == 0) {
            return new State(currentState.t, currentState.pose, currentState.velocity);
        }
        
        return previousState.interpolate(currentState, (time - previousState.t) / (currentState.t - previousState.t));
    }

    public static class State {
        public double t;
        public Pose2d pose;
        public Vector3d velocity;

        public State(double t, Pose2d pose, Vector3d velocity) {
            this.t = t;
            this.pose = pose;
            this.velocity = velocity;
        }

        public State interpolate(State end, double x) {
            double newT = (end.t - t) * x + t;
            Vector3d startPoseVector = Vector3d.fromPose(pose);
            Vector3d endPoseVector = Vector3d.fromPose(end.pose);
            Pose2d newPose = (((endPoseVector.minus(startPoseVector)).times(x)).plus(startPoseVector)).toPose();
            Vector3d newVelocity = ((end.velocity.minus(velocity)).times(x)).plus(velocity);
            return new State(
                newT,
                newPose,
                newVelocity
            );
        }
    }
}