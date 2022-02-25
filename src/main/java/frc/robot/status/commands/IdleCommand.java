package frc.robot.status.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
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
    private Action scannerAction = new ScannerAction(Color.kDarkOrchid, 255, 1.0, 0.05);

    public IdleCommand() {
    }

    @Override
    public void initialize() {
        /** 
            getMatchTime() is problematic.
            During a real match, it counts DOWN.
            But when attached to the simulator, or when in free drive TeleOp mode it counts UP.
            This makes it difficult to reliably use it during development.

            Do some gymnastics to try to generate an elapsed time from getMatchTime().
        **/
        double rawTime = Timer.getMatchTime();
        MatchType mt = DriverStation.getMatchType();
        double timeElapsed = 0;
        if (DriverStation.isFMSAttached() || (mt == MatchType.Practice)) {
            // Time will be counting down.
            timeElapsed = TELEOP_LENGTH - rawTime;
        } else {
            // Time will be counting up.
            timeElapsed = rawTime;
        }

        Action action;
//        new PrintCommand("Time Left = " + String.valueOf(timeLeft)).schedule();
        if (105.0 > timeElapsed) {
            action = scannerAction;
        } else if ( 125.0 > timeElapsed ) { // 30 Seconds left
            action = new ImageAction("yellow_stripes.png",0.05);
        } else { // Last 10 seconds
            action = new ChaseAction(255, 127, 0, 90);
        }
        Status.getInstance().setAction(action);
    }
}
