// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.*;
import edu.wpi.first.math.numbers.*;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj.Timer;
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

    K.fill(0);
    K.set(1, 1, AutoConstants.kPTranslationController);
    K.set(2, 2, AutoConstants.kPTranslationController);
    K.set(3, 3, AutoConstants.kPThetaController);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    double time = timer.get();
    State refState = trajectory.sample(time);
    r = StateSpaceUtil.poseTo3dVector(refState.poseMeters);
    x = StateSpaceUtil.poseTo3dVector(drive.getPose());
    ff = VecBuilder.fill(1, 2, 3);
    u = (K.times(r.minus(x))).plus(ff);
    // u = ff;
    drive.autoDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        u.get(1, 1), 
                                                        u.get(2, 1), 
                                                        u.get(3, 1), 
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
