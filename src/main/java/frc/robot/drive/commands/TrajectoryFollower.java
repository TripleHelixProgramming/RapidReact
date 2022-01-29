// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import java.time.OffsetDateTime;

import edu.wpi.first.math.*;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
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
import frc.robot.paths.gogogadget;

public class TrajectoryFollower extends CommandBase {
  private Drivetrain drive;
  private Matrix<N3, N3> K = new Matrix<N3, N3>(Nat.N3(), Nat.N3());
  private Matrix<N3, N1> u, r, x, ff;
  private Timer timer = new Timer();
  private double[][] path;
  private Rotation2d offset;
  private int index;

  public TrajectoryFollower(Drivetrain drive, gogogadget path) {
    addRequirements(drive);
    this.drive = drive;
    this.path = path.getPath();

    K.fill(0.0);
    K.set(0, 0, AutoConstants.kPTranslationController);
    K.set(1, 1, AutoConstants.kPTranslationController);
    K.set(2, 2, AutoConstants.kPThetaController);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    drive.resetOdometry(new Pose2d(new Translation2d(4.5,1.7), new Rotation2d(0)));
    // offset = trajectory.getInitialPose().getRotation().plus(drive.getHeading());
    offset = drive.getHeading();
    index = 0;
  }

  @Override
  public void execute() {
    // double time = timer.get();
    // State refState = trajectory.sample(time);
    Pose2d refPose = new Pose2d(new Translation2d(path[index][0], path[index][1]), new Rotation2d(path[index][2]));
    r = StateSpaceUtil.poseTo3dVector(refPose);
    x = StateSpaceUtil.poseTo3dVector(drive.getPose());
    // ff = VecBuilder.fill(1, 2, 3);
    u = (K.times(r.minus(x)));
    // u = (K.times(r.minus(x))).plus(ff);
    drive.autoDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        u.get(0, 0), 
                                                        u.get(1, 0), 
                                                        u.get(2, 0), 
                                                        drive.getHeading().times(-1.0).plus(offset)));
    index++;
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    // return timer.hasElapsed(trajectory.getTotalTimeSeconds());
    return index == path.length;
  }
}
