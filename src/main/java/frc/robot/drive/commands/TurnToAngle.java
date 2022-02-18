// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import frc.lib.control.PIDController;
import frc.lib.util.InterpolatingPoseMap;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.drive.Drivetrain;

public class TurnToAngle extends CommandBase {
  private Drivetrain drive;
  private PIDController thetaController;
  private Rotation2d offset;
  private double lastTime = 0;
  private Timer timer = new Timer();
  private InterpolatingPoseMap map;

  public TurnToAngle(Drivetrain drive) {
    addRequirements(drive);
    this.drive = drive;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    drive.resetOdometry(new Pose2d(new Translation2d(0,0),  new Rotation2d(0)));
    
    thetaController = new PIDController(5.5, 0, 0.0);
    thetaController.setContinous(true);
    thetaController.setInputRange(Math.PI * 2);

    lastTime = 0;

    map = new InterpolatingPoseMap(100);
    map.addPose(drive.getPose(), timer.get());
  }

  @Override
  public void execute() {
    double time = timer.get();
    double dt = time - lastTime;
    Pose2d currentPose = drive.getPose();

    thetaController.setReference(0.5);

    double omega = -thetaController.calculate(currentPose.getRotation().getRadians(), dt);

    drive.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        0,
                                                        0,
                                                        omega,
                                                        drive.getHeading()),
                                                        false);
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
