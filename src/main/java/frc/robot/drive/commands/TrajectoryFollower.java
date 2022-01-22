// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.*;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.numbers.*;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.drive.Drivetrain;

public class TrajectoryFollower extends CommandBase {
  private Drivetrain drive;
  private Matrix<N3, N3> K = new Matrix<N3, N3>(Nat.N3(), Nat.N3());
  private Matrix<N3, N1> u, r, x, ff;
  private Timer timer = new Timer();
  private Trajectory trajectory;

  public TrajectoryFollower(Drivetrain drive, Trajectory trajectory) {
    addRequirements(drive);
    this.drive = drive;
    this.trajectory = trajectory;

    // K.fill(0.0);
    // K.set(1, 1, 1.0);
    // K.set(2, 2, 1.0);
    // K.set(3, 3, 1.0);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    drive.resetOdometry(trajectory.getInitialPose());
  }

  @Override
  public void execute() {
    double time = timer.get();
    State refState = trajectory.sample(time);
    Pose2d refpose = refState.poseMeters;
    Pose2d curPose = drive.getPose();
    // r = StateSpaceUtil.poseTo3dVector(refState.poseMeters);
    // x = StateSpaceUtil.poseTo3dVector(drive.getPose());
    // ff = VecBuilder.fill(1, 2, 3);
    // u = (K.times(r.minus(x)));S
    // u = (K.times(r.minus(x))).plus(ff);
    // u = ff;
    // SmartDashboard.putNumber("xdot", u.get(1, 1));
    // SmartDashboard.putNumber("ydot", u.get(2, 1));
    // SmartDashboard.putNumber("thetadot", u.get(3, 1));
    // drive.autoDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
    //                                                     u.get(1, 1), 
    //                                                     u.get(2, 1), 
    //                                                     u.get(3, 1), 
    //                                                     drive.getHeading().times(-1.0)));

    drive.autoDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        AutoConstants.kPTranslationController * (refpose.getX() - curPose.getX()), 
                                                        AutoConstants.kPTranslationController * (refpose.getY() - curPose.getY()), 
                                                        AutoConstants.kPThetaController * (refpose.getRotation().getRadians() - curPose.getRotation().getRadians()), 
                                                        drive.getHeading().times(-1.0)));
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(trajectory.getTotalTimeSeconds());
  }
}
