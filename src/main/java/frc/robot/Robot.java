package frc.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.ControllerPatroller;
import frc.robot.status.Status;
import frc.robot.status.actions.ChaseAction;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.ScannerAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.status.commands.FillLEDsCommand;
import frc.robot.status.commands.IdleCommand;
import frc.robot.status.commands.SetColor;
import frc.robot.status.groups.LEDDemoCG;
import frc.robot.vision.commands.TurnOffLEDs;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer mRobotContainer;

  private static final int NUM_LOOPS = 40;
  private int delay = NUM_LOOPS;

  public static BufferedImage fiveBallAutoImage;
  public static BufferedImage fourBallAutoImage;
  
  public static BufferedImage twentySecondImage;
  public static BufferedImage tenSecondImage;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    Preferences.initDouble("kMaxSpeedMetersPerSecond", Constants.ModuleConstants.kMaxSpeedMetersPerSecond);

    // Set preferences related to preset shots. Set hood angle and shooter wheel velocity for each shot.
    // See the Robot Worksheet "Secondary Drive Interface" sheet for more details.
    Preferences.initDouble("TLR.Velocity", 1650.0);
    Preferences.initDouble("TLR.Angle", 101.5);

    Preferences.initDouble("TUR.Velocity", 1550);
    Preferences.initDouble("TUR.Angle", 92.0);

    Preferences.initDouble("BUF.Velocity", 1800.0);
    Preferences.initDouble("BUF.Angle", 78.25);
    
    Preferences.initDouble("BUR.Velocity", 1980.0);
    Preferences.initDouble("BUR.Angle", 73.25);

    Preferences.initDouble("BLP.Velocity", 500.0);
    Preferences.initDouble("BLP.Angle", 60.0);

    Preferences.initDouble("SAF.Velocity", 2100.0);
    Preferences.initDouble("SAF.Angle", 75.0);

    String pathname = "None";
    try {
      File deployDir = Filesystem.getDeployDirectory();
      String pathPrefix = deployDir.getAbsolutePath() + "/images/";

      pathname = "THfade.png";
      Robot.fiveBallAutoImage = ImageIO.read(new File( pathPrefix+ pathname));
      pathname = "yellow_stripes.png";
      Robot.twentySecondImage = ImageIO.read(new File( pathPrefix+ pathname));
      pathname = "noise.png";
      Robot.tenSecondImage = ImageIO.read(new File( pathPrefix+ pathname));
      pathname = "fade.png";
      Robot.fourBallAutoImage = ImageIO.read(new File( pathPrefix+ pathname));

  } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem opening image. Check the path.\nImage path = " + pathname);
  }


    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our autonomous chooser on the dashboard.
    mRobotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // mRobotContainer.enableLights();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    new SetColor(Status.getInstance(), Color.kBlack).schedule();
  }

  @Override
  public void disabledPeriodic() {
    // Scan the USB devices. If they change, remap the buttons.
    /* Only check if controllers changed every 40 loops of disablePeriodic().
     * This prevents us from hammering on some routines that cause the RIO to lock up.
     */
    delay--;
    if (0 >= delay) { 
      delay = NUM_LOOPS;
      if (ControllerPatroller.getInstance().controllersChanged()) {
        // Reset the joysticks & button mappings.
        mRobotContainer.configureButtonBindings();
      }
    }

    mRobotContainer.disableLights();
    mRobotContainer.displaySwitch();
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    new SetColor(Status.getInstance(), Color.kBlack).schedule();

    m_autonomousCommand = mRobotContainer.getAutonomousCommand();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

    Status.getInstance().setTeleopStartTime();
    new SetColor(Status.getInstance(), Color.kBlack).andThen(new IdleCommand()).schedule();

    // new SetColor(Status.getInstance(), Color.kDarkOrchid).schedule();
    // new ActionCommand(new ScannerAction(245, 0, 255, 200)).schedule();
    // new ActionCommand(new ChaseAction(255, 100, 100, 200)).schedule();
    // new ActionCommand(new PowerUpAction(100, 100, 200, 255)).schedule();

    // String imagePath = Filesystem.getDeployDirectory().getAbsolutePath() + "/images/" + "THfade.png";
    // String imagePath = Filesystem.getDeployDirectory().getAbsolutePath() + "/images/" + "pulse.png";
    // new ActionCommand(new ImageAction(imagePath).oscillate()).schedule();

    // new LEDDemoCG().schedule();
    // new ActionCommand(new ImageAction("yellow_stripes.png",0.05))
    //   .andThen(new WaitCommand(5))
    //   .andThen(new FillLEDsCommand())
    //   .andThen(new WaitCommand(5))
    //   .schedule();

    

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    mRobotContainer.resetShooter();
//    mRobotContainer.moveHoodToHardStop();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
