package frc.robot.status.groups;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.status.Status;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.LedAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.status.commands.SetColor;

import java.io.File;

public class LEDDemoCG extends SequentialCommandGroup {

    public LEDDemoCG() {
        // File deployDir = Filesystem.getDeployDirectory();
        // String pathPrefix = deployDir.getAbsolutePath() + "/images/";

        ImageAction ia = new ImageAction("THfade.png").oscillate();
        // addCommands(new ActionCommand(ia));
        // addCommands(new WaitCommand(10));

        addCommands(new ActionCommand(new ImageAction("fade.png",0.02).oscillate().brightness(0.7)));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("noise.png", 0.25);
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("noisy-pulse.png");
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("pulse.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("pulse-down.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("stripes.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction("fade.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        addCommands(new SetColor(Status.getInstance(), Color.kBlack));
    }

}