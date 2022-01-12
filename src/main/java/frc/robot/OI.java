package frc.robot;

import static com.team2363.utilities.ControllerMap.*;

import java.util.Optional;

import com.team2363.utilities.ControllerMap;
import com.team2363.utilities.ControllerPatroller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetEncoders;
import frc.robot.drive.commands.ZeroHeading;

public class OI {

  private static OI INSTANCE = new OI();
  /**
   * @return retrieves the singleton instance of the Robot Container
   */
  public static OI getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new OI();
    }
    return INSTANCE;
  }

  private static final String DRIVER = "Xbox";
  private static final int DRIVER_PORT = 0;
  private static final String OPERATOR = "P";
  private static final int OPERATOR_PORT = 1;

  private static final String RADIO_MASTER = "TX16S";

  ControllerPatroller cp;
  Drivetrain dt;

  private Joystick driver;
  private Joystick operator;

  public OI() {
    configureJoysticks();
  }

  public void setDrivetrain(Drivetrain dt) {
    this.dt = dt;
  }

  public Joystick getDriverJoystick() {
    return driver;
  }

  public Joystick getOperatorJoystick() {
    return operator;
  }

  public void configureJoysticks() {
    cp = ControllerPatroller.getPatroller();

    // Driver controller will be RadioMaster, XBox, or whatever is plugged into
    // DRIVER_PORT
    Optional<Joystick> rmJoystick = cp.find(RADIO_MASTER);
    driver = rmJoystick.isPresent() ? rmJoystick.get() : cp.get(DRIVER, DRIVER_PORT);

    // Operator Joystick is PS4 controller or whatever is plugged into OPERATOR_PORT
    operator = cp.get(OPERATOR, OPERATOR_PORT);
  }

  /**
   * Be sure Drivetrain is set before calling this!
   */
  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();

    SmartDashboard.putString("Driver Joystick", driver.getName());
    if (driver.getName().contains(RADIO_MASTER)) {
      new JoystickButton(driver, ControllerMap.RM_SF).whenPressed(new ZeroHeading(dt));
    } else { // Assume XBox Controller
      new JoystickButton(driver, ControllerMap.X_BOX_LOGO_LEFT).whenPressed(new ZeroHeading(dt));

      new JoystickButton(driver, ControllerMap.X_BOX_A);
      new JoystickButton(driver, ControllerMap.X_BOX_B);
      new JoystickButton(driver, ControllerMap.X_BOX_X);
      new JoystickButton(driver, ControllerMap.X_BOX_Y);
    }

    // Below moved to a button on ShuffleBoard.
    // new JoystickButton(driver, ControllerMap.X_BOX_LOGO_RIGHT).whenPressed(new ResetEncoders(dt));

  }

  public double getTranslateX() {
    return driver.getRawAxis(ControllerMap.X_BOX_RIGHT_STICK_Y);
  }

  public double getTranslateY() {
    return driver.getRawAxis(ControllerMap.X_BOX_RIGHT_STICK_X);
  }

  public double getRotation() {
    return driver.getRawAxis(ControllerMap.X_BOX_LEFT_STICK_X);
  }

}
