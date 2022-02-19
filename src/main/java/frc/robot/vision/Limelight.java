// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import org.photonvision.PhotonCamera;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.networktables.NetworkTableInstance.getDefault;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private VisionState state;
  private int loops = 0;

  public Limelight() {
    SmartDashboard.putString("Limelight", "Enabled");
    getDefault().getTable("limelight").getEntry("tx").addListener(event -> {
      state = new VisionState(getXOffset(), Timer.getFPGATimestamp());
    }, EntryListenerFlags.kUpdate);
  }
  
  @Override
  public void periodic() {
    state = new VisionState(getXOffset(), Timer.getFPGATimestamp());
  }

  public double getXOffset() {
    double xOffset = getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    loops++;
    SmartDashboard.putNumber("X offset", xOffset);
    SmartDashboard.putNumber("Loops", loops);
    return xOffset;
  }

  public static class VisionState {
    double tx;
    double timestamp;

    public VisionState(double tx, double timestamp) {
      this.tx = tx;
      this.timestamp = timestamp;
    }
  }
}