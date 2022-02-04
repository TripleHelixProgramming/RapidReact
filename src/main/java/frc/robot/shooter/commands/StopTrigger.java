// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class StopTrigger extends CommandBase {
  Shooter shooter;

  public StopTrigger(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.stopTrigger();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}