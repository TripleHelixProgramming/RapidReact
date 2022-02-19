// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.Timer;

import static edu.wpi.first.networktables.NetworkTableInstance.getDefault;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private volatile VisionState state = new VisionState(0,0,0);

  public Limelight() {
    getDefault().getTable("limelight").getEntry("tx").addListener(event -> {
      if (getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1) {
        double xOffset = getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double yOffset = getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double latency = getDefault().getTable("limelight").getEntry("tl").getDouble(0) + 0.011;
        state = new VisionState(xOffset, yOffset, Timer.getFPGATimestamp() - latency);
      }
    }, EntryListenerFlags.kUpdate);
  }

  public VisionState getState() {
    return state;
  }

  public static class VisionState {
    public final double xOffset;
    public final double yOffset;
    public final double timestamp;

    public VisionState(double xOffset, double yOffset, double timestamp) {
      this.xOffset = xOffset;
      this.yOffset = yOffset;
      this.timestamp = timestamp;
    }
  }
}