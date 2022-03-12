package frc.robot.intake.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.Intake;

public class RetractIntake extends CommandBase {
  private Intake intake;

  public RetractIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.retract();
    Timer.delay(0.2);
    intake.rollerStop();
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
