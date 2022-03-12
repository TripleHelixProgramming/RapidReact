package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;

public class TurnOnLEDs extends CommandBase {
  Limelight limelight;

  public TurnOnLEDs(Limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void initialize() {
    limelight.turnOnLEDs();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
