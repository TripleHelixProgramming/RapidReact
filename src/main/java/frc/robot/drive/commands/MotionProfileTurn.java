// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.Drivetrain;

public class MotionProfileTurn extends CommandBase {
  Drivetrain drive;
  double offset;
  double target;
  double velocity;
  double dt;
  double maxAcceleration, maxVelocity;
  ProfiledPIDController controller;

  public MotionProfileTurn(Drivetrain drive, double offset) {
    this.drive = drive;
    this.offset = offset;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    // target = offset + drive.getPose().getRotation().getRadians();
    SmartDashboard.putString("Turning", "Enabled");
    controller = new ProfiledPIDController(0.35, 0.0, 0.0, new TrapezoidProfile.Constraints(1, 1), 0.02);
    // controller.enableContinuousInput(-Math.PI, Math.PI);
    velocity = 0;
    maxAcceleration = 0.5;
    maxVelocity = 1;
    dt = 0.02;
  }

  @Override
  public void execute() {
    double theta = drive.getPose().getRotation().getRadians();
    double error = shortAngle(0, theta);
    double feedback = controller.calculate(0, shortAngle(0, theta));
    double feedforward = solveVelocity(maxVelocity, maxAcceleration, error, dt);
    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("Velocity", feedforward);
    drive.drive(new ChassisSpeeds(0, 0, feedforward), false);
  }

  public double solveVelocity(double maxVelocity, double maxAcceleration, double error, double dt) {
    double accel = 0;
    if (Math.sqrt(2*maxAcceleration*Math.abs(error)) > Math.abs(velocity)) {
      accel = -maxAcceleration * Math.signum(error);
    } else {
      accel = maxAcceleration * Math.signum(error);
    }
    SmartDashboard.putNumber("Accel", accel);
    velocity = Math.min(maxVelocity, Math.max(-maxVelocity, velocity + accel * dt));
    // velocity += accel * dt;
    return velocity;
  }

  public double shortAngle(double targetAngle, double currentAngle) {
    double diff = (targetAngle - currentAngle + Math.PI) % (2* Math.PI) - Math.PI;
    diff = diff < -Math.PI ? diff + 2 * Math.PI : diff;
    return diff;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.brake();
    SmartDashboard.putString("Turning", "Disabled");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
