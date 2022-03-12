package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class EjectTrigger extends CommandBase {
  Shooter shooter;

  public EjectTrigger(Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    shooter.reverseTrigger();
  }
  
  @Override
  public boolean isFinished() {
    return true;
  }
}