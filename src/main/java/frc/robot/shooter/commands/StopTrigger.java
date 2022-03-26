package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;

public class StopTrigger extends CommandBase {
  Shooter shooter;
  Intake intake;

  public StopTrigger(Shooter shooter, Intake intake) {
    this.shooter = shooter;
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    shooter.stopTrigger();
    intake.topRollerStop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}