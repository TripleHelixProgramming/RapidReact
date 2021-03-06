package frc.robot.drive.commands;

import frc.lib.HelixJoysticks;
import frc.lib.control.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.drive.Drivetrain;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.VisionState;

public class TurnToAngle extends CommandBase {
  private Drivetrain drive;
  private Limelight limelight;
  private PIDController thetaController;
  private HelixJoysticks joysticks;
  private boolean sawTarget;

  public TurnToAngle(Drivetrain drive, Limelight limelight, HelixJoysticks joysticks) {
    addRequirements(drive);
    this.drive = drive;
    this.limelight = limelight;
    this.joysticks = joysticks;
  }

  @Override
  public void initialize() {
    thetaController = new PIDController(0.16, 0, 0.0);
    thetaController.setContinous(true);
    thetaController.setInputRange(360);
    limelight.turnOnLEDs();
    // sawTarget = limelight.hasTarget();
  }

  @Override
  public void execute() {
    // sawTarget = limelight.hasTarget();
    // if (sawTarget) {
      VisionState state = limelight.getState();

      thetaController.setReference(drive.getPose(state.timestamp).getRotation().getDegrees() - state.xOffset);

      double omega = -thetaController.calculate(drive.getPose().getRotation().getDegrees(), 0.02);

      drive.openLoopDrive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                          joysticks.getX() * DriveConstants.kMaxTranslationalVelocity,
                                                          joysticks.getY() * DriveConstants.kMaxTranslationalVelocity,
                                                          omega,
                                                          drive.getHeading()));
    // }
    
  }

  @Override
  public void end(boolean interrupted) {
    drive.brake();
    // limelight.turnOffLEDs();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}