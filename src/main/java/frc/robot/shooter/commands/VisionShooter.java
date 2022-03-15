package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.DCMotor;
import frc.lib.control.PIDController;
import frc.robot.shooter.Shooter;
import frc.robot.status.Status;
import frc.robot.vision.Limelight;

public class VisionShooter extends CommandBase {

    Shooter shooter;
    Limelight limelight;
    double lastPosition, lastTime, hoodAngle;
    double rpm;
    private Timer timer = new Timer();
    Notifier controller = new Notifier(this::controller);
    private DCMotor flywheel = new DCMotor(0.002081897, 0, 0.302);
    private PIDController flywheelController = new PIDController(0.0175, 0, 0);
    boolean closeToTarget = false;
    double velocity = 0;

    public VisionShooter(Shooter shooter, Limelight limelight) {
        this.shooter = shooter;
        this.limelight = limelight;
        lastPosition = shooter.getEncoderPosition();
        lastTime = 0;
        timer.reset();
        timer.start();
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        
        closeToTarget = false;
        velocity = 0;       
        lastPosition = shooter.getEncoderPosition();
        timer.reset();
        lastTime = 0;
        // timer.start();
        controller.startPeriodic(0.02); 
    }

    void controller() {
        double time = timer.get();
        double position = shooter.getEncoderPosition();
        double dt = time - lastTime;
        if (dt < 0.0001) {
            dt = 0.001;
        }
        velocity = (position - lastPosition) / (dt) * 60;

        double distance = limelight.getState().distance;
        double rpm = Shooter.lookupVelocity(distance);
        double hoodAngle = Shooter.lookupHoodAngle(distance);

        shooter.setHoodPosition(hoodAngle);
        flywheelController.setReference(rpm);
        SmartDashboard.putNumber("Estimated velocity", velocity);

        double voltage = flywheel.solveFeedforward(rpm, 0) + flywheelController.calculate(velocity, dt);
        // double voltage = flywheel.solveFeedforward(rpm, 0);

        shooter.setShooterVoltage(Math.min(voltage, 11.5));
        
        lastPosition = position;
        lastTime = time;        
    }

    @Override
    public void execute() {
        double targetDelta = rpm - velocity;
        
        if ((Math.abs(targetDelta) < 20) && !closeToTarget) {
            closeToTarget = true;
            Status.getInstance().fillLEDs();
        }
    }

    @Override
    public void end(boolean interrupted) {
        controller.stop();
        closeToTarget= false;
        velocity = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}