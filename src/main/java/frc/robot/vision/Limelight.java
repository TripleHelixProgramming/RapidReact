package frc.robot.vision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.networktables.NetworkTableInstance.getDefault;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private volatile VisionState state = new VisionState(0,0,0);

  double h = 2.1;
  double theta = 45;

  public Limelight() {
    getDefault().getTable("limelight").getEntry("tx").addListener(event -> {
      if (getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1) {
        double xOffset = getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double yOffset = getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double distance = h/(Math.tan(Math.toRadians(yOffset+theta))*Math.cos(Math.toRadians(xOffset)));
        SmartDashboard.putNumber("Distance", distance);
        double latency = getDefault().getTable("limelight").getEntry("tl").getDouble(0) / 1000.0 + 0.011;
        state = new VisionState(xOffset, distance, Timer.getFPGATimestamp() - latency);
      }
    }, EntryListenerFlags.kUpdate);
  }

  public VisionState getState() {
    return state;
  }

  public boolean hasTarget() {
    return getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1.0;
  }

  public void turnOffLEDs() {
    getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }

  public void turnOnLEDs() {
    getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }

  public static class VisionState {
    public final double xOffset;
    public final double distance;
    public final double timestamp;

    public VisionState(double xOffset, double distance, double timestamp) {
      this.xOffset = xOffset;
      this.distance = distance;
      this.timestamp = timestamp;
    }
  }
}