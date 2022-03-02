// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class ResetEncoder extends CommandBase {
  Shooter shooter;

  public ResetEncoder(Shooter shooter) {
    this.shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.resetHoodEncoder();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
