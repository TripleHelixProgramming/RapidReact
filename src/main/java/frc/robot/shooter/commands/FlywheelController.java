package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.DCMotor;
import frc.robot.shooter.Shooter;

public class FlywheelController extends CommandBase {

    private Shooter shooter;
    private double lastPosition, lastTime;
    private int rpm;
    private Timer timer = new Timer();
    private Notifier controller = new Notifier(this::controller);
    private DCMotor flywheel = new DCMotor(0.002081897, 0, 0.317466);

    public FlywheelController(Shooter shooter, int rpm) {
        this.shooter = shooter;
        this.rpm = rpm;
        lastPosition = shooter.getShooterPosition();
        lastTime = 0;
        timer.reset();
        timer.start();
        addRequirements(shooter);
        SmartDashboard.putNumber("Set Velocity", 0);
    }

    @Override
    public void initialize() {
        controller.startPeriodic(0.02);
    }

    private void controller() {
        double time = timer.get();
        double position = shooter.getShooterPosition();
        double velocity = (position - lastPosition) / (time - lastTime);
        // double voltage = flywheel.solveFeedforward(velocity, 0);
        double targetVelocity = SmartDashboard.getNumber("Set Velocity", 0);
        double voltage = flywheel.solveFeedforward(targetVelocity, 0);
        SmartDashboard.putNumber("Shooter Velocity", shooter.getShooterVelocity());
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