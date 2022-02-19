// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import frc.lib.HelixJoysticks;
import frc.lib.control.PIDController;
import frc.lib.util.InterpolatingPoseMap;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.drive.Drivetrain;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.VisionState;

public class TurnToAngle extends CommandBase {
  private Drivetrain drive;
  private Limelight limelight;
  private PIDController thetaController;
  private double lastTime = 0;
  private Timer timer = new Timer();
  private InterpolatingPoseMap map;
  private HelixJoysticks joysticks;

  public TurnToAngle(Drivetrain drive, Limelight limelight, HelixJoysticks joysticks) {
    addRequirements(drive);
    this.drive = drive;
    this.limelight = limelight;
    this.joysticks = joysticks;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    
    thetaController = new PIDController(0.1, 0, 0.0);
    thetaController.setContinous(true);
    thetaController.setInputRange(360);

    lastTime = 0;

    map = new InterpolatingPoseMap(1000);
    map.addPose(drive.getPose(), timer.get());
  }

  @Override
  public void execute() {
    double time = timer.get();
    double dt = time - lastTime;
    VisionState state = limelight.getState();
    map.addPose(drive.getPose(), Timer.getFPGATimestamp());
    double offset = 0;
    // double offset = map.getLatestPose().getRotation().minus(map.getPose(state.timestamp).getRotation()).getDegrees();

    thetaController.setReference(0);

    // double omega = -thetaController.calculate(currentPose.getRotation().getRadians(), dt);
    double omega = -thetaController.calculate(state.xOffset + offset, dt);

    drive.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        joysticks.getX() * DriveConstants.kMaxTranslationalVelocity,
                                                        joysticks.getY() * DriveConstants.kMaxTranslationalVelocity,
                                                        omega,
                                                        drive.getHeading()),
                                                        true);
    lastTime = time;
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    drive.brake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}