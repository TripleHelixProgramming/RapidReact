package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;

public class TurnOffLEDs extends CommandBase {
  Limelight limelight;

  public TurnOffLEDs(Limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void initialize() {
    limelight.turnOffLEDs();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
