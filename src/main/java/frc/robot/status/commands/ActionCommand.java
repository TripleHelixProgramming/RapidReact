
package frc.robot.status.commands;

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
    public void initialize() {
        // HelixEvents.getInstance().addEvent("STATUS", "Starting ActionCommand");

        // This will set our action to run on the subsystem.
        // This action will run in the dedicated thread to output the rainbow pattern.
        status.setAction(action);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        // Nothing to do, extending is handled when initialized.
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        // HelixEvents.getInstance().addEvent("STATUS", "Ending ActionCommand");
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

}