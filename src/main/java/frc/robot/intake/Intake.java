// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.intake.commands.RetractIntake;

public class Intake extends SubsystemBase {
  private DoubleSolenoid solenoid = new DoubleSolenoid(
                                        ElectricalConstants.kPneumaticHub,
                                        ElectricalConstants.kIntakeDeployPort, 
                                        ElectricalConstants.kIntakeRetractPort);
  private TalonSRX motor = new TalonSRX(ElectricalConstants.kIntakeRollerPort);

  public Intake() {
    super();
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);
    motor.enableVoltageCompensation(true);
    motor.configVoltageCompSaturation(12);
    motor.configPeakCurrentDuration(100,0);
    motor.configPeakCurrentLimit(60,0);
    motor.configContinuousCurrentLimit(40);
    motor.enableCurrentLimit(true);

    setDefaultCommand(new RetractIntake(this));    
  }

  public void deploy() {
    solenoid.set(Value.kForward);
  }

  public void retract() {
    solenoid.set(Value.kReverse);
  }

  public void rollerIn() {
    motor.set(ControlMode.PercentOutput, IntakeConstants.kRollerSpeed);
  }

  public void rollerStop() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  // Status of the intake arm's extended state.
  public boolean isExtended() {
    return solenoid.get() == Value.kForward;
  }

}