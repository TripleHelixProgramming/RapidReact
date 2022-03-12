package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.shooter.Shooter;

public class ResetHood extends CommandBase {
  Shooter shooter;
  public ResetHood(Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    shooter.setHoodSpeed(-ShooterConstants.kHoodSpeed);
  }
  
  @Override
  public void end(boolean interrupted) {
    shooter.resetHoodAngle();
    shooter.stopHood();
  }

  @Override
  public boolean isFinished() {
    return shooter.checkHoodCurrentLimit();
  }
}
