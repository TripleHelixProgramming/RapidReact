// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static com.team2363.utilities.ControllerMap.*;

import static com.team2363.utilities.ControllerMap.RMZ_A_IN;
import static com.team2363.utilities.ControllerMap.RMZ_D_IN;
import static com.team2363.utilities.ControllerMap.RMZ_G_IN;
import static com.team2363.utilities.ControllerMap.RMZ_H_IN;
import static com.team2363.utilities.ControllerMap.RMZ_F_UP;
import static com.team2363.utilities.ControllerMap.RM_SB_BACK;
import static com.team2363.utilities.ControllerMap.RM_SB_FRONT;
import static com.team2363.utilities.ControllerMap.RM_SC_BACK;
import static com.team2363.utilities.ControllerMap.RM_SD_FRONT;
import static com.team2363.utilities.ControllerMap.RM_SE_DOWN;
import static com.team2363.utilities.ControllerMap.RM_SE_UP;
import static com.team2363.utilities.ControllerMap.RM_SG_DOWN;
import static com.team2363.utilities.ControllerMap.RM_SG_UP;
import static com.team2363.utilities.ControllerMap.RM_SH;
import static com.team2363.utilities.ControllerMap.X_BOX_A;
import static com.team2363.utilities.ControllerMap.X_BOX_B;
import static com.team2363.utilities.ControllerMap.X_BOX_LB;
import static com.team2363.utilities.ControllerMap.X_BOX_LEFT_STICK_X;
import static com.team2363.utilities.ControllerMap.X_BOX_LOGO_LEFT;
import static com.team2363.utilities.ControllerMap.X_BOX_LOGO_RIGHT;
import static com.team2363.utilities.ControllerMap.X_BOX_RB;
import static com.team2363.utilities.ControllerMap.X_BOX_RIGHT_STICK_X;
import static com.team2363.utilities.ControllerMap.X_BOX_RIGHT_STICK_Y;
import static com.team2363.utilities.ControllerMap.X_BOX_X;
import static com.team2363.utilities.ControllerMap.X_BOX_Y;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.ControllerPatroller;
import frc.lib.HelixJoysticks;
// import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.auto.groups.FiveBallAuto;
import frc.robot.auto.groups.FourBallAuto;
import frc.robot.auto.groups.NewFiveBallAuto;
import frc.robot.auto.groups.NewFourBallAuto;
import frc.robot.auto.groups.SecondSuperRudeAuto;
import frc.robot.auto.groups.SuperRudeAuto;
import frc.robot.auto.groups.TwoBallEastAuto;
import frc.robot.auto.groups.TwoBallSouthAuto;
import frc.robot.climber.Climber;
import frc.robot.climber.commands.DeployClimber;
import frc.robot.climber.commands.RetractClimber;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.AbsoluteOrientation;
import frc.robot.drive.commands.JoystickDrive;
import frc.robot.drive.commands.RelativeOrientation;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.TurnToAngle;
import frc.robot.drive.commands.ZeroHeading;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.EjectIntake;
import frc.robot.intake.commands.FastIntake;
import frc.robot.intake.commands.OpenIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.EjectTrigger;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PresetFlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.ResetHood;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;
import frc.robot.shooter.commands.VisionAutoShooter;
import frc.robot.shooter.commands.VisionShooter;
import frc.robot.status.Status;
import frc.robot.status.commands.DIOSwitchStatus;
import frc.robot.status.commands.IdleCommand;
import frc.robot.status.commands.SetColor;
import frc.robot.status.commands.XBoxButtonCommand;
import frc.robot.vision.Limelight;
import frc.robot.vision.commands.TurnOffLEDs;
import frc.robot.vision.commands.TurnOnLEDs;

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
  private final Status mStatus = Status.getInstance();
  private final Climber mClimber = new Climber();

  private int mDIOSwitch = -2;
  private final DigitalInput zeroSwitch = new DigitalInput(0);
  private final DigitalInput oneSwitch = new DigitalInput(1);
  private final DigitalInput twoSwitch = new DigitalInput(2);
  private final DigitalInput threeSwitch = new DigitalInput(3);
  private final DigitalInput fourSwitch = new DigitalInput(4);

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

  private boolean limelightEnabled = false;

  private Command innerRudeAuto;
  private Command outerRudeAuto;
  private Command friendlyFourBallAuto;
  private Command fourBallAuto;
  private Command fiveBallAuto;

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
//    mClimber.setDefaultCommand(new RetractClimber(mClimber));

    // Create a button on Smart Dashboard to reset the encoders.
    SmartDashboard.putData("Reset Encoders", new ResetEncoders(mDrive));

    innerRudeAuto = new SuperRudeAuto(mDrive, mIntake, mShooter);
    outerRudeAuto = new SecondSuperRudeAuto(mDrive, mIntake, mShooter);
    friendlyFourBallAuto = new FourBallAuto(mDrive, mIntake, mShooter, mLimelight, joysticks);
    fourBallAuto = new NewFourBallAuto(mDrive, mShooter, mIntake, mLimelight, joysticks);
    fiveBallAuto = new NewFiveBallAuto(mDrive, mShooter, mIntake, mLimelight, joysticks);
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
    
    try {
      if(!fourSwitch.get()){
        autoCommand = fiveBallAuto;
      } else if (!threeSwitch.get()) {
        autoCommand = fourBallAuto;
      } else if (!twoSwitch.get()) {
        autoCommand = friendlyFourBallAuto;
      } else if (!oneSwitch.get()) {
        autoCommand = outerRudeAuto;
      } else if (!zeroSwitch.get()) {
        autoCommand = innerRudeAuto;
      } else {
        autoCommand = fiveBallAuto;
      }
    } finally {
      // Don't close these. It prevents anything else from reading them.
      // fiveBallAuto.close();
      // twoBallSouthAuto.close();
      // twoBallEastAuto.close();
      // fourBallAuto.close();
    }
    // autoCommand = new SuperRudeAuto(mDrive, mIntake, mShooter);
    return autoCommand;
  }

  public void resetSwitch() {
    this.mDIOSwitch = -2;
  }
  
  public void displaySwitch() {
    int cur_switch = -1;
    try {
      if(!fourSwitch.get()){
        cur_switch = fourSwitch.getChannel();
      } else if (!threeSwitch.get()) {
        cur_switch = threeSwitch.getChannel();
      } else if (!twoSwitch.get()) {
        cur_switch = twoSwitch.getChannel();
      } else if (!oneSwitch.get()) {
        cur_switch = oneSwitch.getChannel();
      } else if (!zeroSwitch.get()) {
        cur_switch = zeroSwitch.getChannel();      
      } else {
        cur_switch = -1;
      }
    } catch (Exception e) {

    }

    // The switch position changed.
    if (cur_switch != this.mDIOSwitch) { 
      mDIOSwitch = cur_switch;
      SmartDashboard.putNumber("DIO", mDIOSwitch); 
      new DIOSwitchStatus(mDIOSwitch).schedule();
    }
  }

  /**
   * Called by Robot at teleopInit()
   */
