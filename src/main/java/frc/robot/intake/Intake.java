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
  private TalonSRX bottomMotor = new TalonSRX(ElectricalConstants.kIntakeBottomPort);
  private TalonSRX topMotor = new TalonSRX(ElectricalConstants.kIntakeTopPort);

  private boolean pushCargo = false;
  private boolean topRollerRunning = false;

  public Intake() {
    bottomMotor.configFactoryDefault();
    bottomMotor.setNeutralMode(NeutralMode.Brake);
    bottomMotor.enableVoltageCompensation(true);
    bottomMotor.configVoltageCompSaturation(12);
    bottomMotor.configPeakCurrentDuration(100,0);
    bottomMotor.configPeakCurrentLimit(60,0);
    bottomMotor.configContinuousCurrentLimit(40);
    bottomMotor.enableCurrentLimit(true);   
    topMotor.configFactoryDefault();
    topMotor.setNeutralMode(NeutralMode.Brake);
    topMotor.enableVoltageCompensation(true);
    topMotor.configVoltageCompSaturation(12);
    topMotor.configPeakCurrentDuration(100,0);
    topMotor.configPeakCurrentLimit(60,0);
    topMotor.configContinuousCurrentLimit(40);
    topMotor.enableCurrentLimit(true);   
  }

  public boolean isPushCargo() {
    return pushCargo;
  }

  public void setPushCargo(boolean pushCargo) {
    this.pushCargo = pushCargo;
  }

  /**
   * Decide what to do with the intake top roller.
   * Only run it for pushing the cargo in if the intake is in.
   */
  public void periodic() {
    if (!isExtended()) {
      if (pushCargo) {
        if (!topRollerRunning) {
          topRollerShoot();
          topRollerRunning = true;
        }
      } else if (topRollerRunning) {
        topRollerStop();
        topRollerRunning = false;
      }
    }
  }
  
  public void deploy() {
    solenoid.set(Value.kForward);
  }

  public void retract() {
    solenoid.set(Value.kReverse);
  }

  public void fastRollerIn() {
    bottomMotor.set(ControlMode.PercentOutput, -0.85);
    topMotor.set(ControlMode.PercentOutput, -0.4);
  }

  // public void rollerIn() {
  //   motor.set(ControlMode.PercentOutput, IntakeConstants.kRollerSpeed);
  // }

  public void rollerOut() {
    bottomMotor.set(ControlMode.PercentOutput, IntakeConstants.kRollerEjectSpeed);
    topMotor.set(ControlMode.PercentOutput, IntakeConstants.kRollerEjectSpeed);
  }

  public void topRollerIn() {
    topMotor.set(ControlMode.PercentOutput, -0.6);
  }

  public void topRollerShoot() {
    topMotor.set(ControlMode.PercentOutput, -0.5);
  }

  public void bottomRollerStop() {
    bottomMotor.set(ControlMode.PercentOutput, 0);
  }

  public void topRollerStop() {
    topMotor.set(ControlMode.PercentOutput, 0);
  }

  // Push a ball into the trigger
  // public void rollerPush() {
  //   motor.set(ControlMode.PercentOutput, 0.30);
  // }

  // Status of the intake arm's extended state.
  public boolean isExtended() {
    // return solenoid.get() == Value.kForward;
    return false;
  }
}