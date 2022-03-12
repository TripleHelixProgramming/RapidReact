package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.Shooter;

public class SpinUpShooter extends CommandBase {
  Shooter shooter;
  int rpm;
  public SpinUpShooter(Shooter shooter, int rpm) {
    this.shooter = shooter;
    addRequirements(shooter);
    this.rpm = rpm;
    SmartDashboard.putNumber("Input Velocity", this.rpm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = SmartDashboard.getNumber("Input Velocity", 0);
    shooter.setShooterVelocity(velocity);
    SmartDashboard.putNumber("Shooter Velocity", shooter.getShooterVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
