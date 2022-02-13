package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.DCMotor;
import frc.lib.control.PIDController;
import frc.robot.shooter.Shooter;

public class FlywheelController extends CommandBase {

    private Shooter shooter;
    private double lastPosition, lastTime;
    private int rpm;
    private Timer timer = new Timer();
    private Notifier controller = new Notifier(this::controller);
    private DCMotor flywheel = new DCMotor(0.002081897, 0, 0.317466);
    private PIDController flywheelController = new PIDController(0.025, 0, 0.0002);

    public FlywheelController(Shooter shooter, int rpm) {
        this.shooter = shooter;
        this.rpm = rpm;
        lastPosition = shooter.getEncoderPosition();
        lastTime = 0;
        timer.reset();
        timer.start();
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        controller.startPeriodic(0.02);
        SmartDashboard.putNumber("Set Velocity", 0);
        SmartDashboard.putNumber("Shooter P", 0.025);
        SmartDashboard.putNumber("Shooter D", 0.000);
        
    }

    private void controller() {
        // shooter.setHoodPosition(65);
        double time = timer.get();
        double position = shooter.getEncoderPosition();
        double dt = time - lastTime;
        double velocity = (position - lastPosition) / (dt) * 60;

        double targetVelocity = SmartDashboard.getNumber("Set Velocity", 0);
        double P = SmartDashboard.getNumber("Shooter P", 0);
        double D = SmartDashboard.getNumber("Shooter D", 0);
        flywheelController.setP(P);
        flywheelController.setD(D);
        flywheelController.setReference(targetVelocity);
        SmartDashboard.putNumber("Shooter Velocity", shooter.getShooterVelocity());
        SmartDashboard.putNumber("Estimated velocity", velocity);
        // P = 0.03
        // D = 0.00025

        double voltage = flywheel.solveFeedforward(targetVelocity, 0) + flywheelController.calculate(velocity, dt);
        // double voltage = flywheel.solveFeedforward(targetVelocity, 0);

        shooter.setShooterVoltage(voltage);

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