// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.StateSpaceUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.numbers.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.SwerveTrajectory;
import frc.lib.control.SwerveTrajectory.State;
import frc.paths.Path;
import frc.robot.Constants.AutoConstants;
import frc.robot.drive.Drivetrain;

public class TrajectoryFollower extends CommandBase {
  private Drivetrain drive;
  private Matrix<N3, N3> K = new Matrix<N3, N3>(Nat.N3(), Nat.N3());
  private Matrix<N3, N1> u, r, x, ff;
  private Timer timer = new Timer();
  private SwerveTrajectory trajectory;
  private Rotation2d offset;

  public TrajectoryFollower(Drivetrain drive, Path path) {
    addRequirements(drive);
    this.drive = drive;
    this.trajectory = path.getPath();

    K.fill(0.0);
    K.set(0, 0, AutoConstants.kPTranslationController);
    K.set(1, 1, AutoConstants.kPTranslationController);
    K.set(2, 2, AutoConstants.kPThetaController);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    drive.resetOdometry(trajectory.getInitialPose());
    offset = trajectory.getInitialPose().getRotation().plus(drive.getHeading());
  }

  @Override
  public void execute() {
    double time = timer.get();
    State refState = trajectory.sample(time);
    r = StateSpaceUtil.poseTo3dVector(refState.pose);
    x = StateSpaceUtil.poseTo3dVector(drive.getPose());
    // ff = VecBuilder.fill(1, 2, 3);
    u = (K.times(r.minus(x)));
    // u = (K.times(r.minus(x))).plus(ff);
    drive.autoDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        u.get(0, 0), 
                                                        u.get(1, 0), 
                                                        u.get(2, 0), 
                                                        drive.getHeading().times(-1.0).plus(offset)));
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
