// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.Drivetrain;

public class MotionProfileTurn extends CommandBase {
  Drivetrain drive;
  double offset;
  double target;
  ProfiledPIDController controller;

  public MotionProfileTurn(Drivetrain drive, double offset) {
    this.drive = drive;
    this.offset = offset;
    addRequirements(drive);
    controller = new ProfiledPIDController(0.0, 0.0, 0.0, new TrapezoidProfile.Constraints(10, 10));
    controller.enableContinuousInput(-Math.PI, Math.PI);
  }

  @Override
  public void initialize() {
    target = offset + drive.getPose().getRotation().getRadians();
  }

  @Override
  public void execute() {
    double theta = drive.getPose().getRotation().getRadians();
    double feedback = controller.calculate(theta, 0);
    double feedforward = controller.getSetpoint().velocity;
    drive.drive(new ChassisSpeeds(0, 0, feedforward), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
