package frc.robot.status.commands;

import edu.wpi.first.wpilibj.Timer;
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
    public IdleCommand() {
    }

    @Override
    public void initialize() {
        double elapsedTime = Timer.getMatchTime();
        Action action;
        Status.getInstance().setAction(new ScannerAction(Color.kDarkOrchid, 255, 1.0, 0.05));
        new PrintCommand("Match Time = " + String.valueOf(elapsedTime)).schedule();
        if (105 > elapsedTime) {
            action = new ScannerAction(Color.kDarkOrchid, 255, 1.0, 0.05);
        } else if (115 > elapsedTime ) {
            action = new ImageAction("yellow_stripes.png",0.01);
        } else {
            action = new ChaseAction(255, 127, 0, 90);
        }
        Status.getInstance().setAction(action);
    }
}
