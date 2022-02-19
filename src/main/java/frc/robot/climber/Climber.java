// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElectricalConstants;

public class Climber extends SubsystemBase {
  private Solenoid climber = new Solenoid(ElectricalConstants.kPneumaticHub, ElectricalConstants.kClimberPort);

  public void deploy() {
    climber.set(true);
  }

  public void disable() {
    climber.set(false);
  }
}
