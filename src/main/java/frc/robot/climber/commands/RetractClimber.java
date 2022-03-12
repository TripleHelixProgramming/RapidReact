package frc.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.climber.Climber;

public class RetractClimber extends CommandBase {
  private Climber climber;
  public RetractClimber(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.disable();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
