package frc.robot.intake.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;

public class RetractIntake extends CommandBase {
  private Intake intake;
  private double startRetractTime;
  private boolean topStopped = false;

  public RetractIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.retract();
    intake.bottomRollerStop();
    topStopped = false;
    startRetractTime = Timer.getFPGATimestamp();    
  }

  @Override
  public void execute() {
    double timeElapsed = Timer.getFPGATimestamp() - startRetractTime;
    if (!topStopped && (timeElapsed >= 0.75)) {
      intake.topRollerStop();
      topStopped = true;
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
