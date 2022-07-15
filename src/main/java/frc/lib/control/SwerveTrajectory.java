package frc.lib.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import frc.lib.control.SwerveTrajectory.State.Deserializer;

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

    @JsonCreator
    public SwerveTrajectory(List<State> states) {
        trajectory = states;
    }

    public Pose2d getInitialPose() {
        return trajectory.get(0).pose;
    }

    public Pose2d getFinalPose() {
        return trajectory.get(trajectory.size() - 1).pose;
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
        }

        State previousState = trajectory.get(low - 1);
        State currentState = trajectory.get(low);

        if (currentState.t - previousState.t == 0) {
            return new State(currentState.t, currentState.pose, currentState.velocity);
        }
        
        return previousState.interpolate(currentState, (time - previousState.t) / (currentState.t - previousState.t));
    }

    @JsonDeserialize(using = Deserializer.class)
    public static class State {
        @JsonProperty("ts")
        @JsonAlias("t")
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

        // public static void main(String[] args) {
        //     File deployDir = new File(Filesystem.getDeployDirectory(), "trajectories");
        //     ObjectMapper mapper = new ObjectMapper();
        //     try {
        //         mapper.readValue(jsonString, State.class);
        //         System.out.println("success");
        //     } catch (IOException e) {
        //         System.out.println(e.getMessage());
        //     }
        // }

        public static class Deserializer extends StdDeserializer<State> {

            public Deserializer() {
                this(null);
            }

            public Deserializer(Class<?> vc) {
                super(vc);
            }

            @Override
            public State deserialize(JsonParser p, DeserializationContext ctxt)
                    throws IOException, JsonProcessingException {
                JsonNode node = p.getCodec().readTree(p);
                Pose2d pose = new Pose2d(node.get("x").asDouble(), node.get("y").asDouble(), new Rotation2d(node.get("heading").asDouble()));
                Vector3d velocity = new Vector3d(node.get("vx").asDouble(), node.get("vy").asDouble(), node.get("omega").asDouble());
                return new State(node.get("ts").asDouble(), pose, velocity);
            }
        }
    }
}