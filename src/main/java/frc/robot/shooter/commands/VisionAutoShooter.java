package frc.robot.shooter.commands;

import frc.robot.drive.Drivetrain;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;
import frc.robot.vision.Limelight;

public class VisionAutoShooter extends VisionShooter {
    Drivetrain drivetrain;
    Intake intake;
    
    public VisionAutoShooter(Shooter shooter, Limelight limelight, Drivetrain drivetrain, Intake intake) {
        super(shooter, limelight);
        this.drivetrain = drivetrain;
        this.intake = intake;
    }

    @Override
    public void execute() {
        super.execute();

        // Stopped?
        boolean stopped = drivetrain.getTranlationalVelocity() < 0.1;

        // Have target & aimed
        boolean onTarget = limelight.hasTarget() && (limelight.getState().xOffset < 5.0);

        // Within range
        boolean inRange = (distance > 1.5) && (distance < 3.5);

        boolean upToSpeed = rpmDelta < 50;

        if (stopped && onTarget && inRange && upToSpeed) {
            shooter.startTrigger();
            intake.setPushCargo(true);
        }
    }
}