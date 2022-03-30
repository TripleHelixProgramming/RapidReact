package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import frc.lib.control.DCMotor;
import frc.lib.control.PIDController;
import frc.robot.shooter.Shooter;
import frc.robot.status.Status;
import frc.robot.status.commands.FillLEDsCommand;

public class FlywheelController extends CommandBase {

    Shooter shooter;
    double lastPosition, lastTime, hoodAngle;
    double rpm;
    private Timer timer = new Timer();
    Notifier controller = new Notifier(this::controller);
    private DCMotor flywheel = new DCMotor(0.002081897, 0, 0.37);
    private PIDController flywheelController = new PIDController(0.0125, 0, 0);
    // private PIDController flywheelController = new PIDController(0.05, 0, 0);
    boolean closeToTarget = false;
    double velocity = 0;

    public FlywheelController(Shooter shooter, double rpm, double hoodAngle) {
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

        flywheelController.setReference(rpm);
        SmartDashboard.putNumber("Estimated velocity", velocity);

        double voltage = flywheel.solveFeedforward(rpm, 0) + flywheelController.calculate(velocity, dt);
        // double voltage = flywheel.solveFeedforward(rpm, 0);

        voltage = Math.min(voltage, 11.5);

        shooter.setShooterVoltage(voltage);
        
        lastPosition = position;
        lastTime = time;        
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