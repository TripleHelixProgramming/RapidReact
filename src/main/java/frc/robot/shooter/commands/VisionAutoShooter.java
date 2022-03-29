package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.DCMotor;
import frc.lib.control.PIDController;
import frc.robot.drive.Drivetrain;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;
import frc.robot.status.Status;
import frc.robot.vision.Limelight;

public class VisionAutoShooter extends VisionShooter {
    Drivetrain drivetrain;
    Intake intake;
    
    public VisionAutoShooter(Shooter shooter, Limelight limelight, Drivetrain drivetrain, Intake intake) {
        super(shooter, limelight);
        this.drivetrain = drivetrain;
    }


    @Override
    public void execute() {
        super.execute();
        double targetDelta = Math.abs(rpm - velocity);

        // Stopped?
        boolean stopped = true;

        // Have target & aimed
        boolean onTarget = limelight.hasTarget() && (limelight.getState().xOffset < 5.0);

        // Within range
        double range = limelight.getState().distance;
        boolean inRange = (range > 1.5) && (range < 3.5);

        boolean upToSpeed = targetDelta < 50;

        if (stopped && onTarget && inRange && upToSpeed) {
            shooter.startTrigger();
            intake.setPushCargo(true);
        }

    }

}