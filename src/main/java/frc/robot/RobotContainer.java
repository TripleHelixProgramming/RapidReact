// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static com.team2363.utilities.ControllerMap.*;

import java.util.List;

import frc.robot.Constants.*;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.trajectory.*;
import frc.robot.drive.commands.*;
import frc.lib.*;
import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.ControllerPatroller;
import frc.lib.HelixJoysticks;
// import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.JoystickDrive;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.TestDrive;
import frc.robot.drive.commands.ZeroHeading;
import frc.robot.indexer.Indexer;
import frc.robot.indexer.commands.ShootIndexer;
import frc.robot.indexer.commands.StopIndexer;
// import frc.robot.indexer.Indexer;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.MoveHood;
import frc.robot.shooter.commands.SpinUpShooter;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.RetractIntake;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems
  private final Drivetrain mDrive = new Drivetrain();
  
  private final Intake mIntake = new Intake();
  private final Shooter mShooter = new Shooter();
  private final Indexer mIndexer = new Indexer();

  /*
  private final Indexer mIndexer = new Indexer();
  private final PowerDistribution mPDP = new PowerDistribution(
                                                            ElectricalConstants.kPowerDistributionPort, 
                                                            ElectricalConstants.kPowerDistributionModule);
  */
  private Joystick driver;
  private Joystick operator;
  private HelixJoysticks joysticks;
  private HelixJoysticks op_joysticks;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    mDrive.setDefaultCommand(new JoystickDrive(mDrive, joysticks));
    // mDrive.setDefaultCommand(new TestDrive(mDrive));
    // mIntake.setDefaultCommand(new RetractIntake(mIntake));
    mShooter.setDefaultCommand(new SpinUpShooter(mShooter));

    // Create a button on Smart Dashboard to reset the encoders.
    SmartDashboard.putData("Reset Encoders", new ResetEncoders(mDrive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    TrajectoryConfig config =
        new TrajectoryConfig(
                AutoConstants.kMaxSpeedMetersPerSecond,
                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(2.5, 0)),
            // List.of(),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0.5, Rotation2d.fromDegrees(90)),
            config);
        
    SmartDashboard.putNumber("Trajectory Time", exampleTrajectory.getTotalTimeSeconds());
    
    TrajectoryFollower follower = new TrajectoryFollower(mDrive, exampleTrajectory);
    return follower.andThen(() -> mDrive.brake());
    // return null;
  }

  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();
    
    driver = ControllerPatroller.getInstance().get(OIConstants.kDriverControllers, OIConstants.kDriverPort);
    operator = ControllerPatroller.getInstance().get(OIConstants.kOperatorControllers, OIConstants.kOperatorPort);
    joysticks = new HelixJoysticks(driver, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);
    op_joysticks = new HelixJoysticks(operator, PS4_RIGHT_STICK_Y, PS4_RIGHT_STICK_X, PS4_LEFT_STICK_X);

    if (driver.getName().contains(OIConstants.kRadioMaster)) {
      new JoystickButton(driver, RM_SH).whenPressed(new ZeroHeading(mDrive));

      // Because the RadioMaster has so many more buttons/switches, map many of the operator commands to it, too

      // Intake Control
      new JoystickButton(driver, X_BOX_A).whileHeld(new DeployIntake(mIntake));
      new JoystickButton(driver, X_BOX_B).whenReleased(new RetractIntake(mIntake));

      // Enable Hood adjustment
      // new JoystickButton(driver, RM_SA_FRONT).whileHeld(new MoveHood(mShooter, Shooter.UP));
      // new JoystickButton(driver, RM_SA_BACK).whileHeld(new MoveHood(mShooter, Shooter.DOWN));


    } else { // Assume XBox Controller
      new JoystickButton(driver, X_BOX_LOGO_LEFT).whenPressed(new ZeroHeading(mDrive));

      // new JoystickButton(driver, X_BOX_A);
      // new JoystickButton(driver, X_BOX_B);
      // new JoystickButton(driver, X_BOX_X);
      // new JoystickButton(driver, X_BOX_Y);

      new JoystickButton(driver, X_BOX_A).whenPressed(new DeployIntake(mIntake));
      new JoystickButton(driver, X_BOX_B).whenReleased(new RetractIntake(mIntake));

    }

    // Operator Buttons - Operator is always PS4

    if (operator.isConnected()) {
      // Intake Control
      // new JoystickButton(operator, PS4_R1).whenPressed(new DeployIntake(mIntake));
      // new JoystickButton(operator, PS4_L1).whenPressed(new RetractIntake(mIntake));

      // Enable Hood adjustment
      new JoystickButton(operator, PS4_TRIANGLE).whileHeld(new MoveHood(mShooter, operator));

      new JoystickButton(operator, PS4_SQUARE).whenPressed(new SpinUpShooter(mShooter));
      new JoystickButton(operator, PS4_X).whenPressed(new StopShooter(mShooter));

      new JoystickButton(operator, PS4_R1).whileHeld(new ShootIndexer(mIndexer));
      new JoystickButton(operator, PS4_L1).whenPressed(new StopIndexer(mIndexer));
    }

  }
}