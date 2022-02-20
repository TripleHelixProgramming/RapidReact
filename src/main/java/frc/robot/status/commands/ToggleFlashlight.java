
package frc.robot.status.commands;

// import com.team2363.logger.HelixEvents;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.status.Status;

public class ToggleFlashlight extends Command {

    private Status status = null;

    private boolean forceSet = false;
    private boolean setState = false;

    // Default constructor - will toggle the light.
    public ToggleFlashlight() {
        status = Status.getStatus();
        requires(status);

        // Default will toggle.
    }

    // Override passing a boolean will force the flashlight to a specific state.
    public ToggleFlashlight(boolean state) {
        status = Status.getStatus();
        requires(status);

        forceSet = true;
        setState = state;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // HelixEvents.getInstance().addEvent("STATUS", "Starting ToggleFlashlight");

        // The command can either toggle as is, or set to a specific sate.
        if (forceSet == true) {
            status.setFlashlightState(setState);
        } else {
            // Toggle the flashlight.
            status.toggleFlashlight();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // Nothing to do, extending is handled when initialized.
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // HelixEvents.getInstance().addEvent("STATUS", "Ending ToggleFlashlight");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        // Nothing needed - the toggle is instant.
    }
}