package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.climber.Climber;

public class ToggleClimber extends CommandBase {
  private Climber climber;
  public ToggleClimber(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    // climber.toggle();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
