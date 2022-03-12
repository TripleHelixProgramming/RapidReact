package frc.robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElectricalConstants;

public class Climber extends SubsystemBase {
  private DoubleSolenoid climber = new DoubleSolenoid(ElectricalConstants.kPneumaticHub, ElectricalConstants.kClimberDeployPort,  ElectricalConstants.kClimberRetractPort);

  // public void toggle() {
  //   boolean deployed = climber.get();
  //   climber.set(!deployed);
  // }

  public void deploy() {
    climber.set(Value.kForward);
  }

  public void disable() {
    climber.set(Value.kReverse);
  }
}
