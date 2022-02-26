package frc.robot.status.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.status.Status;
import frc.robot.status.actions.Action;
import frc.robot.status.actions.ChaseAction;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.ScannerAction;

/**
 * This is the command for the robot to run when no buttons
 * are pressed during teleop.
 * 
 * It is a simple wrapper around any other Status command.
 * 
 * This is not the "default" command.
 */
public class IdleCommand extends CommandBase {

    private static final double TELEOP_LENGTH = 135.0;
    private Action scannerAction;
    private Status status;
    private boolean actionSet;
    private int phase = 0;

    public IdleCommand() {
        this.status = Status.getInstance();
        addRequirements(status);
    }

    @Override
    public void initialize() {
        actionSet = false;
        phase = 0;
        if (Alliance.Red == DriverStation.getAlliance()) {
            scannerAction = new ScannerAction(Color.kMaroon, 255, 1.0, 0.05);
        } else if (Alliance.Blue == DriverStation.getAlliance()) {
            scannerAction = new ScannerAction(Color.kDarkBlue, 255, 1.0, 0.05);
        } else {
            scannerAction = new ScannerAction(Color.kDarkOrchid, 200, 1.0, 0.05);
        }
    }

    @Override
    public void execute() {
        /** 
            getMatchTime() is problematic.
            During a real match, it counts DOWN.
            But when attached to the simulator, or when in free drive TeleOp mode it counts UP.
            This makes it difficult to reliably use it during development.

            Do some gymnastics to try to generate an elapsed time from getMatchTime().
        **/
        double timeElapsed = 0;
        timeElapsed = Timer.getFPGATimestamp() - status.getTeleopStartTime();

        Action action = scannerAction;
        // new PrintCommand("Time Left = " + String.valueOf(timeElapsed)).schedule();
        if ( 115.0 < timeElapsed && (0 == phase)) { // 20 Seconds left
            action = new ImageAction("yellow_stripes.png",0.05);
            phase = 1;
            actionSet = false;
        }
         
        if (125.0 < timeElapsed && 1 == phase) { // Last 10 seconds
            action = new ImageAction("noise.png",0.15);
            phase = 2;
            actionSet = false;
        }

        if (!actionSet) {
            status.setAction(action);
            actionSet = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
