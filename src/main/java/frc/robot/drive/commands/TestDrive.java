package frc.robot.drive.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.Drivetrain;

public class TestDrive extends CommandBase {
  Drivetrain drive;

  public TestDrive(Drivetrain drive) {
    addRequirements(drive);
    this.drive = drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Velocity 0", 0);
    SmartDashboard.putNumber("Velocity 1", 0);
    SmartDashboard.putNumber("Velocity 2", 0);
    SmartDashboard.putNumber("Velocity 3", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double v0 = SmartDashboard.getNumber("Velocity 0", 0);
    double v1 = SmartDashboard.getNumber("Velocity 1", 0);
    double v2 = SmartDashboard.getNumber("Velocity 2", 0);
    double v3 = SmartDashboard.getNumber("Velocity 3", 0);
    SwerveModuleState s0 = new SwerveModuleState(v0, new Rotation2d(0));
    SwerveModuleState s1 = new SwerveModuleState(v1, new Rotation2d(0));
    SwerveModuleState s2 = new SwerveModuleState(v2, new Rotation2d(0));
    SwerveModuleState s3 = new SwerveModuleState(v3, new Rotation2d(0));
    SwerveModuleState[] states = new SwerveModuleState[]{s0, s1, s2, s3};
    drive.setModuleStates(states);
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
