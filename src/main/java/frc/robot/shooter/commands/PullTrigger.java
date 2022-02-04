// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class PullTrigger extends CommandBase {
  Shooter shooter;
  boolean hasFired = false;

  public PullTrigger(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    this.hasFired = false;
  }

  @Override
  public void execute() {
    double currentVelocity = shooter.getShooterVelocity();
    double targetVelocity = shooter.getTargetVelocity();

    SmartDashboard.putNumber("Shooter Target Velocity", targetVelocity);
    SmartDashboard.putNumber("Shooter Current Velocity", currentVelocity);
    // Wait for the shooter wheel to reach the target speed before "firing".
    // If shooter wheel slows below some percent of the target, stop the trigger and wait some more.
    if (0.0 < targetVelocity) {
      if (currentVelocity < (targetVelocity * 0.90)) {
        shooter.stopTrigger();
        hasFired = false;
      } else {
        shooter.startTrigger();
        hasFired = true;
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
