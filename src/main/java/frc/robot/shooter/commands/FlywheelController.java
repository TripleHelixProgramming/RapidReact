package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import frc.lib.control.DCMotor;
import frc.lib.control.PIDController;
import frc.robot.shooter.Shooter;
import frc.robot.status.commands.FillLEDsCommand;

public class FlywheelController extends CommandBase {

    private final double kTolerance = 0.8;
    Shooter shooter;
    double lastPosition, lastTime, hoodAngle;
    int rpm;
    private Timer timer = new Timer();
    Notifier controller = new Notifier(this::controller);
    private DCMotor flywheel = new DCMotor(0.002081897, 0, 0.317466);
    private PIDController flywheelController = new PIDController(0.02, 0, 0);
    // private PIDController flywheelController = new PIDController(0.05, 0, 0);
    private boolean closeToTarget = false;

    public FlywheelController(Shooter shooter, int rpm, double hoodAngle) {
        this.shooter = shooter;
        this.rpm = rpm;
        this.hoodAngle = hoodAngle;
        lastPosition = shooter.getEncoderPosition();
        lastTime = 0;
        timer.reset();
        timer.start();
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setHoodPosition(hoodAngle);
        controller.startPeriodic(0.02);        
    }

    void controller() {
        double time = timer.get();
        double position = shooter.getEncoderPosition();
        double dt = time - lastTime;
        double velocity = (position - lastPosition) / (dt) * 60;

        flywheelController.setReference(rpm);
        SmartDashboard.putNumber("Estimated velocity", velocity);

        double voltage = flywheel.solveFeedforward(rpm, 0) + flywheelController.calculate(velocity, dt);
        // double voltage = flywheel.solveFeedforward(rpm, 0);

        shooter.setShooterVoltage(voltage);

        // 
        if (kTolerance < (velocity/rpm) && !closeToTarget) {
            closeToTarget = true;
            // Only do this if it is a preset shot
            if (this instanceof PresetFlywheelController) {
                new ScheduleCommand(new FillLEDsCommand());
            }
        }
        lastPosition = position;
        lastTime = time;        
    }

    @Override
    public void end(boolean interrupted) {
        controller.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}