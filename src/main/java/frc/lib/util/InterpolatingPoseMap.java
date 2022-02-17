package frc.lib.util;

import java.util.TreeMap;

import edu.wpi.first.math.geometry.Pose2d;

public class InterpolatingPoseMap {
    private TreeMap<Double, Pose2d> history;
    private int mapSize;

    public InterpolatingPoseMap(int mapSize) {
        this.mapSize = mapSize;
        history = new TreeMap<Double, Pose2d>();
    }

    public void addPose(Pose2d pose, double timestamp) {
        history.put(timestamp, pose);
        if (history.size() > mapSize) {
            history.remove(history.firstKey());
        }
    }

    public Pose2d getLatestPose() {
        return history.lastEntry().getValue();
    }

    public Pose2d getPose(double timestamp) {
        if (timestamp <= history.firstKey()) {
            return history.firstEntry().getValue();
        } 
        if (timestamp >= history.lastKey()) {
            return history.lastEntry().getValue();
        }
        double low = history.floorKey(timestamp);
        double high = history.ceilingKey(timestamp);
        return history.get(low).interpolate(history.get(high), (timestamp - low) / (high - low));
    }
}