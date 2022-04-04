package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;

public class TurnOnLEDs extends CommandBase {
  Limelight limelight;

  public TurnOnLEDs(Limelight limelight) {
    this.limelight = limelight;
    addRequirements(limelight);
  }

  @Override
  public void execute() {
    limelight.turnOnLEDs();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
