// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.climber.Climber;

public class DeployClimber extends CommandBase {
  private Climber climber;
  public DeployClimber(Climber climber) {
    this.climber = climber;
  }

  @Override
  public void initialize() {
    climber.deploy();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
