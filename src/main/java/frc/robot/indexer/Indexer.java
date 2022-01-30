// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.ElectricalConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  CANSparkMax motor;

  public Indexer() {
    motor = new CANSparkMax(ElectricalConstants.kIndexerPort, MotorType.kBrushless);

    motor.restoreFactoryDefaults();
    motor.enableVoltageCompensation(12);
  }

  public void rollerIn() {
    motor.set(-0.7);
  }

  public void rollerStop() {
    motor.set(0);
  }
}
