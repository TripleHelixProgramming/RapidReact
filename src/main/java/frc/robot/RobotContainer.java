// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static com.team2363.utilities.ControllerMap.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.ControllerPatroller;
import frc.lib.HelixJoysticks;
// import frc.robot.indexer.Indexer;
// import frc.robot.intake.Intake;
// import frc.robot.shooter.Shooter;
import frc.paths.gogogogadget;
// import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.JoystickDrive;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.drive.commands.ZeroHeading;
// import frc.robot.indexer.Indexer;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.EjectTrigger;
import frc.robot.shooter.commands.MoveHoodButton;
import frc.robot.shooter.commands.MoveHoodJoystick;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.ResetHood;
import frc.robot.shooter.commands.SpinUpShooter;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.EjectIntake;
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
    TrajectoryFollower follower = new TrajectoryFollower(mDrive, new gogogogadget());
    return follower.andThen(() -> mDrive.brake());
  }

  public void moveHoodToHardStop() {
    new ResetHood(mShooter).schedule();
  }

  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();
    
    driver = ControllerPatroller.getInstance().get(OIConstants.kDriverControllers, OIConstants.kDriverPort);
    operator = ControllerPatroller.getInstance().get(OIConstants.kOperatorControllers, OIConstants.kOperatorPort);
    joysticks = new HelixJoysticks(driver, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);
    op_joysticks = new HelixJoysticks(operator, PS4_RIGHT_STICK_Y, PS4_RIGHT_STICK_X, PS4_LEFT_STICK_X);

    if (driver.getName().contains(OIConstants.kRadioMaster)) {
      new JoystickButton(driver, RM_SD_FRONT).whenPressed(new ZeroHeading(mDrive));

      // Because the RadioMaster has so many more buttons/switches, map many of the operator commands to it, too

      // Intake Control
      new JoystickButton(driver, RM_SE_UP).whenPressed(new DeployIntake(mIntake));
      new JoystickButton(driver, RM_SE_UP).whenReleased(new RetractIntake(mIntake));

      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectTrigger(mShooter));

      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new RetractIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new StopTrigger(mShooter));

      // Enable Hood adjustment
      new JoystickButton(driver, RM_SB_FRONT).whenHeld(new MoveHoodButton(mShooter, Shooter.UP));
      new JoystickButton(driver, RM_SB_BACK).whenHeld(new MoveHoodButton(mShooter, Shooter.DOWN));

      // Trigger
      new JoystickButton(driver, RM_SH).whenPressed(new PullTrigger(mShooter));
      new JoystickButton(driver, RM_SH).whenReleased(new StopTrigger(mShooter));

      // Shoot
      new JoystickButton(driver, RM_SF).whenPressed(new SpinUpShooter(mShooter));
      new JoystickButton(driver, RM_SF).whenReleased(new StopShooter(mShooter));

      // Reset Hood
      new JoystickButton(driver, RM_SC_BACK).whenPressed(new ResetHood(mShooter));

    } else { // Assume XBox Controller
      new JoystickButton(driver, X_BOX_LOGO_LEFT).whenPressed(new ZeroHeading(mDrive));

      // new JoystickButton(driver, X_BOX_A);
      // new JoystickButton(driver, X_BOX_B);
      // new JoystickButton(driver, X_BOX_X);
      // new JoystickButton(driver, X_BOX_Y);

      new JoystickButton(driver, X_BOX_B).whenPressed(new DeployIntake(mIntake));
      new JoystickButton(driver, X_BOX_A).whenReleased(new RetractIntake(mIntake));

      // Reset Hood
      new JoystickButton(driver, X_BOX_LOGO_RIGHT).whenPressed(new ResetHood(mShooter));
    }

    // Operator Buttons - Operator is always PS4

    if (operator.isConnected()) {
      // Intake Control
      new JoystickButton(operator, PS4_CIRCLE).whenPressed(new DeployIntake(mIntake));
      new JoystickButton(operator, PS4_CIRCLE).whenReleased(new RetractIntake(mIntake));

      new JoystickButton(operator, PS4_SQUARE).whenPressed(new EjectIntake(mIntake));
      new JoystickButton(operator, PS4_SQUARE).whenPressed(new EjectTrigger(mShooter));

      new JoystickButton(operator, PS4_SQUARE).whenReleased(new RetractIntake(mIntake));
      new JoystickButton(operator, PS4_SQUARE).whenReleased(new StopTrigger(mShooter));

      // Enable Hood adjustment
      // new JoystickButton(operator, PS4_TRIANGLE).whileHeld(new MoveHoodJoystick(mShooter, operator));

      // Enable Hood adjustment
      new JoystickButton(operator, PS4_R1).whileHeld(new MoveHoodButton(mShooter, Shooter.UP));
      new JoystickButton(operator, PS4_R2).whileHeld(new MoveHoodButton(mShooter, Shooter.DOWN));
      
      // Shooter
      new JoystickButton(operator, PS4_TRIANGLE).whenPressed(new SpinUpShooter(mShooter));
      new JoystickButton(operator, PS4_X).whenPressed(new StopShooter(mShooter));

      new JoystickButton(operator, PS4_L2).whenPressed(new PullTrigger(mShooter));
      new JoystickButton(operator, PS4_L2).whenReleased(new StopTrigger(mShooter));
    }

  }
}