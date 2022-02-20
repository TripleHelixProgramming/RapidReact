
package frc.robot.status.commands;

// import com.team2363.logger.HelixEvents;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.status.actions.Action;
import frc.robot.status.Status;

public class ActionCommand extends CommandBase {

    private Status status = null;
    private Action action = null;

    // Default constructor.
    public ActionCommand(Action action) {
        this.status = Status.getInstance();
        addRequirements(status);
        this.action = action;
    }


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // HelixEvents.getInstance().addEvent("STATUS", "Starting ActionCommand");

        // This will set our action to run on the subsystem.
        // This action will run in the dedicated thread to output the rainbow pattern.
        action.reset();
        status.setAction(action);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // Nothing to do, extending is handled when initialized.
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // This command literally just sets the action on the subsystem.
        // So there's nothing to do.
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // HelixEvents.getInstance().addEvent("STATUS", "Ending ActionCommand");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {

    }
}