package frc.robot.status.commands;

import static com.team2363.utilities.ControllerMap.*;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.status.Status;

public class XBoxButtonCommand extends CommandBase{
    private Status status;
    private Color color;
    private int brightness = 200;
    private int percent = 75;

    public XBoxButtonCommand(int button) {
        status = Status.getInstance();
        addRequirements(status);
        
        switch (button) {
            case X_BOX_A:
                this.color = Color.kGreen;
            break;

            case X_BOX_B:
                this.color = Color.kRed;
            break;

            case X_BOX_X:
                this.color = Color.kBlue;
            break;

            case X_BOX_Y:
                this.color = Color.kYellow;
            break;

            default:
                this.color=Color.kMediumOrchid;
            break;
        }
    }

    @Override
    public void initialize() {
        status.setColor(color, brightness, percent);
    }

    @Override
    public boolean isFinished() {
      return true;
    }      
}
