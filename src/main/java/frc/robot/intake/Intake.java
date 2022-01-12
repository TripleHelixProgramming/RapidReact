// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private DoubleSolenoid solenoid = new DoubleSolenoid(IntakeConstants.intakeDeployID, IntakeConstants.intakeRetractID);

  public Intake() {

  }

  public void deploy() {

  }

  public void retract() {

  }

  public void rollerIn() {

  }

  public void rollerStop() {

  }
}