public void resetShooter() {
  mShooter.stopTrigger();
  new StopShooter(mShooter).andThen( new ResetHood(mShooter)).schedule();
}

  public void stopShooter() {
    mShooter.stopTrigger();
    new StopShooter(mShooter).schedule();
  }
  
  public void moveHoodToHardStop() {
    new ResetHood(mShooter).schedule();
  }

  public void enableLights() {
    mLimelight.turnOnLEDs();
  }

  public void disableLights() {
    mLimelight.turnOffLEDs();
  }

  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();

    driver = ControllerPatroller.getInstance().get(OIConstants.kDriverControllers, OIConstants.kDriverPort);
    operator = ControllerPatroller.getInstance().get(OIConstants.kOperatorControllers, OIConstants.kOperatorPort);
    joysticks = new HelixJoysticks(driver, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);
    op_joysticks = new HelixJoysticks(operator, X_BOX_RIGHT_STICK_Y, X_BOX_RIGHT_STICK_X, X_BOX_LEFT_STICK_X);

    if (driver.getName().contains(OIConstants.kZorro)) {

//      new JoystickButton(driver, RMZ_E_UP).whenPressed(new ZeroHeading(mDrive));

      new JoystickButton(driver, RMZ_A_IN).whenHeld(new TurnToAngle(mDrive, mLimelight, joysticks));
      
      new JoystickButton(driver, RMZ_D_IN).whenPressed(new PullTrigger(mShooter, mIntake));
                                                     
      new JoystickButton(driver, RMZ_D_IN).whenReleased(new StopTrigger(mShooter, mIntake));
                                                        

      new JoystickButton(driver, RMZ_H_IN).whenPressed(new ResetHood(mShooter));
      new JoystickButton(driver, RMZ_G_IN).whenReleased(new ZeroHeading(mDrive));

      
      new JoystickButton(driver, RMZ_F_UP).whenHeld(new AbsoluteOrientation(mDrive, joysticks));
//      new JoystickButton(driver, RMZ_F_DOWN).whenHeld(new RelativeOrientation(mDrive, joysticks));

//      new JoystickButton(driver, RMZ_F_UP).whenHeld(new DeployIntake(mIntake));
      
    } else if (driver.getName().contains(OIConstants.kRadioMaster)) {
      new JoystickButton(driver, RM_SD_FRONT).whenPressed(new ZeroHeading(mDrive));

      // Because the RadioMaster has so many more buttons/switches, map many of the
      // operator commands to it, too

      // Intake Control
      new JoystickButton(driver, RM_SE_UP).whenPressed(new FastIntake(mIntake));
      new JoystickButton(driver, RM_SE_UP).whenReleased(new RetractIntake(mIntake));

      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenPressed(new EjectTrigger(mShooter));

      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new RetractIntake(mIntake));
      new JoystickButton(driver, RM_SE_DOWN).whenReleased(new StopTrigger(mShooter, mIntake));

      // Enable Hood adjustment
      // new JoystickButton(driver, RM_SB_FRONT).whenHeld(new MoveHoodButton(mShooter,
      // Shooter.UP));
      // new JoystickButton(driver, RM_SB_BACK).whenHeld(new MoveHoodButton(mShooter,
      // Shooter.DOWN));

      // Trigger
      new JoystickButton(driver, RM_SH).whenPressed(new PullTrigger(mShooter, mIntake));
      new JoystickButton(driver, RM_SH).whenReleased(new StopTrigger(mShooter, mIntake));

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
      // xBoxLB.whenHeld(new DeployIntake(mIntake));
      xBoxLB.whenHeld(new FastIntake(mIntake));
      // xBoxLB.whenReleased(new RetractIntake(mIntake));
      JoystickButton xBoxRB = new JoystickButton(operator, X_BOX_RB);
      xBoxRB.whenHeld(new EjectIntake(mIntake));
      // xBoxRB.whenReleased(new RetractIntake(mIntake));
      // JoystickButton xBoxL = new JoystickButton(operator, X_BOX_LEFT_STICK_BUTTON);
      // JoystickButton xBoxR = new JoystickButton(operator,
      // X_BOX_RIGHT_STICK_BUTTON);

      JoystickButton xBoxA = new JoystickButton(operator, X_BOX_A);

      xBoxA.whileHeld(new TurnOnLEDs(mLimelight));
      xBoxA.whenPressed(new PresetFlywheelController(mShooter,"BUF")          
                          .alongWith(new XBoxButtonCommand(X_BOX_A))); // baseline, upper goal, front shot

      // xBoxA.whenHeld(new VisionAutoShooter(mShooter, mLimelight, mDrive, mIntake)
      // .alongWith(new TurnOnLEDs(mLimelight))
      // .alongWith(new XBoxButtonCommand(X_BOX_A)));

      xBoxA.whenReleased(new StopShooter(mShooter)
                            .alongWith(new TurnOffLEDs(mLimelight))
                            .alongWith(new IdleCommand()));

      JoystickButton xBoxB = new JoystickButton(operator, X_BOX_B);

      xBoxB.whileHeld(new TurnOnLEDs(mLimelight));
      xBoxB.whenHeld(new PresetFlywheelController(mShooter, "BUR")
                          .alongWith(new XBoxButtonCommand(X_BOX_B))); // baseline, upper goal, rear shot

      xBoxB.whenReleased(new StopShooter(mShooter)
                            .alongWith(new TurnOffLEDs(mLimelight))
                            .alongWith(new IdleCommand()));

      JoystickButton xBoxX = new JoystickButton(operator, X_BOX_X);

      xBoxX.whileHeld(new TurnOnLEDs(mLimelight));
      xBoxX.whenHeld(new VisionShooter(mShooter, mLimelight)
                    .alongWith(new XBoxButtonCommand(X_BOX_X)));

      xBoxX.whenReleased(new StopShooter(mShooter)
                            .alongWith(new TurnOffLEDs(mLimelight))
                            .alongWith(new IdleCommand()));

      JoystickButton xBoxY = new JoystickButton(operator, X_BOX_Y);

      xBoxY.whileHeld(new TurnOnLEDs(mLimelight));
      xBoxY.whenHeld(new PresetFlywheelController(mShooter, "SAF")
                          .alongWith(new XBoxButtonCommand(X_BOX_Y))); // tarmac, upper goal, rear shot    

      xBoxY.whenReleased(new StopShooter(mShooter)
                            .alongWith(new TurnOffLEDs(mLimelight))
                            .alongWith(new IdleCommand()));
      
      // Climber
      new JoystickButton(operator, X_BOX_LOGO_RIGHT).whenPressed(new DeployClimber(mClimber));
      new JoystickButton(operator, X_BOX_LOGO_LEFT).whenPressed(new RetractClimber(mClimber));

      new JoystickButton(operator,9).and(new JoystickButton(operator,10))
      .whenActive(new ResetHood(mShooter));

      new Button() {
        @Override
        public boolean get() {
          return (operator.getPOV() == 0);
        }
      }.whenHeld(new PresetFlywheelController(mShooter, "TUR"))
        .whenReleased(new StopShooter(mShooter)
        .alongWith(new IdleCommand()));

      new Button() {
        @Override
        public boolean get() {
          return (operator.getPOV() == 180);
        }
      }.whenPressed(new PresetFlywheelController(mShooter, "BLP")
        .alongWith(new PullTrigger(mShooter, mIntake)))
        .whenReleased(new StopShooter(mShooter).alongWith(new StopTrigger(mShooter, mIntake)));

      new Button() {
        @Override
        public boolean get() {
          return (operator.getPOV() == 270);
        }
      }.whenHeld(new OpenIntake(mIntake));

        // .and(new JoystickButton(operator, X_BOX_LOGO_LEFT))
        // .whenActive(new ToggleClimber(mClimber));

        // new Button() {
        //   @Override
        //   public boolean get() {
        //     return (operator.getPOV() == 180);
        //   }
        // }.whenPressed(new PresetFlywheelController(mShooter, "SAF").alongWith(new XBoxButtonCommand(-1)))
        //   .whenReleased(new StopShooter(mShooter).alongWith(new IdleCommand()));
  
          // .and(new JoystickButton(operator, X_BOX_LOGO_LEFT))
          // .whenActive(new ToggleClimber(mClimber));
  
    }

  }
}

