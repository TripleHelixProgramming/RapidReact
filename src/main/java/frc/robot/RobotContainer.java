// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static com.team2363.utilities.ControllerMap.*;

import edu.wpi.first.math.geometry.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.ControllerPatroller;
import frc.lib.HelixJoysticks;
// import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.auto.groups.DriveForwardAndShoot;
import frc.robot.auto.groups.FiveBallAuto;
import frc.robot.auto.groups.FourBallAuto;
import frc.robot.auto.groups.ShootAndDriveForward;
import frc.robot.climber.Climber;
import frc.robot.climber.commands.DeployClimber;
import frc.robot.climber.commands.RetractClimber;
import frc.robot.climber.commands.ToggleClimber;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.AbsoluteOrientation;
import frc.robot.drive.commands.JoystickDrive;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TurnToAngle;
import frc.robot.drive.commands.ZeroHeading;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.EjectIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.*;
import frc.robot.vision.Limelight;

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
  private final Limelight mLimelight = new Limelight();
  private final Climber mClimber = new Climber();

  /*
   * private final Indexer mIndexer = new Indexer();
   * private final PowerDistribution mPDP = new PowerDistribution(
   * ElectricalConstants.kPowerDistributionPort,
   * ElectricalConstants.kPowerDistributionModule);
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
    mIntake.setDefaultCommand(new RetractIntake(mIntake));
    // mShooter.setDefaultCommand(new StopShooter(mShooter));
    // mShooter.setDefaultCommand(new FlywheelController(mShooter, 0));
    mClimber.setDefaultCommand(new RetractClimber(mClimber));

    // Create a button on Smart Dashboard to reset the encoders.
    SmartDashboard.putData("Reset Encoders", new ResetEncoders(mDrive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // return new ShootAndDriveForward(mDrive, mIntake, mShooter);
    // return new FourBallAuto(mDrive, mIntake, mShooter);
    // return new TrajectoryFollower(mDrive, new CollectSecondBall());
    Command autoCommand = null;

    DigitalInput fourBallAuto = new DigitalInput(0);
    DigitalInput oneBallAuto = new DigitalInput(1);
    DigitalInput twoBallAuto = new DigitalInput(2);

    try {
      if (!fourBallAuto.get()) {
        autoCommand = new FourBallAuto(mDrive, mIntake, mShooter);
      } else if (!oneBallAuto.get()) {
        autoCommand = new ShootAndDriveForward(mDrive, mIntake, mShooter);
      } else if (!twoBallAuto.get()) {
        autoCommand = new DriveForwardAndShoot(mDrive, mIntake, mShooter);
      }
    } finally {
      fourBallAuto.close();
      oneBallAuto.close();
      twoBallAuto.close();
    }
    // return autoCommand;
    return new FiveBallAuto(mDrive, mIntake, mShooter);
    // return new TurnToAngle(mDrive, mLimelight);
  }

  public void stopShooter() {
    new StopShooter(mShooter).schedule();
  }
  
  public void moveHoodToHardStop() {
    new ResetHood(mShooter).schedule();
  }

  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();

    driver = ControllerPatroller.getInstance().get(OIConstants.kDriverControllers, OIConstants.kDriverPort);
    operator = ControllerPatroller.getInstance().get(OIConstants.kOperatorControllers, OIConstants.kOperatorPort);
    joysticks = new HelixJoysticks(driver, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);
    op_joysticks = new HelixJoysticks(operator, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);

    if (driver.getName().contains(OIConstants.kZorro)) {

      new JoystickButton(driver, RMZ_E_UP).whenPressed(new ZeroHeading(mDrive));

      new JoystickButton(driver, RMZ_A_IN).whenHeld(new TurnToAngle(mDrive, mLimelight, joysticks));
      
      new JoystickButton(driver, RMZ_D_IN).whenPressed(new PullTrigger(mShooter));
      new JoystickButton(driver, RMZ_D_IN).whenReleased(new StopTrigger(mShooter));
      
      // new JoystickButton(driver, RMZ_E_UP).whenHeld(new AbsoluteOrientation(mDrive, joysticks));

      new JoystickButton(driver, RMZ_F_UP).whenHeld(new DeployIntake(mIntake));
      
    } else if (driver.getName().contains(OIConstants.kRadioMaster)) {
      new JoystickButton(driver, RM_SD_FRONT).whenPressed(new ZeroHeading(mDrive));

      // Because the RadioMaster has so many more buttons/switches, map many of the
      // operator commands to it, too

      // Intake Control
      new JoystickButton(driver, RM_SE_UP).whenPressed(new DeployIntake(mIntake));
      new JoystickButton(driver, RM_SE_UP).whenReleased(new RetractIntake(mIntake));

      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectTrigger(mShooter));

      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new RetractIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new StopTrigger(mShooter));

      // Enable Hood adjustment
      // new JoystickButton(driver, RM_SB_FRONT).whenHeld(new MoveHoodButton(mShooter,
      // Shooter.UP));
      // new JoystickButton(driver, RM_SB_BACK).whenHeld(new MoveHoodButton(mShooter,
      // Shooter.DOWN));

      // Trigger
      new JoystickButton(driver, RM_SH).whenPressed(new PullTrigger(mShooter));
      new JoystickButton(driver, RM_SH).whenReleased(new StopTrigger(mShooter));

      // Shoot
      // Backward layup
      new JoystickButton(driver, RM_SG_DOWN).whenPressed(new SetShooterState(mShooter, 1100, 61));
      // Forward high layup
      new JoystickButton(driver, RM_SG_UP).whenPressed(new SetShooterState(mShooter, 1625, 65));
      // middle position - stop
      new JoystickButton(driver, RM_SG_DOWN).whenReleased(new StopShooter(mShooter));
      new JoystickButton(driver, RM_SG_UP).whenReleased(new StopShooter(mShooter));

      new JoystickButton(driver, RM_SB_FRONT).whenPressed(new SetShooterState(mShooter, 1000, 70));
      new JoystickButton(driver, RM_SB_BACK).whenReleased(new SetShooterState(mShooter, 0, 90));

      // Reset Hood (zero with current limit)
      new JoystickButton(driver, RM_SC_BACK).whenPressed(new ResetHood(mShooter));

    }  
/* XBox Controller is now the operator.     
    else { // Assume XBox Controller

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
*/      

    // Operator Buttons - Operator is always XBox

    if (operator.isConnected()) {

      // new JoystickButton(operator, X_BOX_LEFT_TRIGGER);
      // new JoystickButton(operator, X_BOX_RIGHT_TRIGGER);
      JoystickButton xBoxLB = new JoystickButton(operator, X_BOX_LB);
      xBoxLB.whenHeld(new DeployIntake(mIntake));
      // xBoxLB.whenReleased(new RetractIntake(mIntake));
      JoystickButton xBoxRB = new JoystickButton(operator, X_BOX_RB);
      xBoxRB.whenHeld(new EjectIntake(mIntake));
      // xBoxRB.whenReleased(new RetractIntake(mIntake));
      // JoystickButton xBoxL = new JoystickButton(operator, X_BOX_LEFT_STICK_BUTTON);
      // JoystickButton xBoxR = new JoystickButton(operator,
      // X_BOX_RIGHT_STICK_BUTTON);
      JoystickButton xBoxA = new JoystickButton(operator, X_BOX_A);
      xBoxA.whileHeld(new PresetFlywheelController(mShooter,"BUF")); // baseline, upper goal, front shot
      xBoxA.whenReleased(new StopShooter(mShooter));

      JoystickButton xBoxB = new JoystickButton(operator, X_BOX_B);
      xBoxB.whileHeld(new PresetFlywheelController(mShooter, "BUR")); // baseline, upper goal, rear shot
      xBoxB.whenReleased(new StopShooter(mShooter));

      JoystickButton xBoxX = new JoystickButton(operator, X_BOX_X);
      xBoxX.whileHeld(new PresetFlywheelController(mShooter, "TLR")); // tarmac, lower goal, rear shot    
      xBoxX.whenReleased(new StopShooter(mShooter));

      JoystickButton xBoxY = new JoystickButton(operator, X_BOX_Y);
      xBoxY.whileHeld(new PresetFlywheelController(mShooter, "TUR")); // tarmac, upper goal, rear shot    
      xBoxY.whenReleased(new StopShooter(mShooter));
      
      // JoystickButton xBoxLogoLeft = new JoystickButton(operator, X_BOX_LOGO_LEFT);

      // JoystickButton xBoxLogoRight = new JoystickButton(operator, X_BOX_LOGO_RIGHT);
      // xBoxLogoRight.whenPressed(new PullTrigger(mShooter));
      // xBoxLogoRight.whenReleased(new StopTrigger(mShooter));

      // Climber
      new JoystickButton(operator, X_BOX_LOGO_RIGHT)
        .and(new JoystickButton(operator, X_BOX_LOGO_LEFT))
        .whenActive(new ToggleClimber(mClimber));
    }

  }
}

