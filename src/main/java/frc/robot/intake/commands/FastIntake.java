package frc.robot.intake.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;

public class FastIntake extends CommandBase {
  private Intake intake;
  private double startTime;
  private boolean rollersRunning = false;

  public FastIntake(Intake intake) {
    this.intake = intake; 
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.deploy();
    rollersRunning = false;
    startTime = Timer.getFPGATimestamp();    
  }

  @Override
  public void execute() {
    double timeElapsed = Timer.getFPGATimestamp() - startTime;
    if (!rollersRunning && (timeElapsed >= 0.20)) {
      intake.fastRollerIn();
      rollersRunning = true;
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
