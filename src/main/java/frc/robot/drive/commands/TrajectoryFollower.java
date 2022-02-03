// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.PIDController;
import frc.lib.control.SwerveTrajectory;
import frc.lib.control.SwerveTrajectory.State;
import frc.paths.Path;
import frc.robot.Constants.AutoConstants;
import frc.robot.drive.Drivetrain;

public class TrajectoryFollower extends CommandBase {
  private Drivetrain drive;
  private SwerveTrajectory trajectory;
  private PIDController xController, yController, thetaController;
  private Rotation2d offset;
  private double lastTime = 0;
  private Timer timer = new Timer();

  public TrajectoryFollower(Drivetrain drive, Path path) {
    addRequirements(drive);
    this.drive = drive;
    this.trajectory = path.getPath();
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    drive.resetOdometry(trajectory.getInitialPose());
    offset = trajectory.getInitialPose().getRotation().plus(drive.getHeading());
    
    xController = new PIDController(AutoConstants.kPTranslationController, 0, 0);
    yController = new PIDController(AutoConstants.kPTranslationController, 0, 0);
    thetaController = new PIDController(AutoConstants.kPThetaController, 0, 0);
    thetaController.setContinous(true);
    thetaController.setInputRange(Math.PI * 2);

    lastTime = 0;
  }

  @Override
  public void execute() {
    double time = timer.get();
    double dt = time - lastTime;
    State refState = trajectory.sample(time);
    Pose2d currentPose = drive.getPose();

    xController.setReference(refState.pose.getX());
    yController.setReference(refState.pose.getY());
    thetaController.setReference(refState.pose.getRotation().getRadians());

    drive.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        xController.calculate(currentPose.getX(), dt),
                                                        yController.calculate(currentPose.getY(), dt),
                                                        thetaController.calculate(currentPose.getRotation().getRadians(), dt),
                                                        drive.getHeading().minus(offset)),
                                                        true);
    lastTime = time;
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(trajectory.getTotalTime());
  }
}