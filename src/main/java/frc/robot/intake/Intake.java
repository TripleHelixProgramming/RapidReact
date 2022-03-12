package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private DoubleSolenoid solenoid = new DoubleSolenoid(
                                        ElectricalConstants.kPneumaticHub,
                                        ElectricalConstants.kIntakeDeployPort, 
                                        ElectricalConstants.kIntakeRetractPort);
  private TalonSRX motor = new TalonSRX(ElectricalConstants.kIntakeRollerPort);

  public Intake() {
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);
    motor.enableVoltageCompensation(true);
    motor.configVoltageCompSaturation(12);
    motor.configPeakCurrentDuration(100,0);
    motor.configPeakCurrentLimit(60,0);
    motor.configContinuousCurrentLimit(40);
    motor.enableCurrentLimit(true);   
  }

  public void deploy() {
    solenoid.set(Value.kForward);
  }

  public void retract() {
    solenoid.set(Value.kReverse);
  }

  public void fastRollerIn() {
    motor.set(ControlMode.PercentOutput, 0.95);
  }

  public void rollerIn() {
    motor.set(ControlMode.PercentOutput, IntakeConstants.kRollerSpeed);
  }

  public void rollerOut() {
    motor.set(ControlMode.PercentOutput, IntakeConstants.kRollerEjectSpeed);
  }

  public void rollerStop() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  // Status of the intake arm's extended state.
  public boolean isExtended() {
    // return solenoid.get() == Value.kForward;
    return false;
  }
}