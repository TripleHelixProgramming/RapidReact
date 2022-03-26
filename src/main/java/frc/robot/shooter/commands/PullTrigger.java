package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;

public class PullTrigger extends CommandBase {
  Shooter shooter;
  Intake intake;

  public PullTrigger(Shooter shooter, Intake intake) {
    this.shooter = shooter;
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    shooter.startTrigger();
    intake.topRollerIn();
  }
  
  @Override
  public boolean isFinished() {
    return true;
  }
}