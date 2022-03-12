package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class StopTrigger extends CommandBase {
  Shooter shooter;

  public StopTrigger(Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    shooter.stopTrigger();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}