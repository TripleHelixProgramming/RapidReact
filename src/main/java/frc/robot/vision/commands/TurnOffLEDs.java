package frc.robot.vision.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;

public class TurnOffLEDs extends CommandBase {
  Limelight limelight;

  public TurnOffLEDs(Limelight limelight) {
    this.limelight = limelight;
    addRequirements(limelight);
  }

  @Override
  public void execute() {
    limelight.turnOffLEDs();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
