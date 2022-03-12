package frc.robot.drive.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.control.PIDController;
import frc.lib.control.SwerveTrajectory;
import frc.lib.control.SwerveTrajectory.State;
import frc.paths.Path;
import frc.robot.Constants.AutoConstants;
import frc.robot.drive.Drivetrain;

public class TrajectoryFollower extends CommandBase {
  private Drivetrain drive;
  private SwerveTrajectory trajectory;
  private PIDController xController, yController, thetaController;
  private double lastTime = 0;
  private Timer timer = new Timer();

  public TrajectoryFollower(Drivetrain drive, Path path) {
    addRequirements(drive);
    this.drive = drive;
    this.trajectory = path.getPath();
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    
    xController = new PIDController(AutoConstants.kPTranslationController, 0, 0);
    yController = new PIDController(AutoConstants.kPTranslationController, 0, 0);
    thetaController = new PIDController(AutoConstants.kPThetaController, 0, 0);
    thetaController.setContinous(true);
    thetaController.setInputRange(Math.PI * 2);

    lastTime = 0;
  }

  @Override
  public void execute() {
    double time = timer.get();
    double dt = time - lastTime;
    State refState = trajectory.sample(time);
    Pose2d currentPose = drive.getPose();

    xController.setReference(refState.pose.getX());
    yController.setReference(refState.pose.getY());
    thetaController.setReference(refState.pose.getRotation().getRadians());

    double vx = xController.calculate(currentPose.getX(), dt) + refState.velocity.x;
    double vy = yController.calculate(currentPose.getY(), dt) + refState.velocity.y;
    double omega = -thetaController.calculate(currentPose.getRotation().getRadians(), dt) - refState.velocity.z;


    drive.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
                                                        vx,
                                                        vy,
                                                        omega,
                                                        drive.getHeading()),
                                                        false);
    lastTime = time;
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    drive.brake();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(trajectory.getTotalTime());
  }
}