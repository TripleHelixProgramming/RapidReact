// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.intake.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;

public class EjectIntake extends CommandBase {
  private Intake intake;

  public EjectIntake(Intake intake) {
    this.intake = intake; 
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.deploy();
    Timer.delay(0.2);
    intake.rollerOut();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
