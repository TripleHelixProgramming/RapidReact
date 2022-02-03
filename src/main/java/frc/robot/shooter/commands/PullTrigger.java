// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class PullTrigger extends CommandBase {
  Shooter shooter;
  boolean hasFired = false;

  public PullTrigger(Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    this.hasFired = false;
  }

  @Override
  public void execute() {
    // Wait for the shooter wheel to reach the target speed before "firing".
    // If shooter wheel slows below some percent of the target, stop the trigger and wait some more.
    if (shooter.getShooterVelocity() < (shooter.getTargetVelocity() * 0.95)) {
      shooter.stopTrigger();
      hasFired = false;
    } else if (shooter.getShooterVelocity() >= shooter.getTargetVelocity()) {
      shooter.startTrigger();
      hasFired = true;
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
