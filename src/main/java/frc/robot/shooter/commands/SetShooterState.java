package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.Drivetrain;
import frc.robot.shooter.Shooter;

public class SetShooterState extends CommandBase {
  private Shooter shooter;
  private int rpm;
  private double hoodAngle;

  public SetShooterState(Shooter shooter, int rpm, double hoodAngle) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.rpm = rpm;
    this.hoodAngle = hoodAngle;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setShooterVelocity(rpm);
    shooter.setHoodPosition(hoodAngle);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
