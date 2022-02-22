// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;

public class TurnOffLEDs extends CommandBase {
  public TurnOffLEDs(Limelight limelight) {
    limelight.turnOffLEDs();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
