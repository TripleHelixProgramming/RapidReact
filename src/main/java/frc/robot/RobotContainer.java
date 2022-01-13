// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.team2363.utilities.ControllerMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.ControllerPatroller;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.JoystickDrive;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.ZeroHeading;
import frc.robot.indexer.Indexer;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;

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
  private final Indexer mIndexer = new Indexer();
  private final Shooter mShooter = new Shooter();
  private final PowerDistribution mPDP = new PowerDistribution(
                                                            ElectricalConstants.kPowerDistributionPort, 
                                                            ElectricalConstants.kPowerDistributionType);

  private Joystick driver;
  private Joystick operator;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    mDrive.setDefaultCommand(new JoystickDrive(mDrive));
    mDrive.resetEncoders();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // TODO: replace later with real auto command once we write swerve trajectory follower
    return null;
  }

  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();
    
    driver = ControllerPatroller.getPatroller().get(OIConstants.kDriverControllers, OIConstants.kDriverPort);
    operator = ControllerPatroller.getPatroller().get(OIConstants.kOperatorControllers, OIConstants.kOperatorPort);

    if (driver.getName().contains(OIConstants.kRadioMaster)) {
      new JoystickButton(driver, ControllerMap.RM_SF).whenPressed(new ZeroHeading(mDrive));
    } else { // Assume XBox Controller
      new JoystickButton(driver, ControllerMap.X_BOX_LOGO_LEFT).whenPressed(new ZeroHeading(mDrive));

      new JoystickButton(driver, ControllerMap.X_BOX_A);
      new JoystickButton(driver, ControllerMap.X_BOX_B);
      new JoystickButton(driver, ControllerMap.X_BOX_X);
      new JoystickButton(driver, ControllerMap.X_BOX_Y);
    }
  }
}